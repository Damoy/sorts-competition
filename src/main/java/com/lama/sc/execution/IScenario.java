package com.lama.sc.execution;

import com.lama.sc.visualisation.SortVisualizer;

public interface IScenario {

	public void execute(ScenarioConfig config);
	public void output();
	public void display();
	public SortVisualizer getVisualizer();
	
}
