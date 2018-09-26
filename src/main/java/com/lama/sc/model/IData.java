package com.lama.sc.model;

import com.lama.sc.model.IData;

public interface IData{

	public int get(int index);
	public IData set(int index, int value);
	public int getLength();
	public void display();
	
}
