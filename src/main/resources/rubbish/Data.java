package com.lama.sc.model;

public class Data implements IData {
	
	protected int[] content;

	public Data(int... content) {
		this.content = content;
	}
	
	@Override
	public int get(int index) {
		return content[index];
	}

}
