package com.lama.sc.execution;

import com.lama.sc.utils.time.EnumTimeGranularity;

public final class ScenarioConfig {
	
	private EnumTimeGranularity timeGranularity;
	private EnumScenarioOutputMode outputMode;
	private int times;
	
	private ScenarioConfig(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode, int times){
		this.timeGranularity = timeGranularity;
		this.outputMode = outputMode;
		this.times = times;
	}
	
	public static ScenarioConfig of(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode, int times){
		return new ScenarioConfig(timeGranularity, outputMode, times);
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

}
