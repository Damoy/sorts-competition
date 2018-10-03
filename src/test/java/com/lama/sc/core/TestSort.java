package com.lama.sc.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.lama.sc.generator.EnumRandomGenerationBound;
import com.lama.sc.generator.Generator;
import com.lama.sc.generator.IGenerator;
import com.lama.sc.model.IData;

/**
 * Checks that implemented sorts produce
 * valid results.
 * Checks by comparing with Java sort results.
 * Checks are made on different data sets.
 */
@RunWith(JUnit4.class)
public class TestSort {

	private IGenerator generator;
	private List<IData> flatDatasets;
	private List<IData> randomDatasets;
	private List<IData> sortedDatasets;
	private List<IData> inversedDatasets;
	
	private int sizeStart;
	private int powMax;
	
	@Before
	public void setup(){
		generator = Generator.getInstance();
		sizeStart = 2^8;
		powMax = 16;
		
		buildRandomDatasets();
		buildFlatDatasets();
		buildInversedDatasets();
		buildSortedDatasets();
		
		checkDatasets();
	}
	
	public void checkDatasets(){
		Assert.assertNotNull(generator);
		Assert.assertNotNull(flatDatasets);
		Assert.assertNotNull(randomDatasets);
		Assert.assertNotNull(sortedDatasets);
		Assert.assertNotNull(inversedDatasets);
		
		Assert.assertFalse(flatDatasets.isEmpty());
		Assert.assertFalse(randomDatasets.isEmpty());
		Assert.assertFalse(sortedDatasets.isEmpty());
		Assert.assertFalse(inversedDatasets.isEmpty());
	}
	
	private void buildRandomDatasets(){
		randomDatasets = new ArrayList<>();
		
		for(int i = 0; i < powMax; ++i){
			randomDatasets.add(generator.randomGeneration(sizeStart + (2 * i),
					-1000000, 1000000, EnumRandomGenerationBound.N));
		}
	}
	
	private void buildFlatDatasets(){
		flatDatasets = new ArrayList<>();	
		
		for(int i = 0; i < powMax; ++i){
			flatDatasets.add(generator.flatGeneration(sizeStart + (2 * i), 6543));
		}
	}
	
	private void buildSortedDatasets(){
		sortedDatasets = new ArrayList<>();
		
		for(int i = 0; i < powMax; ++i){
			sortedDatasets.add(generator.sortedGeneration(sizeStart + (2 * i), -1000000, 1000000));
		}
	}
	
	private void buildInversedDatasets(){
		inversedDatasets = new ArrayList<>();
		
		for(int i = 0; i < powMax; ++i){
			inversedDatasets.add(generator.reversedGeneration(sizeStart + (2 * i), -1000000, 1000000));
		}
	}
	
	@Test
	public void testInsertionSort(){
		flatDatasets.forEach(fd -> checkDataset(InsertionSort.getInstance(), fd));
		randomDatasets.forEach(rd -> checkDataset(InsertionSort.getInstance(), rd));
		sortedDatasets.forEach(sd -> checkDataset(InsertionSort.getInstance(), sd));
		inversedDatasets.forEach(id -> checkDataset(InsertionSort.getInstance(), id));
	}
	
	@Test
	public void testHeapSort(){
		flatDatasets.forEach(fd -> checkDataset(HeapSort.getInstance(), fd));
		randomDatasets.forEach(rd -> checkDataset(HeapSort.getInstance(), rd));
		sortedDatasets.forEach(sd -> checkDataset(HeapSort.getInstance(), sd));
		inversedDatasets.forEach(id -> checkDataset(HeapSort.getInstance(), id));
	}
	
	@Test
	public void testMergeSort(){
		flatDatasets.forEach(fd -> checkDataset(MergeSort.getInstance(), fd));
		randomDatasets.forEach(rd -> checkDataset(MergeSort.getInstance(), rd));
		sortedDatasets.forEach(sd -> checkDataset(MergeSort.getInstance(), sd));
		inversedDatasets.forEach(id -> checkDataset(MergeSort.getInstance(), id));
	}
	
	@Test
	public void testQuickSort(){
		flatDatasets.forEach(fd -> checkDataset(QuickSort.getInstance(), fd));
		randomDatasets.forEach(rd -> checkDataset(QuickSort.getInstance(), rd));
		sortedDatasets.forEach(sd -> checkDataset(QuickSort.getInstance(), sd));
		inversedDatasets.forEach(id -> checkDataset(QuickSort.getInstance(), id));
	}
	
	@Test
	public void testSmoothSort(){
		flatDatasets.forEach(fd -> checkDataset(SmoothSort.getInstance(), fd));
		randomDatasets.forEach(rd -> checkDataset(SmoothSort.getInstance(), rd));
		sortedDatasets.forEach(sd -> checkDataset(SmoothSort.getInstance(), sd));
		inversedDatasets.forEach(id -> checkDataset(SmoothSort.getInstance(), id));
	}
	
	private void checkDataset(ISort sortAlgo, IData dataset){
		Assert.assertEquals(sortAlgo.process(dataset.clone()),
				JavaSort.getInstance().process(dataset.clone()));
	}
}
