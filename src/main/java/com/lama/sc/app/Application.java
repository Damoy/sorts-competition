package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.JavaSort;
import com.lama.sc.core.MergeSort;
import com.lama.sc.core.QuickSort;
import com.lama.sc.core.SmoothSort;
import com.lama.sc.execution.EnumScenarioOutputMode;
import com.lama.sc.execution.IScenario;
import com.lama.sc.execution.IScenarioBuilder;
import com.lama.sc.execution.ScenarioBuilder;
import com.lama.sc.execution.ScenarioConfig;
import com.lama.sc.generator.EnumRandomGenerationBound;
import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Chart;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class Application {

	public static void main(String[] args) {

		flatGeneration("Insertion Sort", InsertionSort.getInstance());
		flatGeneration("Heap Sort", HeapSort.getInstance());
		flatGeneration("Java Sort", JavaSort.getInstance());
		flatGeneration("Merge Sort", MergeSort.getInstance());
		flatGeneration("Quick Sort", QuickSort.getInstance()); 
		// flatGeneration("Smooth Sort", SmoothSort.getInstance());



	}

	private static void flatGeneration(String scenarioTitle, ISort sortAlgo){
		IGenerator generator = Generator.getInstance();

		int flatValue = 42;
		int size = 2 ^ 4;

		IData dataset2pow4 = generator.flatGeneration(size, flatValue);
		IData dataset2pow5 = generator.flatGeneration(size << 1, flatValue);
		IData dataset2pow6 = generator.flatGeneration(size << 2, flatValue);
		IData dataset2pow7 = generator.flatGeneration(size << 3, flatValue);
		IData dataset2pow8 = generator.flatGeneration(size << 4, flatValue);

		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		scenarioBuilder.addEntry(scenarioTitle, sortAlgo, dataset2pow4);
		scenarioBuilder.addEntry(scenarioTitle, sortAlgo, dataset2pow5);
		scenarioBuilder.addEntry(scenarioTitle, sortAlgo, dataset2pow6);
		scenarioBuilder.addEntry(scenarioTitle, sortAlgo, dataset2pow7);
		scenarioBuilder.addEntry(scenarioTitle, sortAlgo, dataset2pow8);

		IScenario scenario1 = scenarioBuilder.build("Flat" + scenarioTitle);

		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY));
		scenario1.output();
		scenarioBuilder.clear();
	}

	@SuppressWarnings("unused")
	private static void tutorial() {
		// Data generator used to generate data to process
		IGenerator generator = Generator.getInstance();
		// One example of data set
		IData dataset1 = generator.randomGeneration(10, -10, 10, EnumRandomGenerationBound.N);

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
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY));

		// Display results to console
		scenario1.output();
	}
}