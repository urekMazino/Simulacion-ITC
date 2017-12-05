package pruebas;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class PokerPruea {
	int n;
	double porcentaje;
	Juego[] Juegos;
	double[] numeros;
	public void metodo(){
		leerDatos();
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
		System.out.println("i\t\tprobabilidad \tOi\tEi\t(Oi-Ei)^2/2");
		double chiCuadradaObtenida=0;
		for (int i=0;i<Juegos.length;i++){
			System.out.printf("%s\t\t%f\t%d\t%f\t%f\n",Juegos[i].evento,Juegos[i].probabilidad,Juegos[i].ocurrencias,Juegos[i].getEsperada(),Juegos[i].resultado);
			chiCuadradaObtenida+=Juegos[i].resultado;
		}
		System.out.println("sumatoria es igual a: "+chiCuadradaObtenida);
		if (chiCuadrada.comparar(Juegos.length, porcentaje, chiCuadradaObtenida)){
			System.out.println("es menor por lo que los numeros son uniformes");
			
		} else {
			System.out.println("es mayorpor lo que los numeros no son uniformes");
		}
	}
	public void leerDatos(){
		n = getInt("Ingresa el numero de numeros: ");
		porcentaje = getDouble("Ingresa el porcentaje de error: ");
	}

	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
	public static double getDouble(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextDouble();
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
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
			System.out.println(i+"  "+ numeros[i]);
		}
		numeros = MisNumeros.numeros;
	}
	public static void main(String args[]){
		new PokerPruea().metodo();
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