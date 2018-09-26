package com.lama.sc.app;

import com.lama.sc.core.HeapSort;
import com.lama.sc.core.ISort;
import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.JavaSort;
import com.lama.sc.core.MergeSort;
import com.lama.sc.core.QuickSort;
import com.lama.sc.generator.Generator;
import com.lama.sc.model.IData;
import com.lama.sc.utils.Utils;
import com.lama.sc.utils.time.EnumTimeGranularity;
import com.lama.sc.utils.time.Time;

public class Application {

	public static void main(String[] args){
		randomScenario(10, -100, 100);
		Utils.println("");
		randomScenario(10, -100000, 100000);
		Utils.println("");
		
		sortedScenario(10, -100, 100);
		Utils.println("");
		sortedScenario(10, -100000, 100000);
		Utils.println("");
		
		reversedScenario(10, -100, 100);
		Utils.println("");
		reversedScenario(10, -100000, 100000);
		Utils.println("");
	
		flatScenario(10, 1000);
		Utils.println("");
		flatScenario(10, 1000);
		Utils.println("");
	}
	
	private static void randomScenario(int nb, int min, int max){
		Utils.println(">> Start of random scenario with:");
		Utils.println(">> Elements :" + nb + ", min value: " + min + ", max value: " + max + ".");
		
		IData dataset = Generator.getInstance().randomGeneration(nb, min, max);
		processScenario(InsertionSort.getInstance(), dataset, EnumTimeGranularity.MICROSECONDS, "Insertion sort");
		processScenario(MergeSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Merge sort");
		processScenario(QuickSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Quick sort");
		processScenario(HeapSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Heap sort");
		processScenario(JavaSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Java sort");
		
		Utils.println(">> End of random scenario.");
	}
	
	private static void sortedScenario(int nb, int min, int max){
		Utils.println(">> Start of sorted scenario with:");
		Utils.println(">> Elements :" + nb + ", min value: " + min + ", max value: " + max + ".");
		
		IData dataset = Generator.getInstance().sortedGeneration(nb, min, max);
		processScenario(InsertionSort.getInstance(), dataset, EnumTimeGranularity.MICROSECONDS, "Insertion sort");
		processScenario(MergeSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Merge sort");
		processScenario(QuickSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Quick sort");
		processScenario(HeapSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Heap sort");
		processScenario(JavaSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Java sort");
		
		Utils.println(">> End of sorted scenario.");
	}
	
	private static void reversedScenario(int nb, int min, int max){
		Utils.println(">> Start of reversed scenario with:");
		Utils.println(">> Elements :" + nb + ", min value: " + min + ", max value: " + max + ".");
		
		IData dataset = Generator.getInstance().reversedGeneration(nb, min, max);
		processScenario(InsertionSort.getInstance(), dataset, EnumTimeGranularity.MICROSECONDS, "Insertion sort");
		processScenario(MergeSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Merge sort");
		processScenario(QuickSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Quick sort");
		processScenario(HeapSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Heap sort");
		processScenario(JavaSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Java sort");
		
		Utils.println(">> End of reversed scenario.");
	}
	
	private static void flatScenario(int nb, int value){
		Utils.println(">> Start of flat scenario with:");
		Utils.println(">> Elements :" + nb + ", value: " + value + ".");
		
		IData dataset = Generator.getInstance().flatGeneration(nb, value);
		processScenario(InsertionSort.getInstance(), dataset, EnumTimeGranularity.MICROSECONDS, "Insertion sort");
		processScenario(MergeSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Merge sort");
		processScenario(QuickSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Quick sort");
		processScenario(HeapSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Heap sort");
		processScenario(JavaSort.getInstance(), dataset.clone(), EnumTimeGranularity.MICROSECONDS, "Java sort");
		
		Utils.println(">> End of flat scenario.");
	}
	
	private static void processScenario(ISort algo, IData dataset, EnumTimeGranularity granularity, String title){
		Time.start();
		algo.process(dataset);
		Time.end();
		Time.display(title, granularity);
		dataset.display();
	}
}