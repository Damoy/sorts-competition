package com.lama.sc.model;

import com.lama.sc.utils.Utils;

public class Data implements IData {

	private int[] content;
	
	private Data(int size){
		this.content = new int[size];
	}
	
	public static IData of(int size){
		return new Data(size);
	}
	
	@Override
	public int get(int index) {
		return content[index];
	}
	
	@Override
	public IData set(int index, int value){
		content[index] = value;
		return this;
	}

	@Override
	public void display() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for(int i = 0; i < content.length; ++i){
			sb.append(content[i]);
			sb.append(",");
		}
		
		sb.append(content[content.length - 1]);
		Utils.println(sb.toString());
	}
	
	@Override
	public int getLength() {
		return content.length;
	}

}
