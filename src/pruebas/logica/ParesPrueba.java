package pruebas.logica;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JTextArea;

import pruebas.componentes.PanelTabla;
import pruebas.componentes.PanelTextArea;

public class ParesPrueba {
	int n;
	int divisiones;
	double porcentaje;
	Intervalo[] intervalos;
	double[] numeros;
	int[][] cuadro;
	private PanelTextArea panelTabla;
	private JTextArea txtArea;
	public void metodo(int n,double porcentaje,int divisiones,PanelTextArea panelTabla,JTextArea txtArea){
		this.n = n;
		this.divisiones = divisiones;
		this.porcentaje = porcentaje;
		this.panelTabla = panelTabla;
		this.txtArea = txtArea;
		generarNumeros();
		crearTabla();
	}
	public void crearTabla(){
		int[][] cuadro = new int[divisiones][divisiones];
		double chiCuadradaObtenida=0;
		double esperada = ((double)n)/(divisiones*divisiones);
		double subDivision = 1.0/divisiones;
		String output1 ="";
		String output2 ="<html><center>";
		for (int i=0;i<n;i++){
			int columna = (int)(numeros[i]/subDivision);
			int fila = (int)(numeros[((i+1==n)?i:i+1)]/subDivision);
			cuadro[fila][columna]++;
		}
		output1 +="Oi\n";
		for (int i=0;i<cuadro.length;i++){
			for (int j=0;j<cuadro[0].length;j++){
				output1 +=cuadro[i][j]+"\t";
			}
			output1 +="\n";
		}
		output1 +="\nEi = "+esperada+"\n\n";

		output1 +="(Oi-Ei)^2/2\n";
		NumberFormat formatter = new DecimalFormat("#0.000000");
		for (int i=0;i<cuadro.length;i++){
			for (int j=0;j<cuadro[0].length;j++){
				double res = Math.pow(cuadro[i][j]-esperada,2)/esperada;
				
				output1 += formatter.format(res)+"\t";
				chiCuadradaObtenida +=  res;
			}
			output1 +="\n";
		}
		double valorChi = chiCuadrada.getValor(divisiones*divisiones, porcentaje);
		output2 +="La sumatoria es igual a: "+chiCuadradaObtenida+"<br>";
		output2 +=("el valor de chi cuadrada para "+(divisiones*divisiones-1)+" grados de libertad y error de "+(porcentaje*100)+"% es de: "+valorChi);
		if (chiCuadradaObtenida<valorChi){
			output2+=("<br>"+chiCuadradaObtenida+" es menor que "+valorChi+" por lo que los numeros son uniformes");
			
		} else {
			output2+=("<br>"+chiCuadradaObtenida+" es mayor que "+valorChi+" por lo que los numeros no son uniformes");
		}
		output2+="</center></html>";
		panelTabla.lbResultado.setText(output2);
		panelTabla.textArea.setText(output1);
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