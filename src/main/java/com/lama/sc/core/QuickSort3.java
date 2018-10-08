package com.lama.sc.core;

import com.lama.sc.model.IData;

public class QuickSort3 implements ISort {
	
	private final static ISort INSTANCE = new QuickSort3();

	private QuickSort3(){}

	public static ISort getInstance(){
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		return sort(data, 0, data.getLength() - 1);
	}
	
	private IData sort(IData data, int low, int high) {
		if(low < high) {
			int id  = partition(data, low, high);
			sort(data, low, id - 1);
			sort(data, id + 1, high);
		}
		
		return data;	
	}
	
	private int partition(IData data, int low, int high) {
		int pivot = median(data, low, high);
		int l = low - 1;

		for (int i = low; i < high; ++i) {
			if(data.get(i) <= pivot) {
				++l;
				int temp = data.get(l);
				data.set(l, data.get(i));
				data.set(i, temp);
			}
		}

		int temp = data.get(l + 1);
		data.set(l + 1, data.get(high));
		data.set(high, temp);

		return l + 1;
	}
	
	private int median(IData data, int low, int high) {
		int mid = (low + high) >> 1;
			
		if(data.get(low) < data.get(high))
			swap(data, low, high);
		
		if(data.get(mid) < data.get(low))
			swap(data, mid, low);
		
		if(data.get(high) < data.get(mid))
			swap(data, high, mid);
		
		return mid;
	}
	
	private void swap(IData data, int low, int high) {
		int tmp = data.get(low);
		data.set(low, data.get(high));
		data.set(low, tmp);
	}

	@Override
	public String getTitle() {
		return "Quick3";
	}
}