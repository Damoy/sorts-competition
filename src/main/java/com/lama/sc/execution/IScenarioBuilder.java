package com.lama.sc.execution;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

/**
 * Allows to build scenarios.
 */
public interface IScenarioBuilder {
	
	/**
	 * Build a scenqrio.
	 * 
	 * @param width window width
	 * @param height window height
	 */
	public IScenario build(String applicationTitle, String scenarioTitle, int width, int height);
	
	/**
	 * Add entry to process.
	 * 
	 * @param entryTitle its title
	 * @param sortAlgo sort used
	 * @param data to process
	 */
	public IScenarioBuilder addEntry(String entryTitle, ISort sortAlgo, IData data);
	
	/**
	 * Reset the builder
	 */
	public IScenarioBuilder clear();
	
}
