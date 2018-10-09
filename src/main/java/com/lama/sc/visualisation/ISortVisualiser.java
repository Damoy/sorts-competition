package com.lama.sc.visualisation;

/**
 * A graph visualization.
 */
public interface ISortVisualiser {

	public void launch();
	
	/**
	 * Build the visualizer.
	 */
	public ISortVisualiser build();
	
	/**
	 * Add an entry to the visualizer.
	 */
	public ISortVisualiser addEntry(String serieTitle, long dataSize, long time);
	
}
