package Buffon;

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

public class AgujasPanel extends JPanel{

	public ArrayList<Needle> needles = new ArrayList<>();
	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeries s1 = new XYSeries("Aprox PI");
	double rights = 0;
	double events = 1000;
	double needleLen = 100;
	JLabel label;
	DecimalFormat df = new DecimalFormat("#.0000000000000"); 
	
//	int maxTime = 10000;
	public AgujasPanel(JLabel label){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dataset.addSeries(s1);
		this.add(new AgujasPanelDraw(needles));
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
		needles.clear();
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
					if (needles.size() >= events){
						System.out.println(needles.size()+","+rights);
						timer.cancel();
						break;
					}
//					System.out.println("Valor aprox de pi: "+);

					throwNeedle();
				}
				repaint();
			}
			
		},(long) 100, 100);
	}
	public void throwNeedle(){

		Point origin = new Point(ThreadLocalRandom.current().nextInt(1, 801),ThreadLocalRandom.current().nextInt(0, 401));
		int angle = ThreadLocalRandom.current().nextInt(0, 360);
		Point p2 = new Point(Math.max(-1, Math.min((int)Math.round(origin.x+Math.cos(angle)*needleLen),800)),Math.max(-1, Math.min((int)Math.round(origin.y+Math.sin(angle)*needleLen),400)));
		Color color;
		Needle n = new Needle(origin,p2);
		if (validateNeedle(n)){
			n.c = Color.GREEN;
			rights++;
		} else {
			n.c = Color.RED;
		}
		needles.add(n);
		s1.add(needles.size(),aprox());
		label.setText("Pi: "+df.format(aprox()));
	}
	public double aprox(){
		return (rights==0)?0:(2*((double)needles.size()/rights));
	}
	public boolean validateNeedle(Needle n){
		if ((n.p1.x+100)/100==(n.p2.x+100)/100){
			return false;
		} else {
			return true;
		}
	}
	
}

class Needle{
	Point p1,p2;
	Color c;
	public Needle(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
}
