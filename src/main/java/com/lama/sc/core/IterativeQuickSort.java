package com.lama.sc.core;

import com.lama.sc.model.IData;
import java.util.*; 

public class IterativeQuickSort implements ISort {

	private final static ISort INSTANCE = new IterativeQuickSort();

	private IterativeQuickSort(){}

	public static ISort getInstance(){
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		return sort(data, 0, data.getLength() - 1);
	}


	static int partition(IData data, int low, int high) { 
		int p = data.get(high); 

		int i = (low-1);  
		for (int j = low; j <= high-1; j++) { 
 
			if (data.get(j) <= p) { 
				i++; 
				int tmp = data.get(i);
				data.set(i, data.get(j));
				data.set(j, tmp);
			} 
		} 

		int tmp = data.get(i+1); 
		data.set(i+1, data.get(high));
		data.set(high, tmp);

		return i+1; 
	} 

	static IData sort (IData data, int l, int h) { 
		int[] s = new int[h-l+1]; 
		int top = -1; 
		s[++top] = l; 
		s[++top] = h; 

		while (top >= 0) { 
			h = s[top--]; 
			l = s[top--]; 

			int p = partition(data, l, h); 

			if (p-1 > l) { 
				s[++top] = l; 
				s[++top] = p - 1; 
			} 

			if (p+1 < h) { 
				s[++top] = p + 1; 
				s[++top] = h; 
			} 
		} 
		
		return data;
	} 

	@Override
	public String getTitle() {
		return "IterativeQuickSort";
	}
}
