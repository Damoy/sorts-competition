package com.lama.sc.execution;

import java.util.List;

import com.lama.sc.core.ISort;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;
import com.lama.sc.utils.time.Time;
import com.lama.sc.visualisation.SortVisualizer;

public class Scenario implements IScenario {

	private List<IScenarioEntry> entries;
	private StringBuilder output;
	private String applicationTitle;
	private String scenarioTitle;
	private SortVisualizer visualiser;
	private int chartWidth;
	private int chartHeight;
	
	public Scenario(String applicationTitle, String scenarioTitle, int chartWidth, int chartHeight, List<IScenarioEntry> entries) {
		this.applicationTitle = applicationTitle;
		this.scenarioTitle = scenarioTitle;
		this.entries = entries;
		this.chartWidth = chartWidth;
		this.chartHeight = chartHeight;
		this.output = new StringBuilder();
		// this.visualiser = new SortVisualizer(applicationTitle, scenarioTitle, chartWidth, chartHeight, null, null);
	}

	@Override
	public void execute(ScenarioConfig config) {
		clear();
		
		switch(config.getOutputMode()){
		case DETAILED:
			executeWithDetailedMode(config);
			break;
		case TIME_ONLY:
			executeWithTimeOnlyMode(config);
			break;
		}
	}
	
	private void executeWithDetailedMode(ScenarioConfig config) {
		EnumTimeGranularity timeGranularity = config.getTimeGranularity();
		int times = config.getTimes();
		
		// visualiser.setXAxisTitle(config.getVisualiserXLabel());
		// visualiser.setYAxisTitle(config.getVisualiserYLabel());
		
		output.append(">> Starting scenario \"");
		output.append(scenarioTitle);
		output.append("\" ...\n");
		
		ISort sortAlgo;
		IData data ;
		long averageExecutionTime;

		for(IScenarioEntry entry : entries) {
			sortAlgo = entry.getSortAlgorithm();
			data = entry.getData();
			averageExecutionTime = 0L;
			
			output.append("--> ");
			output.append(sortAlgo.getTitle());
			output.append("\n");
			
			for(int t = 0; t < times; ++t) {
				data = data.clone();
				long start = System.nanoTime();
				sortAlgo.process(data);
				// updating the entry result
				averageExecutionTime += (System.nanoTime() - start);
			}
			
			// In order to obtain nanoseconds, microseconds or milliseconds (logarithmic)
			averageExecutionTime = Utils.log2(Time.getComputedTime(averageExecutionTime / times, timeGranularity));
			// averageExecutionTime = Time.getComputedTime(averageExecutionTime / times, timeGranularity);
			
			output.append(data.toString());
			output.append("\nAverage execution time: ");
			output.append(averageExecutionTime);
			output.append(" ");
			output.append(EnumTimeGranularity.getString(timeGranularity));
			output.append("\n");
			
			// updating the visualizer
			//visualiser.addEntry(sortAlgo.getTitle(), Utils.log2(data.getLength()), averageExecutionTime);
		}
		
		output.append(">> Scenario ended successfully.");
	}
	
	private void executeWithTimeOnlyMode(ScenarioConfig config) {
		// visualiser.setXAxisTitle(config.getVisualiserXLabel());
		// visualiser.setYAxisTitle(config.getVisualiserYLabel());
		
		EnumTimeGranularity timeGranularity = config.getTimeGranularity();
		int times = config.getTimes();
		
		output.append("Time granularity: ");
		output.append(EnumTimeGranularity.getString(timeGranularity));
		output.append("\n\n[");
		
		IScenarioEntry entry;
		ISort sortAlgo;
		IData data;
		long averageExecutionTime;
		
		for(int i = 0; i < entries.size() - 1; ++i){
			entry = entries.get(i);
			sortAlgo = entry.getSortAlgorithm();
			
			output.append(sortAlgo.getTitle());
			output.append(":\n");
			
			data = entry.getData();
			averageExecutionTime = 0L;
			
			for(int t = 0; t < times; ++t) {
				data = data.clone();
				long start = System.nanoTime();
				sortAlgo.process(data);
				// updating the entry result
				averageExecutionTime += (System.nanoTime() - start);
			}
			
			averageExecutionTime = Utils.log2(Time.getComputedTime(averageExecutionTime / times, timeGranularity));
			// averageExecutionTime = Time.getComputedTime(averageExecutionTime / times, timeGranularity);
			
			// output.append(data.toString());
			// output.append("\n");
			// output.append(averageExecutionTime);
			// output.append("\n");
			
			// updating the visualizer
			//visualiser.addEntry(sortAlgo.getTitle(), Utils.log2(data.getLength()), averageExecutionTime);
			output.append(averageExecutionTime);
			output.append(",");
		}
		
		// last iteration
		entry = entries.get(entries.size() - 1);
		sortAlgo = entry.getSortAlgorithm();
		data = entry.getData();
		averageExecutionTime = 0L;
		
		for(int t = 0; t < times; ++t) {
			long start = System.nanoTime();
			sortAlgo.process(data.clone());
			// updating the entry result
			averageExecutionTime += (System.nanoTime() - start);
		}
		
		averageExecutionTime = Utils.log2(Time.getComputedTime(averageExecutionTime / times, timeGranularity));
		
		// output.append(data.toString());
		// output.append("\n");
		// output.append(averageExecutionTime);
		// output.append("\n");
		
		// updating the visualizer
		// visualiser.addEntry(sortAlgo.getTitle(), Utils.log2(data.getLength()), averageExecutionTime);
		output.append(averageExecutionTime);
		output.append("]\n");
	}
	
	private void clear() {
		output.setLength(0);
		visualiser = new SortVisualizer(applicationTitle, scenarioTitle, chartWidth, chartHeight, null, null);
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
	public SortVisualizer getVisualizer() {
		return visualiser;
	}
	
}
