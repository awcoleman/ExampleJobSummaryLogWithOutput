package com.awcoleman.ExampleJobSummaryLogWithOutput;

import java.io.IOException;

import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.awcoleman.examples.avro.BinRecForPartitions;

/**
 * @author awcoleman
 * @version 20150624
 * license: Apache License 2.0; http://www.apache.org/licenses/LICENSE-2.0
 */
public class BinRecInputFormat extends FileInputFormat<LongWritable, AvroValue<BinRecForPartitions>> {

	@Override
	protected boolean isSplitable(JobContext context, Path filename){
		return false;
	}

	@Override
	public RecordReader<LongWritable, AvroValue<BinRecForPartitions>> createRecordReader(
			InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {
		return new BinRecRecordReader();
	}
}