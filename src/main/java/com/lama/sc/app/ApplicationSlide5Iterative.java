package com.lama.sc.app;

import com.lama.sc.core.ISort;
import com.lama.sc.core.IterativeQuickSort;
import com.lama.sc.core.IterativeQuickSort3;
import com.lama.sc.core.IterativeQuickSort3Swap;
import com.lama.sc.core.IterativeQuickSortRandom;
import com.lama.sc.core.IterativeQuickSortRandomSwap;
import com.lama.sc.core.IterativeQuickSortSwap;
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

public class ApplicationSlide5Iterative {

	public static void main(String[] args) {
		tresholdQuickComparing(50);
	}
	
	private static void tresholdQuickComparing(int times) {
		IGenerator generator = Generator.getInstance();
		IScenarioBuilder builder = ScenarioBuilder.getInstance();

		int size = (int) Math.pow(2, 8);
		int min = -1000;
		int max = 1000;
		
		IData dataset2pow8 = generator.randomGeneration(size, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow9 = generator.randomGeneration(size << 1, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow10 = generator.randomGeneration(size << 2, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow11 = generator.randomGeneration(size << 3, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow12 = generator.randomGeneration(size << 4, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow13 = generator.randomGeneration(size << 5, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow14 = generator.randomGeneration(size << 6, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow15 = generator.randomGeneration(size << 7, min, max, EnumRandomGenerationBound.N);
		IData dataset2pow16 = generator.randomGeneration(size << 8, min, max, EnumRandomGenerationBound.N);

		input(builder, "ItQuick", IterativeQuickSort.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		input(builder, "ItQuick3", IterativeQuickSort3.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		input(builder, "ItQuickRand", IterativeQuickSortRandom.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		input(builder, "ItQuickSwap", IterativeQuickSortSwap.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		input(builder, "ItQuick3Swap", IterativeQuickSort3Swap.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		input(builder, "ItQuickRandSwap", IterativeQuickSortRandomSwap.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11,
				dataset2pow12, dataset2pow13, dataset2pow14, dataset2pow15,
				dataset2pow16);
		
		IScenario scenario = builder.build("", "Comparing iterative quick sort and swaps", 900, 500);
		scenario.execute(ScenarioConfig.of(EnumTimeGranularity.MICROSECONDS, EnumScenarioOutputMode.TIME_ONLY, times, null, null));
		scenario.output();
	}
	
	private static void input(IScenarioBuilder on, String title, ISort sortAlgo, IData... datasets){
		for(IData data : datasets)
			on.addEntry(title, sortAlgo, data);
	}

}
