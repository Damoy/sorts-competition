package com.lama.sc.core;

import com.lama.sc.model.IData;

public class InsertionSort implements ISort{
	
	private final static ISort INSTANCE = new InsertionSort();

	private InsertionSort(){}

	public static ISort getInstance(){
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		int size = data.getLength();

		for(int i = 1; i < size; ++i) {
			int key = data.get(i);
			int j = i - 1;

			while(j >= 0 && data.get(j) > key) {
				data.set(j + 1, data.get(j));
				--j;
			}
			
			data.set(j + 1, key);
		}
		
		return data;
	}

	@Override
	public String getTitle() {
		return "Insertion";
	}
	
}
