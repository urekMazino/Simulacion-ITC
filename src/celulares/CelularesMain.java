package celulares;

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

public class CelularesMain {
	static int it;
	static int celularesAComprar;
	static String titulo="Celulares";
	static ArrayList<Integer> utilidades;
	static double moda, desviacion, varianza,promedio,suma;
	static int costo;
	static Intervalo[] intervalos = {
			new Intervalo(0,0.3,100),
			new Intervalo(0.3,.5,150),
			new Intervalo(0.5,.8,200),
			new Intervalo(0.8,.95,250),
			new Intervalo(0.95,1,300)
			};
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
		System.out.println("n\tProbabilidad\tVendidos\tCosto\tDevolucion\tganancia\tutilidad");
		clearData();
		for (int i=0;i<it;i++){
			lanzar(i+1);
		}
		promedio = suma/utilidades.size();
		System.out.println("Utilidad promedio: "+promedio);
		System.out.println("Moda: "+moda());
		System.out.println("Varianza: "+varianza());
		System.out.println("Desviacion Estandar: "+desviacion());
	}
	public static double moda(){
		moda = calcularUtilidad(intervalos[0]);
		int ocurrenciasMax = intervalos[0].ocurrencias;
		for (int i=0;i<intervalos.length;i++){
			if (intervalos[i].ocurrencias>ocurrenciasMax){
				ocurrenciasMax = intervalos[i].ocurrencias;
				moda = calcularUtilidad(intervalos[i]);
			}
		}
		return moda;
	}
	public static double varianza(){
		double var=0;
		for (int i=0; i<utilidades.size();i++)
		{
		    var = var + Math.pow(utilidades.get(i) - promedio, 2);
		}
		return var;
	}
	public static double desviacion(){
		return Math.sqrt(varianza());
	}
	public static int calcularUtilidad(Intervalo intervalo){
		int vendidos = Math.min(intervalo.vendidos,celularesAComprar);
		int ganancia = vendidos * 100;
		int devolucion =  (celularesAComprar-vendidos)*25;
		int utilidad = ganancia+devolucion-costo;
		return utilidad;
	}
	public static void imprimirUtilidad(Intervalo intervalo,int iteracion,double roll){
		int vendidos = Math.min(intervalo.vendidos,celularesAComprar);
		int ganancia = vendidos * 100;
		int devolucion =  (celularesAComprar-vendidos)*25;
		int utilidad = ganancia+devolucion-costo;
		utilidades.add(utilidad);
		System.out.printf("%d\t%f\t%d\t\t%d\t%d\t\t%d\t\t%d\n",iteracion,roll,vendidos,costo,devolucion,ganancia,utilidad);
	}
	public static void lanzar(int iteracion){
		double roll = Math.random();

		for (int i=0;i<intervalos.length;i++){
			if(roll<=intervalos[i].superior  && roll>intervalos[i].inferior){
				intervalos[i].ocurrencias++;
				imprimirUtilidad(intervalos[i],i+1,roll);
				suma+=utilidades.get(utilidades.size()-1);
			}
		}
	}
	
	public static void leerDatos(){
		Scanner scan = new Scanner(System.in);
		utilidades = new ArrayList<Integer>();
		it = getInt("Ingresa el numero de dias:");
		celularesAComprar = getInt("Celulares a comprar:");
		costo = celularesAComprar*75;
	}

	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
	public static void crearGrafica(){
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (int i=0;i<intervalos.length;i++){
			dataset.setValue( intervalos[i].vendidos+" celulares" , intervalos[i].ocurrencias);  
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
	int vendidos;
	int ocurrencias=0;
	public Intervalo(double inferior,double superior,int vendidos){
		this.inferior = inferior;
		this.superior = superior;
		this.vendidos = vendidos;
	}
	
}
