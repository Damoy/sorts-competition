package com.lama.sc.core;

import com.lama.sc.model.IData;

public class MergeSort implements ISort {

	private final static ISort INSTANCE = new MergeSort();
	
	private MergeSort() {}
	
	public ISort getInstance(){
		return INSTANCE;
	}
	
	@Override
	public IData process(IData data) {
		// return launch(data, 0, data.getLength());
		return data;
	}
	
	private IData launch(IData data, int ilow, int imax){
//        int m = (l+r) / 2; 
//        
//        process(arr, l, m); 
//        process(arr , m+1, r); 
//        merge(arr, l, m, r);
		return data;
	}
	
	
}
