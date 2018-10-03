package com.lama.sc.execution;

import com.lama.sc.rendering.Chart;

public interface IScenario {

	public void execute(ScenarioConfig config, int times);
	public void output();
	public void display();
	public Chart getChart();
	
}
