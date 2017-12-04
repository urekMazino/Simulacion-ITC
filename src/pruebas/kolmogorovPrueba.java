package pruebas;

import java.util.Arrays;
import java.util.Scanner;

public class kolmogorovPrueba{
	int n;
	double porcentaje;
	double[] numeros;
	public void metodo(){
		leerDatos();
		generarNumeros();
		crearTabla();
	}

	public void crearTabla(){
		Arrays.sort(numeros);
		double max = 0;
		System.out.println("i\t\tOi\tEi\t\t(Oi-Ei)");
		for (int i=0;i<n;i++){
			double esperado = ((double)i)/n;
			double res = Math.abs(numeros[i]-esperado);
			System.out.printf("%d\t%f\t%f\t%f\n",i,numeros[i],esperado,res);
			max = Math.max(max, res);
		}
		System.out.println("el mayor es: "+max);
		if (kolmogorovTabla.comparar(n, porcentaje, max)){
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
	
	public void generarNumeros(){
		numeros = new double[n];
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
		}
	}
	public static void main(String args[]){
		new kolmogorovPrueba().metodo();
	}
	
}