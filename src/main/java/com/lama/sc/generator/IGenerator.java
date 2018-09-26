package com.lama.sc.generator;

import com.lama.sc.model.IGeneratorData;

public interface IGenerator {

	public IGeneratorData randomGeneration(int size, int low, int max);
	
}
