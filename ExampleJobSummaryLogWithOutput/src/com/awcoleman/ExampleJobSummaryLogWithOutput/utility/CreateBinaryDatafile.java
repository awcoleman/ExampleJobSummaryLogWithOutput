package com.awcoleman.ExampleJobSummaryLogWithOutput.utility;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/*
 * Create a sample datafile to read in with the custom InputFormat
 * 
 * Datafile will not be splittable since there is no specific record marker
 * 
 * name,recid,eventType,eventDurationSec,endDatetime
 * string,long,int,int,string
 * 
 * @author awcoleman
 * @version 20150624
 * license: Apache License 2.0; http://www.apache.org/licenses/LICENSE-2.0
 */
public class CreateBinaryDatafile {

	public CreateBinaryDatafile(int numrecs) throws IOException {

		Random rangen = new Random(); 
		long recid=0;
		try (
				DataOutputStream out = new DataOutputStream(new FileOutputStream("/tmp/sampleDatafile.bin"));
				) {

			for (int ii=0;ii<numrecs;ii++) {
				out.writeUTF( RandomStringUtils.randomAlphabetic(10) );
				out.writeLong( ++recid );
				out.writeInt( rangen.nextInt(32) );
				out.writeInt( rangen.nextInt(3600) );
				out.writeUTF( generateDatetime() );
			}
		}
	}

	public String generateDatetime() {		
		long offset = Timestamp.valueOf("2015-02-21 00:00:00").getTime();
		long end = Timestamp.valueOf("2015-02-24 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss.SSS");
		
		return sdf.format(rand);
	}

	public static void main(String[] args) throws IOException {
		CreateBinaryDatafile mainObj = new CreateBinaryDatafile(200);
	}

}
