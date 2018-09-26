package com.lama.sc.utils;

import java.util.Random;

public final class Utils {

	private final static Random SEED = new Random();
	
	private Utils(){}
	
	public static <T> void println(T o){
		System.out.println(o.toString());
	}
	
	public static <T> void print(T o){
		System.out.print(o.toString());
	}
	
	public static int irand(int min, int max){
		return SEED.nextInt((max - min) + 1) + min;
	}
	
}
