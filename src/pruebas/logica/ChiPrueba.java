package pruebas.logica;

import java.util.Scanner;

import javax.swing.JTextArea;

import pruebas.componentes.PanelEntrada;
import pruebas.componentes.PanelTabla;

public class ChiPrueba {
	int n;
	PanelTabla panelTabla;
	double porcentaje;
	Intervalo[] intervalos;
	double[] numeros;
	JTextArea txtArea;
	public void metodo(int n,double porcentaje,PanelTabla panelTabla,JTextArea txtArea){
		this.n = n;
		this.porcentaje = porcentaje;
		this.panelTabla = panelTabla;
		this.txtArea = txtArea;
		generarNumeros();
		generarIntervalos();
		contarOcurrencias();
		crearTabla();
	}
	public void contarOcurrencias(){
		for(int i=0;i<numeros.length;i++){
			for(int j=0;j<intervalos.length;j++){
				if(numeros[i]<=intervalos[j].superior  
						&& numeros[i]>intervalos[j].inferior){
					intervalos[j].nuevaOcurrencia();
				}
			}
		}
	}
	public void crearTabla(){
		double chiCuadradaObtenida=0;
		String res = "<html><center>";
		for (int i=0;i<intervalos.length;i++){
			panelTabla.model.addRow(new Object[]{i+"",
					intervalos[i].superior+"",
					intervalos[i].ocurrencias+"",
					intervalos[i].esperada+"",
					intervalos[i].resultado+""});
			chiCuadradaObtenida+=intervalos[i].resultado;
		}
		panelTabla.model.addRow(new Object[]{"","","","","X2 ="+chiCuadradaObtenida});
		double valorChi = chiCuadrada.getValor(intervalos.length, porcentaje);
		res +=("el valor de chi cuadrada para "+(intervalos.length-1)+" grados de libertad y error de "+(porcentaje*100)+"% es de: "+valorChi);
		if (chiCuadradaObtenida<valorChi){
			res+=("<br>"+chiCuadradaObtenida+" es menor que "+valorChi+" por lo que los numeros son uniformes");
			
		} else {
			res+=("<br>"+chiCuadradaObtenida+" es mayor que "+valorChi+" por lo que los numeros no son uniformes");
		}
		res+="</center></html>";
		panelTabla.lbResultado.setText(res);
	}
	
	public Intervalo[] generarIntervalos(){
		intervalos = new Intervalo[(int) Math.round(Math.sqrt(n))];
		for (int i=0;i<intervalos.length;i++){
			intervalos[i] = new Intervalo(((double)i)/intervalos.length,((double)i+1)/intervalos.length,i+"",((double)n)/intervalos.length);
		}
		return intervalos;
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
class Intervalo{
	double inferior,superior;
	String evento;
	int ocurrencias=0;
	double esperada,resultado;
	public Intervalo(double inferior,double superior,String evento,double esperada){
		this.inferior = inferior;
		this.superior = superior;
		this.evento = evento;
		this.esperada = esperada;
	}
	public Intervalo(double inferior,double superior,String evento){
		this(inferior,superior,evento,0);
	}
	public void nuevaOcurrencia(){
		ocurrencias++;
		resultado = Math.pow(ocurrencias-esperada,2)/esperada;
	}
	
}