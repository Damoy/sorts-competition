package com.lama.sc.core;

import java.util.Arrays;

import com.lama.sc.model.IData;

/**
 * Java sort: Dual pivot quick sort.
 */
public class JavaSort implements ISort {
	
	private final static ISort INSTANCE = new JavaSort();

	private JavaSort(){}

	public static ISort getInstance(){
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		Arrays.sort(data.get());
		return data;
	}

	@Override
	public String getTitle() {
		return "Java";
	}

}
