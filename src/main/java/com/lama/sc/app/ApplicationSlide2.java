package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.IterativeQuickSort;
import com.lama.sc.core.FastMergeSort;
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
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;

public class ApplicationSlide2 {

	public static void main(String[] args) {
		long start  = System.nanoTime();
		
		// 1. Slide 2 - sorted
		//optimumVsNonOptimumSortSorted(100);
		
		// 2. Slide 2 - Reversed
		//optimumVsNonOptimumSortReversed(100);
		
		// 3. Slide 3 - Flat
		//optimumVsNonOptimumSortFlat(100);
		
		//optimumVsNonOptimumSortRandom(100, EnumRandomGenerationBound.N);

		//IJustWantToTestMySort();
		
		
		Utils.println("Total: " + (System.nanoTime() - start) / 1000000000 + "s");
	}
	
	private static void IJustWantToTestMySort() {
		
		IGenerator generator = Generator.getInstance();
		IData dataset1 = generator.randomGeneration(10, -10, 10, EnumRandomGenerationBound.N);
		IData dataset2 = generator.randomGeneration(10, -10, 20, EnumRandomGenerationBound.N);
		IData dataset3 = generator.randomGeneration(10, -10, 30, EnumRandomGenerationBound.N);
		IData dataset4 = generator.randomGeneration(100, -100, 100, EnumRandomGenerationBound.N);
		IData dataset5 = generator.randomGeneration((int) Math.pow(2,13), -100, 100, EnumRandomGenerationBound.N);


		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		scenarioBuilder.addEntry("QuickIterative test 1", IterativeQuickSort.getInstance(), dataset1);
		scenarioBuilder.addEntry("QuickIterative test 2", IterativeQuickSort.getInstance(), dataset2);
		scenarioBuilder.addEntry("QuickIterative test 3", IterativeQuickSort.getInstance(), dataset3);
		scenarioBuilder.addEntry("QuickIterative test 4", IterativeQuickSort.getInstance(), dataset4);
		scenarioBuilder.addEntry("QuickIterative test 5", IterativeQuickSort.getInstance(), dataset5);
		IScenario scenarioSmooth = scenarioBuilder.build("TestQuickSortIterative", "QUICK", 900, 500);

		scenarioSmooth.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.DETAILED, 50, "Data Size", "Time log2(mics)"));
		
		scenarioSmooth.output();
		
	} 

	
	private static void optimumVsNonOptimumSortFlat(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - flat - pow"; // (" + bound + ")";
		
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
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortReversed(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - reversed - pow";
		
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
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortSorted(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - sorted - pow";
		
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
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortRandom(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - Random - pow";
		
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
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
				dataset2pow13, dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow5, dataset2pow6,
				dataset2pow7, dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12);
		
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