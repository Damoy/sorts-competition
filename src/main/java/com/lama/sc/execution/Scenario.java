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
	public void execute(ScenarioConfig config) {
		clear();
		
		switch(config.getOutputMode()){
		case DETAILED:
			executeWithDetailedMode(config.getTimeGranularity());
			break;
		case TIME_ONLY:
			executeWithTimeOnlyMode(config.getTimeGranularity());
			break;
		}
	}
	
	private void executeWithDetailedMode(EnumTimeGranularity timeGranularity){
		output.append(">> Starting scenario \"");
		output.append(title);
		output.append("\" ...\n");
		entries.forEach(entry -> processWithDetailedMode(entry, timeGranularity));
		output.append(">> Scenario ended successfully.");
	}
	
	private void executeWithTimeOnlyMode(EnumTimeGranularity timeGranularity){
		output.append("Time granularity: ");
		output.append(EnumTimeGranularity.getString(timeGranularity));
		output.append("\n\n[");
		
		for(int i = 0; i < entries.size() - 1; ++i){
			IScenarioEntry entry = entries.get(i);
			ISort sortAlgo = entry.getSortAlgorithm();
			IData data = entry.getData().clone();
			
			Time.start();
			sortAlgo.process(data);
			Time.stop();
			
			output.append(Time.getComputedTime(timeGranularity));
			output.append(",");
		}
		
		IScenarioEntry entry = entries.get(entries.size() - 1);
		ISort sortAlgo = entry.getSortAlgorithm();
		IData data = entry.getData().clone();
		
		Time.start();
		sortAlgo.process(data);
		Time.stop();
		
		output.append(Time.getComputedTime(timeGranularity));
		output.append("]");
	}
	
	private void processWithDetailedMode(IScenarioEntry entry, EnumTimeGranularity timeGranularity) {
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
	public void display() {
		Utils.println(toString());
	}
	
	@Override
	public void output() {
		Utils.toFile(title, toString());
	}
	
	@Override
	public String toString() {
		return output.toString();
	}
	
}
