package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;

import com.lama.sc.core.FastMergeSort;
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
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class ApplicationSlide3 {

	public static void main(String[] args) {
		long start  = System.nanoTime();
		
		// 1. Slide 3 - random
		bestOptimumRandomSorts(50, EnumRandomGenerationBound.N);
		
		// 2. Slide 3 - sorted
		// bestOptimumSortedSorts(50);
		
		// 3. Slide 3 - Reversed
		// bestOptimumReversedSorts(50);
		
		// 4. Slide 4 - Flat
		// bestOptimumFlatSorts(50);
		
		Utils.println("Total: " + (System.nanoTime() - start) / 1000000000 + "s");
	}
	
	private static void bestOptimumFlatSorts(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing best optimum Sorts - flat - pow";
		
		int size = (int) Math.pow(2, 5);
		int v = 42;
		
		IData dataset2pow5 = generator.flatGeneration(size, v);
		IData dataset2pow6 = generator.flatGeneration(size << 1, v);
		IData dataset2pow7 = generator.flatGeneration(size << 2, v);
		IData dataset2pow8 = generator.flatGeneration(size << 3, v);
		IData dataset2pow9 = generator.flatGeneration(size << 4, v);
		IData dataset2pow10 = generator.flatGeneration(size << 5, v);
		IData dataset2pow11 = generator.flatGeneration(size << 6, v);
		IData dataset2pow12 = generator.flatGeneration(size << 7, v);
		IData dataset2pow13 = generator.flatGeneration(size << 8, v);
		IData dataset2pow14 = generator.flatGeneration(size << 9, v);
		IData dataset2pow15 = generator.flatGeneration(size << 10, v);
		IData dataset2pow16 = generator.flatGeneration(size << 11, v);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void bestOptimumReversedSorts(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing best optimum Sorts - reversed - pow";
		
		int size = (int) Math.pow(2, 5);
		int min = -1000;
		int max = 1000;
		
		IData dataset2pow5 = generator.reversedGeneration(size, min, max);
		IData dataset2pow6 = generator.reversedGeneration(size << 1, min, max);
		IData dataset2pow7 = generator.reversedGeneration(size << 2, min, max);
		IData dataset2pow8 = generator.reversedGeneration(size << 3, min, max);
		IData dataset2pow9 = generator.reversedGeneration(size << 4, min, max);
		IData dataset2pow10 = generator.reversedGeneration(size << 5, min, max);
		IData dataset2pow11 = generator.reversedGeneration(size << 6, min, max);
		IData dataset2pow12 = generator.reversedGeneration(size << 7, min, max);
		IData dataset2pow13 = generator.reversedGeneration(size << 8, min, max);
		IData dataset2pow14 = generator.reversedGeneration(size << 9, min, max);
		IData dataset2pow15 = generator.reversedGeneration(size << 10, min, max);
		IData dataset2pow16 = generator.reversedGeneration(size << 11, min, max);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void bestOptimumSortedSorts(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing best optimum Sorts - sorted - pow";
		
		int size = (int) Math.pow(2, 5);
		int min = -1000;
		int max = 1000;
		
		IData dataset2pow5 = generator.sortedGeneration(size, min, max);
		IData dataset2pow6 = generator.sortedGeneration(size << 1, min, max);
		IData dataset2pow7 = generator.sortedGeneration(size << 2, min, max);
		IData dataset2pow8 = generator.sortedGeneration(size << 3, min, max);
		IData dataset2pow9 = generator.sortedGeneration(size << 4, min, max);
		IData dataset2pow10 = generator.sortedGeneration(size << 5, min, max);
		IData dataset2pow11 = generator.sortedGeneration(size << 6, min, max);
		IData dataset2pow12 = generator.sortedGeneration(size << 7, min, max);
		IData dataset2pow13 = generator.sortedGeneration(size << 8, min, max);
		IData dataset2pow14 = generator.sortedGeneration(size << 9, min, max);
		IData dataset2pow15 = generator.sortedGeneration(size << 10, min, max);
		IData dataset2pow16 = generator.sortedGeneration(size << 11, min, max);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void bestOptimumRandomSorts(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing best optimum Sorts - random - pow";
		
		int size = (int) Math.pow(2, 5);
		int min = -1000;
		int max = 1000;
		
		IData dataset2pow5 = generator.randomGeneration(size, min, max, bound);
		IData dataset2pow6 = generator.randomGeneration(size << 1, min, max, bound);
		IData dataset2pow7 = generator.randomGeneration(size << 2, min, max, bound);
		IData dataset2pow8 = generator.randomGeneration(size << 3, min, max, bound);
		IData dataset2pow9 = generator.randomGeneration(size << 4, min, max, bound);
		IData dataset2pow10 = generator.randomGeneration(size << 5, min, max, bound);
		IData dataset2pow11 = generator.randomGeneration(size << 6, min, max, bound);
		IData dataset2pow12 = generator.randomGeneration(size << 7, min, max, bound);
		IData dataset2pow13 = generator.randomGeneration(size << 8, min, max, bound);
		IData dataset2pow14 = generator.randomGeneration(size << 9, min, max, bound);
		IData dataset2pow15 = generator.randomGeneration(size << 10, min, max, bound);
		IData dataset2pow16 = generator.randomGeneration(size << 11, min, max, bound);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
				dataset2pow13, dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
				dataset2pow13, dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
				dataset2pow13, dataset2pow14, dataset2pow15, dataset2pow16);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void input(IScenarioBuilder on, String title, ISort sortAlgo, IData... datasets){
		for(IData data : datasets)
			on.addEntry(title, sortAlgo, data);
	}
	
}