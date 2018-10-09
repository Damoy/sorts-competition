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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData randomGeneration(int size, int min, int max, EnumRandomGenerationBound bound) {
		IData data = Data.of(size);
		
		switch(bound){
			case LOGN:
				// max = (int) Utils.log2(max);
				for(int i = 0; i < size; ++i)
					data.set(i, (int) Utils.log2(Utils.irand(min, max)));
				
				break;
			case N2:
				// max = max * max;
				
				for(int i = 0; i < size; ++i) {
					int v = Utils.irand(min, max);
					data.set(i, v * v);
				}
				
				break;
			case N:
				for(int i = 0; i < size; ++i)
					data.set(i, Utils.irand(min, max));
				
				break;
		}
		
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData sortedGeneration(int size, int min, int max) {
		return InsertionSort.getInstance().process(randomGeneration(size, min, max, EnumRandomGenerationBound.N));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData reversedGeneration(int size, int min, int max) {
		return sortedGeneration(size, min, max).reverse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData flatGeneration(int size, int value) {
		IData data = Data.of(size);
		
		for(int i = 0; i < size; ++i)
			data.set(i, value);
		
		return data;
	}

}
