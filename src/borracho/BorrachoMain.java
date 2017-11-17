package borracho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BorrachoMain {
	static int it;
	static String titulo="Borracho";
	static int ocurrencias=0;
	static Intervalo[] intervalos = {
			new Intervalo(0,0.25,"Norte",new Point(0,-1)),
			new Intervalo(0.25,.5,"Sur",new Point(0,1)),
			new Intervalo(0.5,.75,"Este",new Point(-1,0)),
			new Intervalo(0.75,1,"Oeste",new Point(1,1))};
	static JFrame frame;
	public static void main(String[] args) {
		do{
			leerDatos();
			metodo();
		} while (true);
	}
	public static void clearData(){
		for (int i=0;i<intervalos.length;i++){
			intervalos[i].ocurrencias=0;
		}
	}
	public static void metodo(){
		System.out.println("n\tcuadras rec.\t# aleatorio\tposicion actual\tA 2 cuadras?");
		clearData();
		for (int i=0;i<it;i++){
			lanzar(i+1);
		}
		System.out.println("La probabilidad es de: "+((float)ocurrencias/(float)it));
	}
	public static void lanzar(int iteracion){
		Point posicion = new Point(0,0);
		for (int j=0;j<10;j++){
			double roll = Math.random();
			for (int i=0;i<intervalos.length;i++){
				if(roll<=intervalos[i].superior  && roll>intervalos[i].inferior){
					intervalos[i].ocurrencias++;
					posicion = intervalos[i].moverBorracho(posicion);
					System.out.printf("%d\t%d\t\t%f\t(%d,%d)\t\t%s\n",iteracion,i+1,roll,posicion.x,posicion.y,estaA2(posicion));
				
				}
			}
		}
		if (estaA2(posicion).equals("si")){
			ocurrencias++;
		}
	}
	private static String estaA2(Point p){
		String res = "no";
		if (((Math.abs(p.x)==2&&Math.abs(p.y)<2) ||(Math.abs(p.y)==2&&Math.abs(p.x)<2))){
			res = "si";
		}
		return res;
	}
	
	public static void leerDatos(){
		Scanner scan = new Scanner(System.in);
		it = getInt("Ingresa el numero de corridas:");
	}

	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
}

class Intervalo{
	double inferior,superior;
	String evento;
	int ocurrencias=0;
	Point mover;
	public Intervalo(double inferior,double superior,String evento,Point mover){
		this.inferior = inferior;
		this.superior = superior;
		this.evento = evento;
		this.mover = mover;
	}
	public Point moverBorracho(Point p){
		p.setLocation(p.x+mover.x, p.y+mover.y);
		return p;
	}
}
