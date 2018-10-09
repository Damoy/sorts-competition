package com.lama.sc.core;

import java.util.Arrays;

import com.lama.sc.model.Data;
import com.lama.sc.model.IData;

/**
 * Iterative Quick sort with median5 pivot.
 * 
 * Median 5 calculation uses MedianOfMedians algorithm.
 * 
 * https://en.wikipedia.org/wiki/Median_of_medians
 */
public class IterativeQuickSort5 implements ISort {

	private final static ISort INSTANCE = new IterativeQuickSort5();
	private final static int SUBLIST_LENGTH = 5;

	private IterativeQuickSort5() {
	}

	public static ISort getInstance() {
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		return sort(data, 0, data.getLength() - 1);
	}

	private int partition(IData data, int low, int high) {
		// median 5 pivot
		int p = MedianOfMedians.findMedian(data);
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
		return "IterativeQuickSort5";
	}
	
	private static class MedianOfMedians {
		
		public static int findMedian(IData data) {
			return findMedianUtil(data, SUBLIST_LENGTH, 0, data.getLength() - 1);
		}

		private static int findMedianUtil(IData data, int k, int low, int high) {
			if (low == high) {
				return data.get(low);
			}

			// sort the mth largest element in the given array
			int m = partition(data, low, high);

			// Adjust position relative to the current subarray being processed
			int length = m - low + 1;

			// If mth element is the median, return it
			if (length == k) {
				return data.get(m);
			}

			// If mth element is greater than median, search in the left subarray
			if (length > k) {
				return findMedianUtil(data, k, low, m - 1);
			}
			// otherwise search in the right subArray
			else {
				return findMedianUtil(data, k - length, m + 1, high);
			}
		}

		private static int partition(IData data, int low, int high) {
			// Get pivotvalue by finding median of medians
			int pivotValue = getPivotValue(data, low, high);

			// Find the sorted position for pivotVale and return it's index
			while (low < high) {
				while (data.get(low) < pivotValue) {
					low++;
				}

				while (data.get(high) > pivotValue) {
					high--;
				}

				if (data.get(low) == data.get(high)) {
					low++;
				} else if (low < high) {
					int tmp = data.get(low);
					data.set(low, data.get(high));
					data.set(high, tmp);
				}
			}
			
			return high;
		}

		// Find pivot value, such the it is always 'closer' to the actual median
		private static int getPivotValue(IData data, int low, int high) {
			// If number of elements in the array are small, return the actual median
			if (high - low + 1 <= 9) {
				Arrays.sort(data.get());
				return data.get(data.getLength() >> 1);
			}

			// Otherwise divide the array into subarray of 5 elements each, and recursively
			// find the median

			// Array to hold '5 element' subArray, last subArray may have less than 5
			// elements
			IData tmp = null;

			// Array to hold the medians of all '5-element SubArrays'
			IData medians = Data.of((int) Math.ceil((double) (high - low + 1) / 5));
			int medianIndex = 0;

			while (low <= high) {
				// get size of the next element, it can be less than 5
				tmp = Data.of(Math.min(5, high - low + 1));

				// copy next 5 (or less) elements, in the subarray
				for (int j = 0; j < tmp.getLength() && low <= high; j++) {
					tmp.set(j, data.get(low));
					low++;
				}

				// sort subArray
				Arrays.sort(tmp.get());

				// find mean and store it in median Array
				medians.set(medianIndex, tmp.get(tmp.getLength() >> 1));
				medianIndex++;
			}

			// Call recursively to find median of medians
			return getPivotValue(medians, 0, medians.getLength() - 1);
		}
	}

}
