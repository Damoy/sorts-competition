package com.lama.sc.utils.time;

public final class Time {

	private static long start;
	private static long end;
	private final static StringBuilder SBUILDER = new StringBuilder();
	
	private Time(){}
	
	public static void start(){
		start = System.nanoTime();
	}
	
	public static void stop(){
		end = System.nanoTime() - start;
	}
	
	public static String output(EnumTimeGranularity granularity){
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
		
		String sbuilderContent = SBUILDER.toString();
		resetStringBuilder();
		return sbuilderContent;
	}
	
	public static long getComputedTime(EnumTimeGranularity timeGranularity){
		long computedTime = end;
		
		switch(timeGranularity){
			case MICROSECONDS:
				computedTime = end / 1000;
				break;
			case MILLISECONDS:
				computedTime = end / 1000000;
				break;
			case SECONDS:
				computedTime = end / 1000000000;
				break;
			case NANOSECONDS:
			default:
		}
		
		return computedTime;
	}
	
	private static void resetStringBuilder(){
		SBUILDER.setLength(0);
	}
}
