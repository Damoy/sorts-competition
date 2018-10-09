package com.lama.sc.app;

import com.lama.sc.core.IterativeQuickSort;
import com.lama.sc.core.IterativeQuickSort3;
import com.lama.sc.core.IterativeQuickSort5;
import com.lama.sc.core.IterativeQuickSortRandom;
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
		iterativeQuick();
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
