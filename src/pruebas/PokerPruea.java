package pruebas;

import java.util.Scanner;

public class PokerPruea {
	int n;
	double porcentaje;
	Intervalo[] intervalos;
	double[] numeros;
	public void metodo(){
		leerDatos();
		generarNumeros();
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
		System.out.println("i\tprobabilidad \tOi\tEi\t(Oi-Ei)^2/2");
		double chiCuadradaObtenida=0;
		for (int i=0;i<intervalos.length;i++){
			System.out.printf("%s\t%f\t%d\t%f\t%f\n",intervalos[i].evento,intervalos[i].superior,intervalos[i].ocurrencias,intervalos[i].esperada,intervalos[i].resultado);
			chiCuadradaObtenida+=intervalos[i].resultado;
		}
		System.out.println("sumatoria es igual a: "+chiCuadradaObtenida);
		if (chiCuadrada.comparar(intervalos.length, porcentaje, chiCuadradaObtenida)){
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
	public Intervalo[] generarIntervalos(){
		intervalos = new Intervalo[7];
		intervalos[0] = new Intervalo(0,.3024,"Pachuca");
		intervalos[1] = new Intervalo(.3024,.8064,"Un par");
		intervalos[2] = new Intervalo(0,.9144,"Dos pares");
		intervalos[3] = new Intervalo(0,.3024,"Tercia");
		intervalos[4] = new Intervalo(0,.3024,"Full");
		intervalos[5] = new Intervalo(0,.3024,"Poker");
		intervalos[6] = new Intervalo(0,.3024,"Quintilla");
		for (int i=0;i<intervalos.length;i++){
			intervalos[i].esperada= (intervalos[i].superior-intervalos[i].inferior)*n;
		}
		return intervalos;
	}
	public void generarNumeros(){
		numeros = new double[n];
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
			System.out.println(i+"  "+ numeros[i]);
		}
	}
	public static void main(String args[]){
		new PokerPruea().metodo();
	}
	
}
