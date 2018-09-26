package com.lama.sc.model.sort;

import com.lama.sc.model.IData;
import com.lama.sc.model.generation.GeneratorData;
import com.lama.sc.model.generation.IGeneratorData;

public class SortedData extends Data implements IData {

	private SortedData(int size){
		super(new int[size]);
	}
	
	public static ISortedData of(int size){
		return new SortedData(size);
	}
}
