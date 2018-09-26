package com.lama.sc.app;

import com.lama.sc.core.InsertionSort;
import com.lama.sc.core.MergeSort;
import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;

public class Application {

	public static void main(String[] args){
		scenario1();
		scenario2();
		scenario3();
	}
	
	private static void scenario1(){
		IGenerator generator = Generator.getInstance();
		IData dataset1 = generator.randomGeneration(100, -10000, 10000);
		IData dataset1Sorted = InsertionSort.getInstance().process(dataset1);
		dataset1Sorted.display();
	}
	
	private static void scenario2(){
		IGenerator generator = Generator.getInstance();
		IData dataset2 = generator.randomGeneration(20, -30, 30);
		// dataset2.display();
		IData dataset2Sorted = InsertionSort.getInstance().process(dataset2);
		dataset2Sorted.display();
	}
	
	private static void scenario3(){
		IGenerator generator = Generator.getInstance();
		IData dataset3 = generator.randomGeneration(10, -10, 10);
		MergeSort.getInstance().process(dataset3).display();
	}
}
