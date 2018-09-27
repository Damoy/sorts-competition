package com.lama.sc.execution;

import com.lama.sc.utils.time.EnumTimeGranularity;

public interface IScenario {

	public void execute(EnumTimeGranularity timeGranularity);
	public void output();
	
}
