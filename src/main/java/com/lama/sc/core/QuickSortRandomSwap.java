package com.lama.sc.core;

import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;

/**
 * Recursive Quick sort using random pivot.
 * 
 * Swaps to Insertion sort if StackOverflowError is thrown.
 */
public class QuickSortRandomSwap implements ISort {
	
	private final static ISort INSTANCE = new QuickSortRandomSwap();

	private QuickSortRandomSwap(){}

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
			
			try {
				sort(data, low, id - 1);
				sort(data, id + 1, high);
			} catch(StackOverflowError e) {
				return InsertionSort.getInstance().process(data);
			}
		}
		
		return data;	
	}
	
	private int partition(IData data, int low, int high) {
		// random pivot
		int randPivot = Utils.irand(low, high);
		int tmp = data.get(high);
		data.set(high, data.get(randPivot));
		data.set(randPivot, tmp);
		
		int pivot = data.get(high);
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
	
	@Override
	public String getTitle() {
		return "QuickRand";
	}
}