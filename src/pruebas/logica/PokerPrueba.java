package pruebas.logica;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JTextArea;

import pruebas.componentes.PanelTabla;

public class PokerPrueba {
	int n;
	double porcentaje;
	Juego[] Juegos;
	double[] numeros;
	private PanelTabla panelTabla;
	private JTextArea txtArea;
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
			asignarJuego(numeros[i]);
		}
	}
	public void asignarJuego(double n){
		char[] arr = new DecimalFormat(".00000").format(n).substring(1,6).toCharArray();
		Arrays.sort(arr);
		int juego1=1,juego2=1,acumulador=1;
		char actual=arr[0];
		for (int i=1;i<5;i++){
			if (actual==arr[i]){
				acumulador++;
				if (i==4){
					if (juego1==1){
						juego1 = acumulador;
					} else {
						juego2 = acumulador;
					}
				}
			} else {
				actual = arr[i];
				if (acumulador>1){
					if (juego1==1){
						juego1 = acumulador;
					} else {
						juego2 = acumulador;
					}
				}
				acumulador=1;
			}
		}
		for (Juego j:Juegos){
			j.checarOcurrencia(juego1, juego2);
		}
	}
	public void crearTabla(){
		String res = "<html><center>";
		double chiCuadradaObtenida=0;
		for (int i=0;i<Juegos.length;i++){
			panelTabla.model.addRow(new Object[]{Juegos[i].evento+"",
					Juegos[i].probabilidad+"",
					Juegos[i].ocurrencias+"",
					Juegos[i].getEsperada()+"",
					Juegos[i].resultado+""});
			chiCuadradaObtenida+=Juegos[i].resultado;
		}
		
		panelTabla.model.addRow(new Object[]{"","","","","X2 ="+chiCuadradaObtenida});
		double valorChi = chiCuadrada.getValor(Juegos.length, porcentaje);
		res +=("el valor de chi cuadrada para "+(Juegos.length-1)+" grados de libertad y error de "+(porcentaje*100)+"% es de: "+valorChi);
		if (chiCuadradaObtenida<valorChi){
			res+=("<br>"+chiCuadradaObtenida+" es menor que "+valorChi+" por lo que los numeros son uniformes");
			
		} else {
			res+=("<br>"+chiCuadradaObtenida+" es mayor que "+valorChi+" por lo que los numeros no son uniformes");
		}
		res+="</center></html>";
		panelTabla.lbResultado.setText(res);
	}
	public void generarIntervalos(){
		Juegos = new Juego[7];
		Juegos[0] = new Juego(.3024,"Pachuca",1,1,n);
		Juegos[1] = new Juego(.5040,"1 par",2,1,n);
		Juegos[2] = new Juego(0.1080,"2 pares",2,2,n);
		Juegos[3] = new Juego(0.0920,"Tercia",3,1,n);
		Juegos[4] = new Juego(0.0090,"Full",3,2,n);
		Juegos[5] = new Juego(0.0045,"Poker",4,1,n);
		Juegos[6] = new Juego(0.0001,"Quint",5,1,n);
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
class Juego{
	double probabilidad;
	String evento;
	int ocurrencias=0,juego1,juego2;
	double resultado;
	int n;
	public Juego(double P,String evento,int juego1,int juego2,int n){
		this.probabilidad = P;
		this.evento = evento;
		this.juego1 = juego1;
		this.juego2 = juego2;
		this.n = n;
	}
	public void checarOcurrencia(int j1,int j2){
		if ((juego1==j1 && juego2==j2)||(juego1==j2 && juego2==j1)){
			nuevaOcurrencia();
		}
	}
	public void nuevaOcurrencia(){
		ocurrencias++;
		resultado = Math.pow(ocurrencias-getEsperada(),2)/getEsperada();
	}
	public double getEsperada(){
		return n*probabilidad;
	}
}