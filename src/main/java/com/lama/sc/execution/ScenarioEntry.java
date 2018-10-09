package com.lama.sc.execution;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

public class ScenarioEntry implements IScenarioEntry {

	private String title;
	private ISort sortAlgo;
	private IData data;
	
	private ScenarioEntry(String title, ISort sortAlgo, IData data) {
		this.title = title;
		this.sortAlgo = sortAlgo;
		this.data = data;
	}

	public static IScenarioEntry of(String title, ISort sortAlgo, IData data) {
		return new ScenarioEntry(title, sortAlgo, data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IData getData() {
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISort getSortAlgorithm() {
		return sortAlgo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle() {
		return title;
	}

}
