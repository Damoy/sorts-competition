package com.lama.sc.execution;

import com.lama.sc.utils.time.EnumTimeGranularity;

public final class ScenarioConfig {
	
	private EnumTimeGranularity timeGranularity;
	private EnumScenarioOutputMode outputMode;
	private int times;
	private String visualiserXLabel;
	private String visualiserYLabel;
	
	private ScenarioConfig(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode, int times,
			String visualiserXLabel, String visualiserYLabel) {
		this.timeGranularity = timeGranularity;
		this.outputMode = outputMode;
		this.times = times;
		this.visualiserXLabel = visualiserXLabel;
		this.visualiserYLabel = visualiserYLabel;
	}

	public static ScenarioConfig of(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode, int times,
			String visualiserXLabel, String visualiserYLabel) {
		return new ScenarioConfig(timeGranularity, outputMode, times, visualiserXLabel, visualiserYLabel);
	}

	public EnumTimeGranularity getTimeGranularity() {
		return timeGranularity;
	}

	public void setTimeGranularity(EnumTimeGranularity timeGranularity) {
		this.timeGranularity = timeGranularity;
	}

	public EnumScenarioOutputMode getOutputMode() {
		return outputMode;
	}

	public void setOutputMode(EnumScenarioOutputMode outputMode) {
		this.outputMode = outputMode;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getVisualiserXLabel() {
		return visualiserXLabel;
	}

	public void setVisualiserXLabel(String visualiserXLabel) {
		this.visualiserXLabel = visualiserXLabel;
	}

	public String getVisualiserYLabel() {
		return visualiserYLabel;
	}

	public void setVisualiserYLabel(String visualiserYLabel) {
		this.visualiserYLabel = visualiserYLabel;
	}

}
