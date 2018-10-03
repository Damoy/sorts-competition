package com.lama.sc.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Checks our data model.
 */
@RunWith(JUnit4.class)
public class TestData {

	private IData data1;
	private IData data2;
	private int[] buf1;
	private int[] buf2;
	
	@Before
	public void setup(){
		data1 = Data.of(10);
		data2 = Data.of(20);
		
		buf1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		buf2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		for(int i = 0; i < data1.getLength(); ++i){
			data1.set(i, buf1[i]);
		}
		
		for(int i = 0; i < data2.getLength(); ++i){
			data2.set(i, buf2[i]);
		}
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals(data1.getLength(), 10);
		Assert.assertEquals(data2.getLength(), 20);
	}
	
	@Test
	public void testContent(){
		Assert.assertArrayEquals(data1.get(), buf1);
		Assert.assertArrayEquals(data2.get(), buf2);
	}
	
	@Test
	public void testCloning(){
		Assert.assertArrayEquals(data1.clone().get(), buf1);
		Assert.assertArrayEquals(data2.clone().get(), buf2);
	}
}
