package com.lama.sc.rendering;

public interface IChart {

	public void launch();
	public IChart build();
	public IChart addEntry(String serieTitle, long dataSize, long time);
	
}
