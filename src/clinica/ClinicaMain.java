package clinica;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ClinicaMain {
	static int it;
	static String titulo="Clinica";
	static Intervalo[] intervalos = {
			new Intervalo(0,0.05,"0 consultas"),
			new Intervalo(0.05,.15,"1 consulta"),
			new Intervalo(0.15,.35,"2 consultas"),
			new Intervalo(0.35,.65,"3 consultas"),
			new Intervalo(0.65,.85,"4 consultas"),
			new Intervalo(0.85,1,"5 consultas")};
	static JFrame frame;
	public static void main(String[] args) {
		do{
			leerDatos();
			metodo();
			crearUI();
		} while (true);
	}
	public static void clearData(){
		for (int i=0;i<intervalos.length;i++){
			intervalos[i].ocurrencias=0;
		}
	}
	public static void metodo(){
		System.out.println("n\tProbabilidad\tevento");
		clearData();
		for (int i=0;i<it;i++){
			lanzar(i+1);
		}
	}
	public static void lanzar(int iteracion){
		double roll = Math.random();
		for (int i=0;i<intervalos.length;i++){
			if(roll<=intervalos[i].superior  && roll>intervalos[i].inferior){
				intervalos[i].ocurrencias++;
				System.out.printf("%d\t%f\t%s\n",iteracion,roll,intervalos[i].evento);
			}
		}
	}
	
	public static void leerDatos(){
		Scanner scan = new Scanner(System.in);
		it = getInt("Ingresa el numero de dias:");
	}

	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
	public static void crearGrafica(){
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (int i=0;i<intervalos.length;i++){
			dataset.setValue( intervalos[i].evento , intervalos[i].ocurrencias);  
		}
		JFreeChart chart = ChartFactory.createPieChart(      
		         titulo,   // chart title 
		         dataset,          // data    
		         true,             // include legend   
		         true, 
		         false);
		 PiePlot plot = (PiePlot) chart.getPlot();
		 PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
		            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.000%"));
         plot.setLabelGenerator(gen);
//	      plot.setSimpleLabels(true);
		frame.add(new ChartPanel( chart ),BorderLayout.CENTER);

	}
	
	public static void crearUI(){
		frame = new JFrame();
		frame.setTitle(titulo);
		frame.setSize(800,900);
		crearGrafica();
		frame.setVisible(true);

	}
}

class Intervalo{
	double inferior,superior;
	String evento;
	int ocurrencias=0;
	public Intervalo(double inferior,double superior,String evento){
		this.inferior = inferior;
		this.superior = superior;
		this.evento = evento;
	}
	
}
