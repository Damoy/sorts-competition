package com.lama.sc.app;

import com.lama.sc.core.FastMergeSort;
import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.JavaSort;
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

public class ApplicationBestChecjk {
	
	public static void main(String[] args) {
		javaVsSmooth(100, EnumRandomGenerationBound.N);
	}
	
	private static void javaVsSmooth(int times, EnumRandomGenerationBound bound){
		IGenerator generator = Generator.getInstance();
		String scenarioTitle = "Comparing Java and Smooth Sorts - Random - pow";
		
		int size = (int) Math.pow(2, 5);
		int min = -1000;
		int max = 1000;
		
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

		input(scenarioBuilder, "Java Sort", JavaSort.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
				dataset2pow13, dataset2pow14, dataset2pow15, dataset2pow16);
		
		input(scenarioBuilder, "Smooth Sort", SmoothSort.getInstance(),
				dataset2pow8, dataset2pow9, dataset2pow10, dataset2pow11, dataset2pow12,
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
