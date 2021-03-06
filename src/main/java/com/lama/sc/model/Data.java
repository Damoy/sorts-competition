package com.lama.sc.model;

public class Data implements IData {

	private int[] content;
	
	private Data(int size){
		this.content = new int[size];
	}
	
	private Data(int... content){
		this.content = new int[content.length];
		for(int i = 0; i < content.length; ++i){
			this.content[i] = content[i];
		}
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
	public IData reverse() {
		for(int i = 0; i < content.length >> 1; ++i){
			int tmp = content[i];
			content[i] = content[content.length - i - 1];
			content[content.length - i - 1] = tmp;
		}
		
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for(int i = 0; i < content.length - 1; ++i){
			sb.append(content[i]);
			sb.append(",");
		}
		
		sb.append(content[content.length - 1]);
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;
		
		Data d = (Data) o;
		
		if(d.getLength() != getLength())
			return false;
		
		for(int i = 0; i < d.getLength(); ++i) {
			if(d.get()[i] != get()[i])
				return false;
		}
		
		return true;
	}
	
	@Override
	public int getLength() {
		return content.length;
	}
	
	@Override
	public IData clone(){
		return new Data(content);
	}

	@Override
	public int[] get() {
		return content;
	}
}
