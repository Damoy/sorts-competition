package com.lama.sc.core;

import com.lama.sc.model.IData;

public class MergeSort2 implements ISort {

	private final static ISort INSTANCE = new MergeSort2();
	
	private MergeSort2() {}
	
	public static ISort getInstance(){
		return INSTANCE;
	}
	
	@Override
	public IData process(IData data) {
		IData buffer = data.clone();
		return execute(data, buffer, 0, data.getLength() - 1);
	}
	
	private IData execute(IData data, IData buffer, int left, int right){
		if(left < right){
			int mid = (left + right) >> 1; 
			execute(data, buffer, left, mid); 
			execute(data, buffer, mid + 1, right); 
			merge(data, buffer, left, mid, right);
			
//			execute(data, left, mid); 
//			execute(data, mid + 1, right); 
//			merge(data, left, mid, right);
		}
		
		return data;
	}
	
	// TODO only one buffer
	void merge(IData data, IData buffer, int left, int mid, int right) {
		// Find sizes of two sub-arrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Create temporary data
		// IData dl = Data.of(n1);
		// IData dr = Data.of(n2);

		// Copy data to temporary ones
		for (int i = 0; i < n1; ++i)
			buffer.set(i, data.get(left + i));
		for (int j = n1; j < n2; ++j)
			buffer.set(j, data.get(mid + 1 + j));

		// Merge the temporary data
		int i = 0;
		int j = n1;
		int k = left;
		
		while (i < n1 && j < n2) {
			if(buffer.get(i) <= buffer.get(j)){
				data.set(k, buffer.get(i));
				++i;
			} else {
				data.set(k, buffer.get(j));
				++j;
			}
			
			++k;
		}

		// Copy remaining elements
		while (i < n1) {
			data.set(k, buffer.get(i));
			++i;
			++k;
		}

		while (j < n2) {
			data.set(k, buffer.get(j));
			++j;
			++k;
		}
	}

	@Override
	public String getTitle() {
		return "Merge";
	}
	
}
