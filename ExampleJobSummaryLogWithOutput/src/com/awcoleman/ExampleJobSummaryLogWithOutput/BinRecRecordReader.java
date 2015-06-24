package com.awcoleman.ExampleJobSummaryLogWithOutput;

import java.io.IOException;

import org.apache.avro.mapred.AvroValue;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.awcoleman.examples.avro.BinRecForPartitions;

/**
 * @author awcoleman
 * @version 20150624
 * license: Apache License 2.0; http://www.apache.org/licenses/LICENSE-2.0
 */
public class BinRecRecordReader extends RecordReader<LongWritable, AvroValue<BinRecForPartitions>> {

	FSDataInputStream fsin;

	private LongWritable key = new LongWritable();
	private AvroValue<BinRecForPartitions> val = new AvroValue<BinRecForPartitions>();

	private long start;
	private long pos;
	private long end;

	@Override
	public boolean nextKeyValue() throws InterruptedException {

		/*		out.writeUTF( RandomStringUtils.randomAlphabetic(10) );
		out.writeLong( ++recid );
		out.writeInt( rangen.nextInt(32) );
		out.writeInt( rangen.nextInt(3600) );
		out.writeUTF( generateDatetime() );
		 */
		try {
			BinRecForPartitions datum = BinRecForPartitions.newBuilder()
					.setName( fsin.readUTF() )
					.setRecid( fsin.readLong() )
					.setEventType( fsin.readInt() )
					.setEventDurationSec( fsin.readInt() )
					.setEndDatetime( fsin.readUTF() )
					.setEndDate("UNK")
					.setEndHour(-1)
					.build();

			//Still need endDate,endHour before wrapping in Avro wrapper
			String thisEndDatetime = datum.getEndDatetime().toString();
			String thisEndDate = thisEndDatetime.substring(0,4)+"-"+thisEndDatetime.substring(4,6)+"-"+thisEndDatetime.substring(6,8);
			int thisEndHour = NumberUtils.toInt(thisEndDatetime.substring(8,10));

			datum.setEndDate(thisEndDate);
			datum.setEndHour(thisEndHour);

			//update val
			val = new AvroValue<BinRecForPartitions>(datum);

			//update key
			key.set(key.get()+1L);

			//Update pos for progress status
			pos = fsin.getPos();

			return true;
		} catch (IOException ioe) {
			return false;
		}
	}

	@Override
	public LongWritable getCurrentKey() throws IOException,
	InterruptedException {
		return key;
	}

	@Override
	public AvroValue<BinRecForPartitions> getCurrentValue() throws IOException,
	InterruptedException {
		return val;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		if (start == end) {
			return 0.0f;
		} else {
			return Math.min(1.0f, (pos - start) / (float) (end - start));
		}
	}

	@Override
	public void initialize(InputSplit insplit, TaskAttemptContext context)
			throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();

		FileSplit split = (FileSplit) insplit;

		start = split.getStart();
		end = start + split.getLength();
		pos = start;

		Path path = split.getPath();
		FileSystem fs = path.getFileSystem(conf);
		fsin = fs.open(path);
	}

	@Override
	public void close() throws IOException {
		fsin.close();
	}


}
