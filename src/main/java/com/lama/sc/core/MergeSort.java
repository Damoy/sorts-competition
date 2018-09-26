package com.lama.sc.core;

import com.lama.sc.model.Data;
import com.lama.sc.model.IData;

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
	
	void merge(IData data, int left, int mid, int right) {
		// Find sizes of two sub-arrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;

		/* Create temp arrays */
		IData L = Data.of(n1);
		IData R = Data.of(n2);

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L.set(i, data.get(left + i));
		for (int j = 0; j < n2; ++j)
			R.set(j, data.get(mid + 1 + j));

		/* Merge the temp arrays */
		// Initial indexes of first and second subarrays
		int i = 0;
		int j = 0;

		// Initial index of merged sub-array
		int k = left;
		while (i < n1 && j < n2) {
			if(L.get(j) <= R.get(j)){
				data.set(k, L.get(i));
				++i;
			} else {
				data.set(k, R.get(j));
				++j;
			}
			
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			data.set(k, L.get(i));
			++i;
			++k;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			data.set(k, R.get(j));
			++j;
			++k;
		}
	}
	
}
