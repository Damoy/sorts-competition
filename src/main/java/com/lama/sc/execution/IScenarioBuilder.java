package com.lama.sc.execution;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

public interface IScenarioBuilder {
	
	public IScenario build(String scenarioTitle);
	public IScenarioBuilder addEntry(String entryTitle, ISort sortAlgo, IData data);
	public IScenarioBuilder clear();
	
}
