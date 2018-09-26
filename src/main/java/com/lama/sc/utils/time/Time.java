package com.lama.sc.utils.time;

import com.lama.sc.utils.Utils;

public final class Time {

	private static long start;
	private static long end;
	private final static StringBuilder SBUILDER = new StringBuilder();
	
	private Time(){}
	
	public static void start(){
		start = System.nanoTime();
	}
	
	public static void end(){
		end = System.nanoTime() - start;
	}
	
	public static void display(String title, EnumTimeGranularity granularity){
		SBUILDER.append("----- ");
		SBUILDER.append(title);
		SBUILDER.append(" -----\n");
		SBUILDER.append("Time: ");
		
		switch(granularity){
		case NANOSECONDS:
			SBUILDER.append(end);
			SBUILDER.append(" nanoseconds.");
			break;
		case MICROSECONDS:
			SBUILDER.append(end / 1000);
			SBUILDER.append(" microseconds.");
			break;
		case MILLISECONDS:
			SBUILDER.append(end / 1000000);
			SBUILDER.append(" milliseconds.");
			break;
		case SECONDS:
			SBUILDER.append(end / 1000000000);
			SBUILDER.append(" seconds.");
			break;
		}
		
		Utils.println(SBUILDER);
		resetStringBuilder();
	}
	
	private static void resetStringBuilder(){
		SBUILDER.setLength(0);
	}
}
