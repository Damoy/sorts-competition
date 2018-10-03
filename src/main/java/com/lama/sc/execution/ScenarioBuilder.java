package com.lama.sc.execution;

import java.util.ArrayList;
import java.util.List;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

public class ScenarioBuilder implements IScenarioBuilder {

	private final static IScenarioBuilder INSTANCE = new ScenarioBuilder();
	
	private List<IScenarioEntry> entries;
	
	private ScenarioBuilder() {
		this.entries = new ArrayList<>();
	}
	
	public static IScenarioBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public IScenarioBuilder addEntry(String entryTitle, ISort sortAlgo, IData data) {
		entries.add(ScenarioEntry.of(entryTitle, sortAlgo, data));
		return this;
	}
	
	@Override
	public IScenario build(String applicationTitle, String scenarioTitle, int width, int height) {
		return new Scenario(applicationTitle, scenarioTitle, width, height, entries);
	}
	
	@Override
	public IScenarioBuilder clear() {
		entries = new ArrayList<>();
		return this;
	}

}
