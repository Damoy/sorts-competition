package com.lama.sc.core;

import com.lama.sc.model.IData;

/**
 * A sort algorithm.
 */
public interface ISort {

	/**
	 * Launch the algorithm processing.
	 * 
	 * @param data the data to process
	 * @return the same data reference, sorted
	 */
	public IData process(IData data);
	
	/**
	 * Used in visualization.
	 */
	public String getTitle();
	
}
