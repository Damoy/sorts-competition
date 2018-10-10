package com.lama.sc.app;

import com.lama.sc.core.FastMergeSort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.IterativeQuickSort;
import com.lama.sc.core.JavaSort;
import com.lama.sc.core.SmoothSort;
import com.lama.sc.execution.EnumScenarioOutputMode;
import com.lama.sc.execution.IScenario;
import com.lama.sc.execution.IScenarioBuilder;
import com.lama.sc.execution.ScenarioBuilder;
import com.lama.sc.execution.ScenarioConfig;
import com.lama.sc.generator.EnumRandomGenerationBound;
import com.lama.sc.generator.Generator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class TutorialApp {
	
	public static void main(String[] args) {
		tutorial1();
	}

	private static void tutorial1() {
		// To generate random data
		IData data = Generator.getInstance().randomGeneration((int) Math.pow(2, 12), -1000, 1000, EnumRandomGenerationBound.N);
		// To construct scenarios
		IScenarioBuilder builder = ScenarioBuilder.getInstance();
		
		// Add scenario entries
		// Will execute the following algorithms on data
		builder.addEntry("Insertion", InsertionSort.getInstance(), data);
		builder.addEntry("Merge", FastMergeSort.getInstance(), data);
		builder.addEntry("ItQuick", IterativeQuickSort.getInstance(), data);
		builder.addEntry("Java", JavaSort.getInstance(), data);
		builder.addEntry("Smooth", SmoothSort.getInstance(), data);
		
		// Build the scenario
		IScenario scenario = builder.build("ApplicationTitle", "ScenarioTitle", 900, 500);
		
		// Execute it
		scenario.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.DETAILED, 50, null, null));
		
		// Console output
		scenario.display();
		
		// File output
		scenario.output();
		
		// Launch JfreeChart visualization
		// scenario.getVisualizer().build().launch();
	}
	
}
