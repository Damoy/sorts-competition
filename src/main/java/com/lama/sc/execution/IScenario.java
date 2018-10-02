package com.lama.sc.execution;

import com.lama.sc.utils.time.EnumTimeGranularity;

public interface IScenario {

	public void execute(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode);
	public void output();
	public void display();
	
}
