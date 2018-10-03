package com.lama.sc.execution;

import java.util.List;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;
import com.lama.sc.rendering.Chart;
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class Scenario implements IScenario {

	private List<IScenarioEntry> entries;
	private StringBuilder output;
	private String applicationTitle;
	private String scenarioTitle;
	private Chart chart;
	private int chartWidth;
	private int chartHeight;
	
	public Scenario(String applicationTitle, String scenarioTitle, int chartWidth, int chartHeight, List<IScenarioEntry> entries) {
		this.applicationTitle = applicationTitle;
		this.scenarioTitle = scenarioTitle;
		this.entries = entries;
		this.chartWidth = chartWidth;
		this.chartHeight = chartHeight;
		this.output = new StringBuilder();
		this.chart = new Chart(applicationTitle, scenarioTitle, chartWidth, chartHeight);
	}

	@Override
	public void execute(ScenarioConfig config, int times) {
		clear();
		
		switch(config.getOutputMode()){
		case DETAILED:
			executeWithDetailedMode(config.getTimeGranularity(), times);
			break;
		case TIME_ONLY:
			executeWithTimeOnlyMode(config.getTimeGranularity(), times);
			break;
		}
	}
	
	// TODO
	@Deprecated
	private void executeWithDetailedMode(EnumTimeGranularity timeGranularity, int times){
		output.append(">> Starting scenario \"");
		output.append(scenarioTitle);
		output.append("\" ...\n");
		entries.forEach(entry -> processWithDetailedMode(entry, timeGranularity));
		output.append(">> Scenario ended successfully.");
	}
	
	private void executeWithTimeOnlyMode(EnumTimeGranularity timeGranularity, int times){
		output.append("Time granularity: ");
		output.append(EnumTimeGranularity.getString(timeGranularity));
		output.append("\n\n[");
		
		for(int i = 0; i < entries.size() - 1; ++i){
			IScenarioEntry entry = entries.get(i);
			ISort sortAlgo = entry.getSortAlgorithm();
			IData data = entry.getData().clone();
			
			long start = System.nanoTime();
			sortAlgo.process(data);
			long computedTime = System.nanoTime() - start;
			
			// here constructs step by step the chart
			chart.addEntry(sortAlgo.getTitle(), Utils.log2(data.getLength()), Utils.log2(computedTime));
			output.append(computedTime);
			output.append(",");
		}
		
		IScenarioEntry entry = entries.get(entries.size() - 1);
		ISort sortAlgo = entry.getSortAlgorithm();
		IData data = entry.getData().clone();
		
		long start = System.nanoTime();
		sortAlgo.process(data);
		long computedTime = System.nanoTime() - start;
		
		output.append(computedTime);
		output.append("]");
	}
	
	private void processWithDetailedMode(IScenarioEntry entry, EnumTimeGranularity timeGranularity) {
		ISort sortAlgo = entry.getSortAlgorithm();
		IData data = entry.getData().clone();
		String title = entry.getTitle();
		
		output.append("--> ");
		output.append(title);
		output.append("\n");
		
		long start = System.nanoTime();
		sortAlgo.process(data);
		long computedTime = System.nanoTime() - start;
		
		chart.addEntry(sortAlgo.getTitle(), Utils.log2(data.getLength()),
				Utils.log2(computedTime));
		output.append(data.toString());
		output.append("\n");
		output.append(computedTime);
		output.append("\n");
	}
	
	private void clear() {
		output.setLength(0);
		chart = new Chart(applicationTitle, scenarioTitle, chartWidth, chartHeight);
	}
	
	@Override
	public void display() {
		Utils.println(toString());
	}
	
	@Override
	public void output() {
		Utils.toFile(scenarioTitle, toString());
	}
	
	@Override
	public String toString() {
		return output.toString();
	}

	@Override
	public Chart getChart() {
		return chart;
	}
	
}
