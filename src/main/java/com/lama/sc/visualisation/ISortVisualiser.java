package com.lama.sc.visualisation;

public interface ISortVisualiser {

	public void launch();
	public ISortVisualiser build();
	public ISortVisualiser addEntry(String serieTitle, long dataSize, long time);
	
}
