package pruebas.logica;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextArea;

import pruebas.componentes.PanelTextArea;

public class HuecosPrueba {
	double alpha,beta,theta;
	int n;
	double porcentaje;
	double[] numeros;
	ArrayList<Integer> huecos = new ArrayList<Integer>();
	private PanelTextArea panelArea;
	private JTextArea txtArea;
	String output1;
	
	public void metodo(int n,double porcentaje,double alpha,double beta,double theta,PanelTextArea panelArea,JTextArea txtArea){
		this.n = n;
		this.alpha = alpha;
		this.beta = beta;
		this.theta = theta;
		this.porcentaje = porcentaje;
		this.panelArea = panelArea;
		this.txtArea = txtArea;
		generarNumeros();
		crearTabla();
		crearTabla2();
	}
	public void crearTabla(){
		output1 = "";
		output1 += "n\tUi\tEps\ti\n";
		  NumberFormat formatter = new DecimalFormat("#0.000000");

		boolean abierto = false;
		for (int i=0;i<n;i++){
			int cumple;
			if (cumpleCondicion(i)){
				cumple = 1;
				abierto = false;
				huecos.add(0);
			} else {
				cumple = 0;
				if (abierto){
					huecos.set(huecos.size()-1, huecos.get(huecos.size()-1)+1);
				} else {
					huecos.add(1);
					abierto = true;
				}
			}
			output1 +=i+"\t"+formatter.format(numeros[i])+"\t"+cumple+"\t"+((cumple==0)?(i+1==n || cumpleCondicion(i+1))?huecos.get(huecos.size()-1)+"":" ":"0")+"\n";
		}
		
//		if (chiCuadrada.comparar(n, porcentaje, max)){
//			System.out.println("es menor por lo que los numeros son uniformes");
//			
//		} else {
//			System.out.println("es mayorpor lo que los numeros no son uniformes");
//		}
	}
	public void crearTabla2(){
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		  Collections.sort(huecos);
		  int pasado=-1;
		  boolean termino = false;
		  String output2 ="<html><center>";
		  NumberFormat formatter = new DecimalFormat("#0.000000");
		  double chiCuadradaObtenida=0;
		  
			for (int i=0;i<huecos.size();i++){
				if (!termino && (pasado+1==huecos.get(i) || pasado==huecos.get(i))){
					pasado = huecos.get(i);
				} else {
					if (!termino)
						pasado = pasado+1;
					termino=true;
				}
				if (hm.containsKey(pasado)){
					hm.put(pasado,hm.get(huecos.get(i))+1);
				} else {
					hm.put(pasado,1);
				}
			}
			output1 += "\ni\tProbabilidad\tOi\tEi\t(Oi-Ei)^2/2\n";
			Iterator it = hm.entrySet().iterator();
	    	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        double p = Math.pow((1-theta),(int) pair.getKey())*((it.hasNext()?theta:1));
	        double esperada = p*huecos.size();
	        double resultado = Math.pow((int)pair.getValue()-esperada,2)/esperada;
	        
	        output1 += ((it.hasNext())?pair.getKey()+"":pair.getKey()+"+")+"\t"+formatter.format(p)+"\t"+(int)pair.getValue()+"\t"+formatter.format(esperada)+"\t"+resultado+"\n";
//	        System.out.printf("%s\t%f\t%d\t%f\t%f\n",(it.hasNext())?pair.getKey()+"":pair.getKey()+"+",p,(int)pair.getValue(),esperada,resultado);
	       chiCuadradaObtenida += resultado;
	    }
	   double valorChi = chiCuadrada.getValor(hm.size(), porcentaje);

		output2 +="La sumatoria es igual a: "+chiCuadradaObtenida+"<br>";
		output2 +=("el valor de chi cuadrada para "+(hm.size()-1)+" grados de libertad y error de "+(porcentaje*100)+"% es de: "+valorChi);

		if (chiCuadradaObtenida<valorChi){
			output2+=("<br>"+chiCuadradaObtenida+" es menor que "+valorChi+" por lo que los numeros son independientes");
			
		} else {
			output2+=("<br>"+chiCuadradaObtenida+" es mayor que "+valorChi+" por lo que los numeros no son independientes");
		}
		output2+="</center></html>";
		panelArea.lbResultado.setText(output2);
		panelArea.textArea.setText(output1);
	}
	public boolean cumpleCondicion(int i){
		return (numeros[i]>= alpha && numeros[i]<= beta);
	}
	public void generarNumeros(){
		numeros = new double[n];
		String output = "";
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
			output +=numeros[i] +"\n";
		}
		txtArea.setText(output);
	}
	
}
