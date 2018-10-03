package com.lama.sc.core;
import com.lama.sc.model.IData;

// Inspired by : 
// https://www.programcreek.com/java-api-examples/index.php?source_dir=TeraSpout-master/src/main/java/org/terasology/utilities/Sorting.java

public final class SmoothSort implements ISort {

	private final static ISort INSTANCE = new SmoothSort();

	private SmoothSort(){}

	public static ISort getInstance(){
		return INSTANCE;
	}

	@Override
	public IData process(IData data) {
		return smoothSort(data);	 
	}

	 final int smoothSortLP[] = {1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 
			177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 
			35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 
			1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703, 
			48315633, 78176337, 126491971, 204668309, 331160281, 535828591, 
			866988873 
	}; 

	public IData smoothSort(IData data) { 
		if (data.getLength() > 1) { 
			smoothSort(data, 0, data.getLength() - 1); 
		} 
		return data;
	} 


	public void smoothSort(IData data, int lo, int hi) { 
		int head = lo;
		int[] array = data.get();

		int p = 1; 
		int pshift = 1; 

		while (head < hi) { 
			if ((p & 3) == 3) { 
 
				smoothSortSift(array, pshift, head); 
				p >>>= 2; 
				pshift += 2; 
			} else { 
				if (smoothSortLP[pshift - 1] >= hi - head) { 
					smoothSortTrinkle(array, p, pshift, head, false); 
				} else { 
					smoothSortSift(array, pshift, head); 
				} 

				if (pshift == 1) { 
					p <<= 1; 
					pshift--; 
				} else { 
					p <<= (pshift - 1); 
					pshift = 1; 
				} 
			} 
			p |= 1; 
			head++; 
		} 

		smoothSortTrinkle(array, p, pshift, head, false); 

		while (pshift != 1 || p != 1) { 
			if (pshift <= 1) { 
				int trail = Integer.numberOfTrailingZeros(p & ~1); 
				p >>>= trail; 
					pshift += trail; 
			} else { 
				p <<= 2; 
				p ^= 7; 
				pshift -= 2; 
				
				smoothSortTrinkle(array, p >>> 1, pshift + 1, head - smoothSortLP[pshift] - 1, true); 
				smoothSortTrinkle(array, p, pshift, head - 1, true); 
			} 

			head--; 
		} 
	} 

	private void smoothSortSift(int[] array, int pshift, 
			int head) { 

		int val = array[head]; 

		while (pshift > 1) { 
			int rt = head - 1; 
			int lf = head - 1 - smoothSortLP[pshift - 2]; 

			if (compareTo(val, array[lf]) >= 0 && compareTo(val, array[rt]) >= 0) 
				break; 
			if (compareTo(array[lf], array[rt]) >= 0) { 
				array[head] = array[lf];
				head = lf; 
				pshift -= 1; 
			} else { 
				array[head] = array[rt];
				head = rt; 
				pshift -= 2; 
			} 
		} 
		
		array[head] = val;
	} 

	private void smoothSortTrinkle(int[] array, int p, 
			int pshift, int head, boolean isTrusty) { 

		int val = array[head]; 

		while (p != 1) { 
			int stepson = head - smoothSortLP[pshift]; 

			if (compareTo(array[stepson],val) <= 0) 
				break; 

			if (!isTrusty && pshift > 1) { 
				int rt = head - 1; 
				int lf = head - 1 - smoothSortLP[pshift - 2]; 
				if (compareTo(array[rt], array[stepson]) >= 0 
						|| compareTo(array[lf], array[stepson]) >= 0) 
					break; 
			} 
			
			array[head] = array[stepson];

	
			head = stepson; 
			int trail = Integer.numberOfTrailingZeros(p & ~1); 
			p >>>= trail; 
			pshift += trail; 
			isTrusty = false; 
		} 

		if (!isTrusty) { 
			
			array[head] = val;
			smoothSortSift(array, pshift, head); 
		} 
	}
	
	private int compareTo(int v1, int v2) {
		if(v1 > v2) {
			return 1;
		}
		
		else if(v2 == v1) {
			return 0;
		}
		
		return -1;
	}

	@Override
	public String getTitle() {
		return "Smooth";
	}
}    
