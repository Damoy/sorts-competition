package com.lama.sc.execution;

import com.lama.sc.utils.time.EnumTimeGranularity;

public final class ScenarioConfig {
	
	private EnumTimeGranularity timeGranularity;
	private EnumScenarioOutputMode outputMode;
	
	private ScenarioConfig(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode){
		this.timeGranularity = timeGranularity;
		this.outputMode = outputMode;
	}
	
	public static ScenarioConfig of(EnumTimeGranularity timeGranularity, EnumScenarioOutputMode outputMode){
		return new ScenarioConfig(timeGranularity, outputMode);
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

}
