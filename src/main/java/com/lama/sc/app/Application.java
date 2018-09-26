package com.lama.sc.app;

import com.lama.sc.core.MergeSort;
import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;

public class Application {

	public static void main(String[] args){
		scenario2();
	}
	
	private static void scenario2(){
		IGenerator generator = Generator.getInstance();
		IData dataset1 = generator.randomGeneration(10, -10, 10);
		MergeSort.getInstance().process(dataset1).display();
	}
}
