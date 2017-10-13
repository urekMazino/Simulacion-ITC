package juegoDeDardos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PanelPrincipal extends JPanel{

	public ArrayList<Dardo> darts = new ArrayList<>();
	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeries s1 = new XYSeries("Aprox PI");
	double rights = 0;
	double events = 1000;
	double radio = 200;
	JLabel label;
	
	DecimalFormat df = new DecimalFormat("#.0000000000000"); 
	
//	int maxTime = 10000;
	public PanelPrincipal(JLabel label){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dataset.addSeries(s1);
		this.add(new Tablero(darts));
		this.add(createChartPanel());
		this.label = label;
	}
	public ChartPanel createChartPanel(){
		JFreeChart chart = ChartFactory.createXYLineChart("Aguja Buffon", "agujas", "Aprox", dataset);
		ValueMarker marker = new ValueMarker(3.1416);
		marker.setPaint(Color.black);
		XYPlot plot = (XYPlot)chart.getPlot();
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
//		range.setAutoRangeMinimumSize(10);
		range.setAutoRangeIncludesZero(false);
		plot.addRangeMarker(marker);
		
		plot.setBackgroundPaint(Color.white);
		ChartPanel cPanel = new ChartPanel(chart);
		return cPanel;
	}
	public void startSimulation(double agujas){
		darts.clear();
		s1.clear();
		rights = 0;
		Timer timer = new Timer();
		events = agujas;
		int per100m = Math.min((int) Math.ceil(agujas/100),1000);
		System.out.println(per100m);
		timer.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				for (int i=0;i<per100m;i++){
					if (darts.size() >= events){
						System.out.println(darts.size()+","+rights);
						timer.cancel();
						break;
					}
//					System.out.println("Valor aprox de pi: "+);

					throwDart();
				}
				repaint();
			}
			
		},(long) 100, 100);
	}
	public void throwDart(){

		Point origin = new Point(ThreadLocalRandom.current().nextInt(200, 601),ThreadLocalRandom.current().nextInt(0, 401));
		Color color;
		Dardo n = new Dardo(origin);
		if (validateDart(n)){
			n.c = Color.GREEN;
			rights++;
		} else {
			n.c = Color.RED;
		}
		darts.add(n);
		s1.add(darts.size(),aprox());
		label.setText("Pi: "+df.format(aprox()));
	}
	public double aprox(){
		return (4*((double)rights/darts.size()));
	}
	public boolean validateDart(Dardo dart){
		if (Math.sqrt(Math.pow(dart.p.x-400,2)+Math.pow(dart.p.y-200, 2))>=radio){
			return false;
		} else {
			return true;
		}
	}
	
}

class Dardo{
	Point p;
	Color c;
	public Dardo(Point p){
		this.p = p;
	}
	public void setColor(Color c){
		this.c = c;
	}
}
