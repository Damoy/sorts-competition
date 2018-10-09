package com.lama.sc.core;

import com.lama.sc.model.Data;
import com.lama.sc.model.IData;

/**
 * Iterative Quick sort using median 3 pivot.
 */
public class IterativeQuickSort3Swap implements ISort {

	private final static ISort INSTANCE = new IterativeQuickSort3Swap();

	private IterativeQuickSort3Swap() {
	}

	public static ISort getInstance() {
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		if(data.getLength() > (int) Math.pow(2, 12)) {
			return InsertionSort.getInstance().process(data);
		}
		
		return sort(data, 0, data.getLength() - 1);
	}

	private int partition(IData data, int low, int high) {
		// median 3 pivot
		int m3 = median(data, low, high);
		int temp = data.get(m3);
		data.set(m3, data.get(high));
		data.set(high, temp);
		
		int p = data.get(high);
		int i = (low - 1);
		
		for (int j = low; j <= high - 1; j++) {
			if (data.get(j) <= p) {
				i++;
				int tmp = data.get(i);
				data.set(i, data.get(j));
				data.set(j, tmp);
			}
		}

		int tmp = data.get(i + 1);
		data.set(i + 1, data.get(high));
		data.set(high, tmp);

		return i + 1;
	}
	
	private int median(IData data, int low, int high) {
		int mid = (low + high) >> 1;
			
		if(data.get(low) < data.get(high)) {
			int tmp = data.get(low);
			data.set(low, data.get(high));
			data.set(high, tmp);
		}
		if(data.get(mid) < data.get(low)) {
			int tmp = data.get(low);
			data.set(low, data.get(high));
			data.set(high, tmp);			
		}
		
		if(data.get(high) < data.get(mid)) {
			int tmp = data.get(low);
			data.set(low, data.get(high));
			data.set(high, tmp);		}
		
		return mid;
	}

	private IData sort(IData data, int l, int h) {
		IData s = Data.of(h - l + 1);
		int top = -1;
		s.set(++top, l);
		s.set(++top, h);

		while (top >= 0) {
			h = s.get(top--);
			l = s.get(top--);
			
			int p = partition(data, l, h);

			if (p - 1 > l) {
				s.set(++top, l);
				s.set(++top, p - 1);
			}

			if (p + 1 < h) {
				s.set(++top, p + 1);
				s.set(++top, h);
			}
		}

		return data;
	}

	@Override
	public String getTitle() {
		return "IterativeQuickSort3Swap";
	}
}
