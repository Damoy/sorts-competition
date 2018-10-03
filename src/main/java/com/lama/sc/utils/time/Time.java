package com.lama.sc.utils.time;

public final class Time {

	private static long start;
	private static long end;
	private final static StringBuilder SBUILDER = new StringBuilder();
	
	private Time(){}
	
	@Deprecated
	public static void start(){
		start = System.nanoTime();
	}
	
	@Deprecated
	public static void stop(){
		end = System.nanoTime() - start;
	}
	
	@Deprecated
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
	
	public static long getComputedTime(long time, EnumTimeGranularity timeGranularity){
		switch(timeGranularity){
			case MICROSECONDS:
				return time / 1000;
			case MILLISECONDS:
				return time / 1000000;
			case SECONDS:
				return time / 1000000000;
			case NANOSECONDS:
			default:
		}
		
		return time;
	}
	
	private static void resetStringBuilder(){
		SBUILDER.setLength(0);
	}
}
