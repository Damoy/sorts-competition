package com.lama.sc.core;

import com.lama.sc.model.IData;

public class HeapSort implements ISort{

	private final static ISort INSTANCE = new HeapSort();
	
	private HeapSort() {}
	
	public static ISort getInstance(){
		return INSTANCE;
	}
	
	@Override
	public IData process(IData data) {
		int len = data.getLength();
		
		// To build the heap
		int i;
		for(i = (len >> 1) - 1; i >= 0; --i)
			heapify(data, len, i);
		
		// To extract one by one an element from the heap
		for(i = len - 1; i >= 0; --i) {
			// Move the current root to the end
			int tmp = data.get(0);
			data.set(0, data.get(i));
			data.set(i, tmp);
			
			// Call the heapify (max) on the reduced heap
			heapify(data, i, 0);
		}
		
		return data;
	}
	
	// To be able to heapify a subtree
	private void heapify(IData data, int len, int i) {
		// biggest == root
		int biggest = i;
		int left = (i << 1) + 1;
		int right = (i << 1) + 2;
		
		// Is left child biggest than the root ?
		if(left < len && data.get(left) > data.get(biggest))
			biggest = left;
		
		// Is right child bigger than biggest found until now ?
		if(right < len && data.get(right) > data.get(biggest))
			biggest = right;
		
		// If the biggest found is not the root
		if(biggest != i) {
			int tmp = data.get(i);
			data.set(i, data.get(biggest));
			data.set(biggest, tmp);
			
			// Heapify recursively
			heapify(data, len, biggest);
		}
	}

	@Override
	public String getTitle() {
		return "Heap";
	}
	
}
