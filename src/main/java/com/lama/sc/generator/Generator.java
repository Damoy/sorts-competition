package com.lama.sc.generator;

import com.lama.sc.core.InsertionSort;
import com.lama.sc.model.Data;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;

public class Generator implements IGenerator {

	private final static IGenerator INSTANCE = new Generator();
	
	private Generator(){}
	
	public static IGenerator getInstance(){
		return INSTANCE;
	}
	
	public IData randomGeneration(int size, int min, int max) {
		IData data = Data.of(size);
		
		for(int i = 0; i < size; ++i)
			data.set(i, Utils.irand(min, max));
		
		return data;
	}

	@Override
	public IData sortedGeneration(int size, int min, int max) {
		return InsertionSort.getInstance().process(randomGeneration(size, min, max));
	}

	@Override
	public IData reversedGeneration(int size, int min, int max) {
		return sortedGeneration(size, min, max).reverse();
	}

	@Override
	public IData flatGeneration(int size, int value) {
		IData data = Data.of(size);
		
		for(int i = 0; i < size; ++i)
			data.set(i, value);
		
		return data;
	}

}
