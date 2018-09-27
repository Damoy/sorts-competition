package com.lama.sc.execution;

import java.util.List;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;
import com.lama.sc.utils.time.Time;

public class Scenario implements IScenario {

	private List<IScenarioEntry> entries;
	private StringBuilder output;
	private String title;
	
	public Scenario(String title, List<IScenarioEntry> entries) {
		this.title = title;
		this.entries = entries;
		this.output = new StringBuilder();
	}

	@Override
	public void execute(EnumTimeGranularity timeGranularity) {
		clear();
		output.append(">> Starting scenario \"");
		output.append(title);
		output.append("\" ...\n");
		entries.forEach(entry -> process(entry, timeGranularity));
		output.append(">> Scenario ended successfully.");
	}
	
	private void process(IScenarioEntry entry, EnumTimeGranularity timeGranularity) {
		ISort sortAlgo = entry.getSortAlgorithm();
		IData data = entry.getData().clone();
		String title = entry.getTitle();
		
		output.append("--> ");
		output.append(title);
		output.append("\n");
		
		Time.start();
		sortAlgo.process(data);
		Time.stop();
		
		output.append(data.toString());
		output.append("\n");
		output.append(Time.output(timeGranularity));
		output.append("\n");
	}
	
	private void clear() {
		output.setLength(0);
	}
	
	@Override
	public void output() {
		Utils.println(toString());
	}
	
	@Override
	public String toString() {
		return output.toString();
	}
	
}
