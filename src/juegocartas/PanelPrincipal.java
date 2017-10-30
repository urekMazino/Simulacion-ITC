package juegocartas;

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

	Tablero tablero = new Tablero();
	Logica logica;
	double rights = 0;
	double events = 1000;
	double radio = 200;
	JLabel label;
	Timer timer;
	
	DecimalFormat df = new DecimalFormat("#.0000000000000"); 
	
//	int maxTime = 10000;
	public PanelPrincipal(JLabel label){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(tablero);
		this.label = label;
	}
	public void startSimulation(int juegos,double velocidad,boolean visual){
		tablero.reset();
		if (timer!=null){
			timer.cancel();
		}
		timer = new Timer();
		logica = new Logica(juegos,tablero);
		logica.setVisual(visual);
		int intervalo = (int) (1500.0/velocidad);
		
		if (visual){
			timer.scheduleAtFixedRate(new TimerTask(){
	
				@Override
				public void run() {
					if (!logica.next()){
						timer.cancel();
					}
				}
				
			},(long) 0, intervalo);
		} else {
			while(logica.next());
		}
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
