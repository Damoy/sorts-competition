package com.lama.sc.execution;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

/**
 * A scenario entry
 */
public interface IScenarioEntry {

	/**
	 * Get the entry data to process
	 */
	public IData getData();
	
	/**
	 * Get the entry sort algorithm to use
	 */
	public ISort getSortAlgorithm(); 
	
	/**
	 * Get the entry title
	 */
	public String getTitle();
	
}
