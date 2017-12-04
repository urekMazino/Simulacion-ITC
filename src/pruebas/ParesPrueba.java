package pruebas;

import java.util.Scanner;

public class ParesPrueba {
	int n;
	int cuadrado;
	double porcentaje;
	Intervalo[] intervalos;
	double[] numeros;
	int[][] cuadro;
	public void metodo(){
		leerDatos();
		generarNumeros();
		crearTabla();
	}
	public void crearTabla(){
		int[][] cuadro = new int[cuadrado][cuadrado];
		double chiCuadradaObtenida=0;
		double esperada = ((double)n)/(cuadrado*cuadrado);
		double subDivision = 1.0/cuadrado;
		for (int i=0;i<n;i++){
			int fila = (int)(numeros[i]/subDivision);
			int columna = (int)(numeros[((i+1==n)?i:i+1)]/subDivision);
			cuadro[fila][columna]++;
		}
		System.out.println("Oi");
		for (int i=0;i<cuadro.length;i++){
			for (int j=0;j<cuadro[0].length;j++){
				System.out.printf("%d\t",cuadro[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Ei: "+esperada);
		System.out.println();

		System.out.println("(Oi-Ei)^2/2");
		for (int i=0;i<cuadro.length;i++){
			for (int j=0;j<cuadro[0].length;j++){
				double res = Math.pow(cuadro[i][j]-esperada,2)/esperada;
				System.out.printf("%f\t",res);
				chiCuadradaObtenida +=  res;
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("sumatoria es igual a: "+chiCuadradaObtenida);
		if (chiCuadrada.comparar((cuadrado*cuadrado), porcentaje, chiCuadradaObtenida)){
			System.out.println("es menor por lo que los numeros son independientes");
			
		} else {
			System.out.println("es mayor por lo que los numeros no son independientes");
		}
	}
	public void leerDatos(){
		n = getInt("Ingresa el numero de numeros: ");
		porcentaje = getDouble("Ingresa el porcentaje de error: ");
		cuadrado = getInt("Ingresa el valor de n(divisiones del cuadrado): ");
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
		intervalos = new Intervalo[(int) Math.round(Math.sqrt(n))];
		for (int i=0;i<intervalos.length;i++){
			intervalos[i] = new Intervalo(((double)i)/intervalos.length,((double)i+1)/intervalos.length,i+"",((double)n)/intervalos.length);
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
		new ParesPrueba().metodo();
	}
	
}