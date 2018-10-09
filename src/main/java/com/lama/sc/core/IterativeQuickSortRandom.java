package com.lama.sc.core;

import com.lama.sc.model.Data;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;

/**
 * Quick sort using random pivot.
 */
public class IterativeQuickSortRandom implements ISort {

	private final static ISort INSTANCE = new IterativeQuickSortRandom();

	private IterativeQuickSortRandom() {
	}

	public static ISort getInstance() {
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		return sort(data, 0, data.getLength() - 1);
	}

	private int partition(IData data, int low, int high) {
		// random pivot
		int randPivot = Utils.irand(low, high);
		int temp = data.get(high);
		data.set(high, data.get(randPivot));
		data.set(randPivot, temp);
		
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
		return "IterativeQuickSortRand";
	}
}
