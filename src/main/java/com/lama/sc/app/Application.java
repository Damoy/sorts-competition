package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.MergeSort;
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
import com.lama.sc.utils.time.EnumTimeGranularity;

public class Application {

	public static void main(String[] args) {
		// optimumVsNonOptimumSortFlat(100);
		// optimumVsNonOptimumSortReversed(100);
		// optimumVsNonOptimumSortSorted(100);
		// optimumVsNonOptimumSortRandom(100);
	}
	
	private static void optimumVsNonOptimumSortFlat(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - flat"; // (" + bound + ")";
		
		int size = 2 ^ 8;
		int v = 42;
		
		IData dataset2pow8 = generator.flatGeneration(size, v);
		IData dataset2pow9 = generator.flatGeneration(size << 1, v);
		IData dataset2pow10 = generator.flatGeneration(size << 2, v);
		IData dataset2pow11 = generator.flatGeneration(size << 3, v);
		IData dataset2pow12 = generator.flatGeneration(size << 4, v);
		IData dataset2pow13 = generator.flatGeneration(size << 5, v);
		IData dataset2pow14 = generator.flatGeneration(size << 6, v);
		IData dataset2pow15 = generator.flatGeneration(size << 7, v);
		IData dataset2pow16 = generator.flatGeneration(size << 8, v);
		IData dataset2pow17 = generator.flatGeneration(size << 9, v);
		IData dataset2pow18 = generator.flatGeneration(size << 10, v);
		IData dataset2pow19 = generator.flatGeneration(size << 11, v);
		IData dataset2pow20 = generator.flatGeneration(size << 12, v);
		IData dataset2pow21 = generator.flatGeneration(size << 13, v);
		IData dataset2pow22 = generator.flatGeneration(size << 14, v);
		IData dataset2pow23 = generator.flatGeneration(size << 15, v);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortReversed(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - reversed"; // (" + bound + ")";
		
		int size = 2 ^ 8;
		int min = -1000000;
		int max = 1000000;
		
		IData dataset2pow8 = generator.reversedGeneration(size, min, max);
		IData dataset2pow9 = generator.reversedGeneration(size << 1, min, max);
		IData dataset2pow10 = generator.reversedGeneration(size << 2, min, max);
		IData dataset2pow11 = generator.reversedGeneration(size << 3, min, max);
		IData dataset2pow12 = generator.reversedGeneration(size << 4, min, max);
		IData dataset2pow13 = generator.reversedGeneration(size << 5, min, max);
		IData dataset2pow14 = generator.reversedGeneration(size << 6, min, max);
		IData dataset2pow15 = generator.reversedGeneration(size << 7, min, max);
		IData dataset2pow16 = generator.reversedGeneration(size << 8, min, max);
		IData dataset2pow17 = generator.reversedGeneration(size << 9, min, max);
		IData dataset2pow18 = generator.reversedGeneration(size << 10, min, max);
		IData dataset2pow19 = generator.reversedGeneration(size << 11, min, max);
		IData dataset2pow20 = generator.reversedGeneration(size << 12, min, max);
		IData dataset2pow21 = generator.reversedGeneration(size << 13, min, max);
		IData dataset2pow22 = generator.reversedGeneration(size << 14, min, max);
		IData dataset2pow23 = generator.reversedGeneration(size << 15, min, max);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortSorted(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts - sorted"; // (" + bound + ")";
		
		int size = 2 ^ 8;
		int min = -1000000;
		int max = 1000000;
		
		IData dataset2pow8 = generator.sortedGeneration(size, min, max);
		IData dataset2pow9 = generator.sortedGeneration(size << 1, min, max);
		IData dataset2pow10 = generator.sortedGeneration(size << 2, min, max);
		IData dataset2pow11 = generator.sortedGeneration(size << 3, min, max);
		IData dataset2pow12 = generator.sortedGeneration(size << 4, min, max);
		IData dataset2pow13 = generator.sortedGeneration(size << 5, min, max);
		IData dataset2pow14 = generator.sortedGeneration(size << 6, min, max);
		IData dataset2pow15 = generator.sortedGeneration(size << 7, min, max);
		IData dataset2pow16 = generator.sortedGeneration(size << 8, min, max);
		IData dataset2pow17 = generator.sortedGeneration(size << 9, min, max);
		IData dataset2pow18 = generator.sortedGeneration(size << 10, min, max);
		IData dataset2pow19 = generator.sortedGeneration(size << 11, min, max);
		IData dataset2pow20 = generator.sortedGeneration(size << 12, min, max);
		IData dataset2pow21 = generator.sortedGeneration(size << 13, min, max);
		IData dataset2pow22 = generator.sortedGeneration(size << 14, min, max);
		IData dataset2pow23 = generator.sortedGeneration(size << 15, min, max);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
	}
	
	private static void optimumVsNonOptimumSortRandom(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum and Non-optimum Sorts"; // (" + bound + ")";
		
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
		IData dataset2pow17 = generator.randomGeneration(size << 9, min, max, bound);
		IData dataset2pow18 = generator.randomGeneration(size << 10, min, max, bound);
		IData dataset2pow19 = generator.randomGeneration(size << 11, min, max, bound);
		IData dataset2pow20 = generator.randomGeneration(size << 12, min, max, bound);
		IData dataset2pow21 = generator.randomGeneration(size << 13, min, max, bound);
		IData dataset2pow22 = generator.randomGeneration(size << 14, min, max, bound);
		IData dataset2pow23 = generator.randomGeneration(size << 15, min, max, bound);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", FastMergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Insertion Sort", InsertionSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Quick Sort", QuickSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		// scenario1.display();
		scenario1.output();
		//scenario1.getVisualizer().build().launch();
	}
	
	private static void compareMerges(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Merge Sorts"; // (" + bound + ")";
		
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
		IData dataset2pow17 = generator.randomGeneration(size << 9, min, max, bound);
		IData dataset2pow18 = generator.randomGeneration(size << 10, min, max, bound);
		IData dataset2pow19 = generator.randomGeneration(size << 11, min, max, bound);
		IData dataset2pow20 = generator.randomGeneration(size << 12, min, max, bound);
		IData dataset2pow21 = generator.randomGeneration(size << 13, min, max, bound);
		IData dataset2pow22 = generator.randomGeneration(size << 14, min, max, bound);
		IData dataset2pow23 = generator.randomGeneration(size << 15, min, max, bound);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", MergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Fast merge sort", FastMergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time "));
		scenario1.output();
		scenario1.getVisualizer().build().launch();
	}
	
	private static void bestOptimumSortRandom(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum Sorts"; // (" + bound + ")";
		
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
		IData dataset2pow17 = generator.randomGeneration(size << 9, min, max, bound);
		IData dataset2pow18 = generator.randomGeneration(size << 10, min, max, bound);
		IData dataset2pow19 = generator.randomGeneration(size << 11, min, max, bound);
		IData dataset2pow20 = generator.randomGeneration(size << 12, min, max, bound);
		IData dataset2pow21 = generator.randomGeneration(size << 13, min, max, bound);
		IData dataset2pow22 = generator.randomGeneration(size << 14, min, max, bound);
		IData dataset2pow23 = generator.randomGeneration(size << 15, min, max, bound);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", MergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
		scenario1.getVisualizer().build().launch();
	}
	
	private static void bestOptimumSortFlat(int times){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Optimum Sorts";
		
		int size = 2 ^ 8;
		int v = 753;
		
		IData dataset2pow8 = generator.flatGeneration(size, v);
		IData dataset2pow9 = generator.flatGeneration(size << 1, v);
		IData dataset2pow10 = generator.flatGeneration(size << 2, v);
		IData dataset2pow11 = generator.flatGeneration(size << 3, v);
		IData dataset2pow12 = generator.flatGeneration(size << 4, v);
		IData dataset2pow13 = generator.flatGeneration(size << 5, v);
		IData dataset2pow14 = generator.flatGeneration(size << 6, v);
		IData dataset2pow15 = generator.flatGeneration(size << 7, v);
		IData dataset2pow16 = generator.flatGeneration(size << 8, v);
		IData dataset2pow17 = generator.flatGeneration(size << 9, v);
		IData dataset2pow18 = generator.flatGeneration(size << 10, v);
		IData dataset2pow19 = generator.flatGeneration(size << 11, v);
		IData dataset2pow20 = generator.flatGeneration(size << 12, v);
		IData dataset2pow21 = generator.flatGeneration(size << 13, v);
		IData dataset2pow22 = generator.flatGeneration(size << 14, v);
		IData dataset2pow23 = generator.flatGeneration(size << 15, v);
		
		IScenarioBuilder scenarioBuilder = ScenarioBuilder.getInstance();

		input(scenarioBuilder, "Merge Sort", MergeSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Heap Sort", HeapSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(), dataset2pow8,
				dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12, dataset2pow13,
				dataset2pow14, dataset2pow15, dataset2pow16, dataset2pow17, dataset2pow18,
				dataset2pow19, dataset2pow20, dataset2pow21, dataset2pow22, dataset2pow23);
		
		IScenario scenario1 = scenarioBuilder.build("Sorts competition", scenarioTitle, 900, 500);
		
		scenario1.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS,
				EnumScenarioOutputMode.TIME_ONLY, times, "Data Size", "Time log2(mics)"));
		scenario1.output();
		scenario1.getVisualizer().build().launch();
	}
	
	private static void input(IScenarioBuilder on, String title, ISort sortAlgo, IData... datasets){
		for(IData data : datasets)
			on.addEntry(title, sortAlgo, data);
	}
	
}