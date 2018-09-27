package com.lama.sc.execution;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;

public interface IScenarioEntry {

	public IData getData();
	public ISort getSortAlgorithm(); 
	public String getTitle();
	
}
