package com.lama.sc.model;

import com.lama.sc.model.IData;

/**
 * Our data model.
 * Sorts should only use this model for processing.
 */
public interface IData {

	public int get(int index);
	public int getLength();
	public int[] get();
	
	public IData set(int index, int value);
	public IData clone();
	public IData reverse();
	
}
