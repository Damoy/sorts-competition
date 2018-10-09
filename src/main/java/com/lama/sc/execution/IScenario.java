package com.lama.sc.execution;

import com.lama.sc.visualisation.SortVisualizer;

/**
 * A bundle of executions to process.
 */
public interface IScenario {

	/*
	 * Executes the scenario using the configuration inquired. 
	 */
	public void execute(ScenarioConfig config);
	
	/**
	 * File output.
	 */
	public void output();
	
	/**
	 * Console output.
	 */
	public void display();
	
	/**
	 * Get reference to the visualiser.
	 */
	public SortVisualizer getVisualizer();
	
}
