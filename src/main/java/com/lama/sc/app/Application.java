package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.JavaSort;
import com.lama.sc.core.MergeSort;
import com.lama.sc.core.QuickSort;
import com.lama.sc.execution.IScenario;
import com.lama.sc.execution.IScenarioBuilder;
import com.lama.sc.execution.ScenarioBuilder;
import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class Application {

	public static void main(String[] args) {
		tutorial();
	}
	
	private static void tutorial() {
		// Data generator used to generate data to process
		IGenerator generator = Generator.getInstance();
		// One example of data set
		IData dataset1 = generator.randomGeneration(10, -10, 10);
		
		// Scenario builder used to build scenarios
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		// Creating a scenario
		scenarioBuilder.addEntry("Insertion sort", InsertionSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("Merge sort", MergeSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("Quick sort", QuickSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("Heap sort", HeapSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("Java sort", JavaSort.getInstance(), dataset1);
		IScenario scenario1 = scenarioBuilder.build("Random generation");
		
		// Executes it 
		scenario1.execute(EnumTimeGranularity.MICROSECONDS);
		
		// Display results to console
		scenario1.output();
	}
}