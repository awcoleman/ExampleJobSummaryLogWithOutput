package com.awcoleman.ExampleJobSummaryLogWithOutput;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileConstants;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyOutputFormat;
import org.apache.avro.reflect.ReflectData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.awcoleman.examples.avro.BinRecForPartitions;

/**
 * Example that opens a temporary log file in the Driver and moves that
 *   log to HDFS with the output under .../_log/joblog.log
 * 
 * @author awcoleman
 * @version 20150624
 * license: Apache License 2.0; http://www.apache.org/licenses/LICENSE-2.0
 */
public class BinRecToAvroRecDriver extends Configured implements Tool {
	private static final Log logger = LogFactory.getLog(BinRecToAvroRecDriver.class);

	public static class Map extends Mapper<LongWritable, AvroValue<BinRecForPartitions>, AvroKey<String>, AvroValue<BinRecForPartitions>> {

		protected void map(LongWritable key, AvroValue<BinRecForPartitions> value, Context context) throws IOException, InterruptedException {

			context.getCounter(MYJOB_CNTRS.MYCNT1).increment(1);

			//Key from map is "YYYY-MM-DD-HH"
			AvroKey<String> outkey = new AvroKey<String>( value.datum().getEndDate().toString()+"-"+value.datum().getEndHour() );
			context.write(outkey, value);
		}
	}


	public static class Reduce extends Reducer<AvroKey<String>, AvroValue<BinRecForPartitions>, AvroKey<BinRecForPartitions>, NullWritable> {

		public void reduce(AvroKey<String> key, Iterable<AvroValue<BinRecForPartitions>> values, Context context) throws IOException, InterruptedException {

			context.getCounter(MYJOB_CNTRS.MYCNT2).increment(1);

			for (AvroValue<BinRecForPartitions> val : values) {
				context.getCounter(MYJOB_CNTRS.MYCNT3).increment(1);

				AvroKey<BinRecForPartitions> outkey = new AvroKey<BinRecForPartitions>(val.datum());
				context.write(outkey, NullWritable.get()); 	
			}
		}
	}

	/*
	 * Create a log FileAppender on the localfs. On job completion, this will be moved to HDFS in the output directory.
	 */
	private String createTempFileAppender(Job job) throws IOException {
		String sep = FileSystems.getDefault().getSeparator();

		//JobID may not exist yet, but JobName does since we call getInstance with name, so use JobName as prefix
		java.nio.file.Path temppath = Files.createTempDirectory(job.getJobName()+"_");

		String fapath = temppath+sep+"joblog.log";

		FileAppender fa = new FileAppender();
		fa.setName("TempFileAppender_"+job.getJobName());
		fa.setFile(fapath);
		fa.setLayout(new PatternLayout("%d{ISO8601} %p %c: %m%n"));
		fa.setThreshold(Level.INFO);
		fa.setAppend(true);
		fa.activateOptions();

		Logger.getRootLogger().addAppender(fa);

		//Add cleanup hooks, log file itself should be deleted by copyFromLocalFile after copy to HDFS
		temppath.toFile().deleteOnExit();

		return fapath;
	}

	/*
	 * Moved the log to HDFS in the output directory.
	 */
	private boolean copyTempFileAppenderToHDFSOutpath(Job job, String fapath, String outpath) {
		String sep = FileSystems.getDefault().getSeparator();

		try {
			FileSystem hdfs = FileSystem.get(job.getConfiguration());

			Path localfile = new Path("file://"+fapath);
			Path hdfsfile = new Path(outpath+sep+"_log"+sep+"joblog.log");
			
			//About to move job summary log to HDFS, so remove from root logger and close
			FileAppender fa = (FileAppender) Logger.getRootLogger().getAppender("TempFileAppender_"+job.getJobName());
			Logger.getRootLogger().removeAppender(fa);
			fa.close();
			
			hdfs.copyFromLocalFile(true, false, localfile, hdfsfile);

			return true;
		} catch (IOException ioe) {
			logger.warn("Unable to move job summary log to HDFS.",ioe);
			return false;
		}
	}

	public int run(String[] args) throws Exception {

		String input = null;
		String output = null;

		if (args.length < 2) {
			System.err.printf("Usage: %s <input> <output>\n",this.getClass().getSimpleName());
			return -1;
		} else {
			input = args[0];
			output = args[1];
		}

		Job job = Job.getInstance(getConf(), "BinRecToAvroRecDriver");
		Configuration conf = job.getConfiguration();

		//Add job log to hold Driver logging (and any summary info about the dataset,job, or counters we want to write)
		String fapath = createTempFileAppender(job);

		//get schema
		Schema outSchema = ReflectData.get().getSchema(com.awcoleman.examples.avro.BinRecForPartitions.class);
		job.getConfiguration().set("outSchema",outSchema.toString());

		//Job conf settings
		job.setJarByClass(BinRecToAvroRecDriver.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setInputFormatClass(BinRecInputFormat.class);
		job.setOutputFormatClass(AvroKeyOutputFormat.class);
		AvroJob.setOutputKeySchema(job, outSchema);

		AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.STRING));
		AvroJob.setMapOutputValueSchema(job, outSchema);


		//Job output compression
		FileOutputFormat.setCompressOutput(job, true);
		job.getConfiguration().set(AvroJob.CONF_OUTPUT_CODEC, DataFileConstants.DEFLATE_CODEC);

		//Input and Output Paths
		FileInputFormat.setInputPaths(job, new Path(input));
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		outPath.getFileSystem(conf).delete(outPath, true);

		boolean jobCompletionStatus = job.waitForCompletion(true);

		//Print Custom Counters before exiting
		Counters counters = job.getCounters();
		for (MYJOB_CNTRS customCounter : MYJOB_CNTRS.values()) {
			Counter thisCounter = counters.findCounter(customCounter);
			System.out.println("Custom Counter "+customCounter+"="+thisCounter.getValue());
		}

		long mycnt1 = job.getCounters().findCounter("com.awcoleman.TestingGettingContainerLogger.BinRecToAvroRecDriver$MYJOB_CNTRS","MYCNT1").getValue();
		long mycnt2 = job.getCounters().findCounter("com.awcoleman.TestingGettingContainerLogger.BinRecToAvroRecDriver$MYJOB_CNTRS","MYCNT2").getValue();
		long mycnt3 = job.getCounters().findCounter("com.awcoleman.TestingGettingContainerLogger.BinRecToAvroRecDriver$MYJOB_CNTRS","MYCNT3").getValue();

		long myfakekpi = mycnt1 - mycnt2;

		String msgMyfakekpi="The Fake KPI of the Dataset: "+String.format("%,d", myfakekpi);
		System.out.println(msgMyfakekpi);
		logger.info(msgMyfakekpi);

		//Finished, so move job log to HDFS in _log dir, clean
		copyTempFileAppenderToHDFSOutpath(job, fapath, output);
				
		return jobCompletionStatus ? 0 : 1;
	}

	public static enum MYJOB_CNTRS {
		MYCNT1,
		MYCNT2,
		MYCNT3
	}

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new Configuration(), new BinRecToAvroRecDriver(), args));
	}

}
