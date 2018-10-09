package com.lama.sc.core;

import com.lama.sc.model.Data;
import com.lama.sc.model.IData;

/**
 * Classic merge sort.
 * Uses temporary data.
 */
public class MergeSort implements ISort {

	private final static ISort INSTANCE = new MergeSort();
	
	private MergeSort() {}
	
	public static ISort getInstance(){
		return INSTANCE;
	}
	
	@Override
	public IData process(IData data) {
		return execute(data, 0, data.getLength() - 1);
	}
	
	private IData execute(IData data, int left, int right){
		if(left < right){
			int mid = (left + right) >> 1; 
			execute(data, left, mid); 
			execute(data, mid + 1, right); 
			merge(data, left, mid, right);
		}
		
		return data;
	}
	
	// TODO only one buffer
	void merge(IData data, int left, int mid, int right) {
		// Find sizes of two sub-arrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Create temporary data
		IData dl = Data.of(n1);
		IData dr = Data.of(n2);

		// Copy data to temporary ones
		for (int i = 0; i < n1; ++i)
			dl.set(i, data.get(left + i));
		for (int j = 0; j < n2; ++j)
			dr.set(j, data.get(mid + 1 + j));

		// Merge the temporary data
		int i = 0;
		int j = 0;
		int k = left;
		
		while (i < n1 && j < n2) {
			if(dl.get(i) <= dr.get(j)){
				data.set(k, dl.get(i));
				++i;
			} else {
				data.set(k, dr.get(j));
				++j;
			}
			
			++k;
		}

		// Copy remaining elements
		while (i < n1) {
			data.set(k, dl.get(i));
			++i;
			++k;
		}

		while (j < n2) {
			data.set(k, dr.get(j));
			++j;
			++k;
		}
	}

	@Override
	public String getTitle() {
		return "Merge";
	}
	
}
