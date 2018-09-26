package com.lama.sc.app;

import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;

public class Application {

	public static void main(String[] args){
		scenario1();
	}
	
	private static void scenario1(){
		IGenerator generator = Generator.getInstance();
		IData dataset1 = generator.randomGeneration(100, -10000, 10000);
		// ISort insertSort = InsertSort.getInstance().execute(dataset1);
	}
}
