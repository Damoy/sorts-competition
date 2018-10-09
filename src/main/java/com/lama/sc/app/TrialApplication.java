package com.lama.sc.app;

import com.lama.sc.core.IterativeQuickSort;
import com.lama.sc.core.IterativeQuickSort3;
import com.lama.sc.core.IterativeQuickSort5;
import com.lama.sc.core.IterativeQuickSortRandom;
import com.lama.sc.core.IterativeQuickSortSwap;
import com.lama.sc.execution.EnumScenarioOutputMode;
import com.lama.sc.execution.IScenario;
import com.lama.sc.execution.IScenarioBuilder;
import com.lama.sc.execution.ScenarioBuilder;
import com.lama.sc.execution.ScenarioConfig;
import com.lama.sc.generator.EnumRandomGenerationBound;
import com.lama.sc.generator.Generator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class TrialApplication {

	public static void main(String[] args) {
		// iterativeQuick();
		iterativeQuickSwap();
	}
	
	private static void iterativeQuickSwap() {
		IData data = Generator.getInstance().randomGeneration((int) Math.pow(2, 13), -1000, 1000, EnumRandomGenerationBound.N);
		IScenarioBuilder builder = ScenarioBuilder.getInstance();
		
		IData data2 = Generator.getInstance().randomGeneration((int) Math.pow(2, 9), -1000, 1000, EnumRandomGenerationBound.N);
		
		builder.addEntry("IQS", IterativeQuickSort.getInstance(), data);
		builder.addEntry("IQSS", IterativeQuickSortSwap.getInstance(), data);
		
		builder.addEntry("IQS2", IterativeQuickSort.getInstance(), data2);
		builder.addEntry("IQSS2", IterativeQuickSortSwap.getInstance(), data2);
		
		IScenario scenario = builder.build("", "", 900, 500);
		scenario.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.DETAILED, 50, null, null));
		scenario.display();
	}
	
	private static void iterativeQuick() {
		IData data = Generator.getInstance().randomGeneration((int) Math.pow(2, 10), -1000, 1000, EnumRandomGenerationBound.N);
		IScenarioBuilder builder = ScenarioBuilder.getInstance();
		
		builder.addEntry("IQS3", IterativeQuickSort.getInstance(), data);
		builder.addEntry("IQS3", IterativeQuickSort3.getInstance(), data);
		builder.addEntry("IQS3", IterativeQuickSort5.getInstance(), data);
		builder.addEntry("IQS3", IterativeQuickSortRandom.getInstance(), data);
		
		IScenario scenario = builder.build("", "", 900, 500);
		scenario.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.DETAILED, 100, null, null));
		scenario.display();
	}
}
