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
import com.lama.sc.utils.time.EnumTimeGranularity;
import com.lama.sc.visualisation.SortVisualizer;
import com.lama.sc.visualisation.ISortVisualiser;

public class Application {

	public static void main(String[] args) {
		launchRandomWithAll(100, EnumRandomGenerationBound.N).getChart().build().launch();
		// launchRandomWithAll(100, EnumRandomGenerationBound.N).getChart().build().launch();
		// launchFlatWithAll(100).getChart().build().launch();
	}
	
	private static void ignore(){
		ISortVisualiser chart = new SortVisualizer("Sorts competition", "Flat generation", 900, 500, "Data size", "Time");
		chart.addEntry("Insertion", 8, 23);
		chart.addEntry("Insertion", 9, 30);
		chart.addEntry("Merge", 8, 21);
		chart.addEntry("Merge", 9, 36);
		chart.build().launch();
	}
	
	private static IScenario launchRandomWithAll(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Random Generation (" + bound + ")";
		
		int size = 2 ^ 8;
		int min = -1000000;
		int max = 1000000;
		
		IData dataset2pow8 = generator.randomGeneration(size, min, max, bound);
		IData dataset2pow9 = generator.randomGeneration(size << 1, min, max, bound);
		IData dataset2pow10 = generator.randomGeneration(size << 2, min, max, bound);
		IData dataset2pow11 = generator.randomGeneration(size << 3, min, max, bound);
		IData dataset2pow12 = generator.randomGeneration(size << 4, min, max, bound);
		IData dataset2pow13 = generator.randomGeneration(size << 5, min, max, bound);
		IData dataset2pow14 = generator.randomGeneration(size << 6, min, max, bound);
		IData dataset2pow15 = generator.randomGeneration(size << 7, min, max, bound);
		IData dataset2pow16 = generator.randomGeneration(size << 8, min, max, bound);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Merge Sort", MergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Java Sort", JavaSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times));
		scenario1.output();
		return scenario1;
	}
	
	private static IScenario launchFlatWithAll(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "FlatGenerationAll";
		int flatValue = 42;
		int size = 2 ^ 8;
		
		IData dataset2pow8 = generator.flatGeneration(size, flatValue);
		IData dataset2pow9 = generator.flatGeneration(size << 1, flatValue);
		IData dataset2pow10 = generator.flatGeneration(size << 2, flatValue);
		IData dataset2pow11 = generator.flatGeneration(size << 3, flatValue);
		IData dataset2pow12 = generator.flatGeneration(size << 4, flatValue);
		IData dataset2pow13 = generator.flatGeneration(size << 5, flatValue);
		IData dataset2pow14 = generator.flatGeneration(size << 6, flatValue);
		IData dataset2pow15 = generator.flatGeneration(size << 7, flatValue);
		IData dataset2pow16 = generator.flatGeneration(size << 8, flatValue);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Merge Sort", MergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Java Sort", JavaSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times));
		scenario1.output();
		return scenario1;
	}
	
	private static void input(IScenarioBuilder on, String title, ISort sortAlgo, IData... datasets){
		for(IData data : datasets)
			on.addEntry(title, sortAlgo, data);
	}
	
	private static void smoothCheck() {
		IGenerator generator = Generator.getInstance();
		IData dataset1 = generator.randomGeneration(10, -10, 10, EnumRandomGenerationBound.N);
		IData dataset2 = generator.randomGeneration(10, -10, 20, EnumRandomGenerationBound.N);
		IData dataset3 = generator.randomGeneration(10, -10, 30, EnumRandomGenerationBound.N);
		IData dataset4 = generator.randomGeneration(100, -100, 100, EnumRandomGenerationBound.N);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		scenarioBuilder.addEntry("Smooth test 1", SmoothSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("Smooth test 2", SmoothSort.getInstance(), dataset2);
		scenarioBuilder.addEntry("Smooth test 3", SmoothSort.getInstance(), dataset3);
		scenarioBuilder.addEntry("Smooth test 4", SmoothSort.getInstance(), dataset4);
		IScenario scenarioSmooth = scenarioBuilder.build("Random generation Smooth", "Random generation Smooth", 900, 500);

		scenarioSmooth.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.DETAILED, 1));
		
		scenarioSmooth.output();
	}
}