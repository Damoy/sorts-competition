package com.lama.sc.app;

import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.MergeSort;
import com.lama.sc.generator.Generator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.time.EnumTimeGranularity;
import com.lama.sc.utils.time.Time;

public class Application {

	public static void main(String[] args){
		scenario1();
	}
	
	private static void scenario1(){
		IData dataset = Generator.getInstance().randomGeneration(10, -1000, 1000);
		processScenario(InsertionSort.getInstance(), dataset, EnumTimeGranularity.NANOSECONDS, "Insertion sort");
		processScenario(MergeSort.getInstance(), dataset.clone(), EnumTimeGranularity.NANOSECONDS, "Merge sort");
	}
	
	private static void scenario2(){
		processScenario(InsertionSort.getInstance(), Generator.getInstance().randomGeneration(10, -30, 30),
				EnumTimeGranularity.NANOSECONDS, "Insertion sort");
	}
	
	private static void scenario3(){
		processScenario(MergeSort.getInstance(), Generator.getInstance().randomGeneration(10, -10, 10),
				EnumTimeGranularity.NANOSECONDS, "Merge sort");
	}
	
	private static void processScenario(ISort algo, IData dataset, EnumTimeGranularity granularity, String title){
		Time.start();
		algo.process(dataset);
		Time.end();
		Time.display(title, granularity);
		dataset.display();
	}
}
