package com.lama.sc.generator;

import com.lama.sc.model.IData;

public interface IGenerator {

	public IData randomGeneration(int size, int min, int max);
	
}