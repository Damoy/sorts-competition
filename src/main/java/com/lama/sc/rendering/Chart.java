	package com.lama.sc.rendering;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JFrame implements IChart{

	private static final long serialVersionUID = 463815431520813632L;
	
	private Map<String, XYSeries> data; 
	private String title;
	
	private int width;
	private int height;
	
	public Chart(String applicationTitle, String chartTitle, int width, int height) {
		super(applicationTitle);
		this.title = chartTitle;
		this.data = new HashMap<>();
		this.width = width;
		this.height = height;
	}
	
	@Override
	public IChart build(){
		// based on the dataset we create the chart
		JFreeChart pieChart = ChartFactory.createXYLineChart(title, "Data Size", "Time", getDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		// Adding chart into a chart panel
		ChartPanel chartPanel = new ChartPanel(pieChart);
		// settind default size
		chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
		// add to contentPane
		setContentPane(chartPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		return this;
	}
	
	@Override
	public void launch(){
		pack();
		requestFocus();
		setVisible(true);
	}
	
	private XYDataset getDataset(){
		XYSeriesCollection dataset = new XYSeriesCollection();
		data.values().forEach(serie -> dataset.addSeries(serie));
		return dataset;
	}
	
	public IChart addEntry(String serieTitle, long dataSize, long time){
		if(data.containsKey(serieTitle)){
			data.get(serieTitle).add(dataSize, time);
		} else {
			XYSeries newSerie = new XYSeries(serieTitle);
			newSerie.add(dataSize, time);
			data.put(serieTitle, newSerie);
		}
		
		return this;
	}
}