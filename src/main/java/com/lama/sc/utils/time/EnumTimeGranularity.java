package com.lama.sc.utils.time;

public enum EnumTimeGranularity {
	NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS;
	
	public static String getString(EnumTimeGranularity timeGranularity){
		switch(timeGranularity){
			case NANOSECONDS:
				return "nanoseconds";
			case MICROSECONDS:
				return "microseconds";
			case MILLISECONDS:
				return "milliseconds";
			case SECONDS:
				return "seconds";
			default:
				return "milliseconds";
		}
	}
}
