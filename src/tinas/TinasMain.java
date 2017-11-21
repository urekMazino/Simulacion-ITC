package tinas;

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

public class TinasMain {
	static int it;
	static String titulo="Borracho";
	static int ocurrencias=0;
	
	static JFrame frame;
	public static void main(String[] args) {
		do{
			leerDatos();
			metodo();
		} while (true);
	}
	public static void clearData(){
		ocurrencias=0;
	}
	public static void metodo(){
		System.out.println("n\ttina\t# aleatorio\tpeso simulado\tpeso total\tse excede?");
		clearData();
		for (int i=0;i<it;i++){
			lanzar(i+1);
		}
		System.out.println("La probabilidad de que se exceda es de: "+(((float)ocurrencias/(float)it)*100)+"%");
	}
	public static void lanzar(int iteracion){
		double pesoSimulado=0;
		double pesoTotal=0;
		for (int j=0;j<5;j++){
			double roll = Math.random();
			if (roll<.5){
				pesoSimulado = 190+Math.sqrt(800*roll);
			} else {
				pesoSimulado = 230-Math.sqrt(800*(1-roll));
			}
			pesoTotal+=pesoSimulado;
			System.out.printf("%d\t%d\t%f\t%f\t%f\t%s\n",iteracion,j+1,roll,pesoSimulado,pesoTotal,(pesoTotal>1000)?"si":"no");
		}
		
		System.out.println();

		if (pesoTotal>1000){
			ocurrencias++;
		}
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

