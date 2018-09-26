package com.lama.sc.core;

import com.lama.sc.model.IData;

public class HeapSort implements ISort{

	private final static ISort INSTANCE = new HeapSort();
	
	private HeapSort() {}
	
	public static ISort getInstance(){
		return INSTANCE;
	}
	
	@Override
	public IData process(IData data) {
		return data;
	}
	
}
