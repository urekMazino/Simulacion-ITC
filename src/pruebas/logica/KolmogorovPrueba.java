package pruebas.logica;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JTextArea;

import pruebas.componentes.PanelTabla;

public class KolmogorovPrueba{
	int n;
	PanelTabla panelTabla;
	double porcentaje;
	JTextArea txtArea;
	double[] numeros;
	
	public void metodo(int n,double porcentaje,PanelTabla panelTabla,JTextArea txtArea){
		this.n = n;
		this.porcentaje = porcentaje;
		this.panelTabla = panelTabla;
		this.txtArea = txtArea;
		generarNumeros();
		crearTabla();
	}

	public void crearTabla(){
		Arrays.sort(numeros);
		double max = 0;
		String res = "<html><center>";

		for (int i=0;i<n;i++){
			double esperado = ((double)i)/n;
			double resultado = Math.abs(numeros[i]-esperado);
			panelTabla.model.addRow(new Object[]{i+"",
					numeros[i]+"",
					esperado+"",
					resultado+""});
			if (resultado>max){
				max = resultado;
			}

		}
		
		res +=("El mayor es: "+max+"<br>");
//		panelTabla.model.addRow(new Object[]{"","","","","X2 ="+chiCuadradaObtenida});
		double kolmogorovValor = KolmogorovTabla.getValor(n, porcentaje);
		res +=("El valor de kolmogorov para "+(n-1)+" grados de libertad y error de "+(porcentaje*100)+"% es de: "+kolmogorovValor);
		if (max<kolmogorovValor){
			res+=("<br>"+max+" es menor que "+kolmogorovValor+" por lo que los numeros son uniformes");
			
		} else {
			res+=("<br>"+max+" es mayor que "+kolmogorovValor+" por lo que los numeros no son uniformes");
		}
		res+="</center></html>";
		panelTabla.lbResultado.setText(res);
	}
	
	public void generarNumeros(){
		numeros = new double[n];
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
		}
	}
	
}