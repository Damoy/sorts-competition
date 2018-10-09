package com.lama.sc.generator;

import com.lama.sc.model.IData;

/**
 * Data generator.
 * 
 * Proposes different generation strategies.
 */
public interface IGenerator {

	/**
	 * Generates random data
	 */
	public IData randomGeneration(int size, int min, int max, EnumRandomGenerationBound bound);
	
	/**
	 * Generates sorted data
	 */
	public IData sortedGeneration(int size, int min, int max);
	
	/**
	 * Generated reversed sorted data
	 */
	public IData reversedGeneration(int size, int min, int max);
	
	/**
	 * Generate flat data
	 */
	public IData flatGeneration(int size, int value);
	
}
