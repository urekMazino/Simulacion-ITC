package metodos.generadores;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RNGMethods {

	public static void main(String[] args){		
		double opcion=1;
		do{
			menuMain();
			switch(getInt("Ingresa Opcion: ")){
			case 1:
				menuSecundary("Medios Cuadrados");
				switch(getInt("Ingresa Opcion: ")){
				case 1:
					mediosCuadrados(getInt("Ingresa semilla:"));
					break;
				case 2:
					mediosCuadrados(ThreadLocalRandom.current().nextInt(1000, 10000));
					break;
				}
				break;
			case 2:
				menuSecundary("Newman");
				switch(getInt("Ingresa Opcion: ")){
				case 1:
					otroMetodo(getLong("Ingresa semilla:"));
					break;
				case 2:
					long limiteSuperior = 1000000000;
					limiteSuperior *= 10;
					otroMetodo( ThreadLocalRandom.current().nextLong((1000000000), limiteSuperior));
					break;
				}
				break;
			}
			
			
			
		}while(opcion!=0);
		
	}
	
	public static void menuMain(){
		System.out.println("Menu");
		System.out.println("1 - Metodo de medios cuadrados");
		System.out.println("2 - Metodo de Newman");
	}
	public static void menuSecundary(String titulo){
		System.out.println(titulo);
		System.out.println("1 - Ingresar la semilla manualmente");
		System.out.println("2 - Ingresar semilla automaticamente");
	}
	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
	
	public static Long getLong(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextLong();
	}
	
	//Metodo 2
	public static void otroMetodo(double semilla){
		printSemilla(semilla);
		ArrayList<Double> numeros = new ArrayList<>();
		double x0=getOtroMetodo(semilla),i=1,num;
		boolean continuar=true;
		do{
			printNumber10(x0,i++);
			x0=getOtroMetodo(x0);
			continuar = condicion(x0,numeros);
			numeros.add(x0);
		}while(continuar);
		printNumber10(x0,i++);
		printSemilla(semilla);

	}
	public static double getOtroMetodo(double entrada){
		double square = (long)entrada;
		square*=(long)entrada;
		double limite = 1000000000;
		limite *= 1000000;
//		System.out.printf("%f\n",square);
		while(square>limite){
			square = removeDigit(square);
		};
//		System.out.printf("dafuq? %f\n",square);
		
		square /= 100000;
		return square;
		
	}
	
	//Medios Cuadrados
	public static void mediosCuadrados(double semilla){
		System.out.println("Semilla: "+semilla);

		ArrayList<Double> numeros = new ArrayList<>();
		double x0=semilla,i=1,num;
		boolean continuar=true;
		do{
			printNumber(x0,i++);
			x0=getMedioCuadrado(x0);
			continuar = condicion(x0,numeros);
			numeros.add(x0);
		}while(continuar);
		printNumber(x0,i++);

	}
	public static boolean condicion(double num,ArrayList<Double> numeros){
		if (num==0){
			System.out.println("llego a 0");
			return false;
		}
		for (int i=0;i<numeros.size();i++){
			Double x = numeros.get(i);
			if (x.equals(num)){
				System.out.println("Se repitio con el numero de indice - "+(i+1));
				return false;
			}
		}
		return true;
	}
	public static double getMedioCuadrado(double entrada){
		double square = (long)entrada*(long)entrada;
//		System.out.println("cuadrado - "+square);
		while(square>1000000){
			System.out.println("square : "+square);
			square = removeDigit(square);
		};
		square /= 100;
		return square;
	}
	public static long getOtro2(double entrada){
		double square = (long)entrada*(long)entrada;
		String str = String.format("%020d", square);
		
		return Long.parseLong(str.substring(5, 16));
	}
	private static double removeDigit(double n){
		return n %= (Math.pow(10,(long) Math.log10(n)));
	}
	private static void printNumber(double n, double index){
		String formatted = String.format("%d - %04d",(long)index ,(long)n);
		System.out.println(formatted);
	}
	private static void printSemilla(double n){
		String formatted = String.format("semilla - %04d",(long)n);
		System.out.println(formatted);
	}
	private static void printNumber10(double n, double index){
		String formatted = String.format("%d - %010d",(long)index ,(long)n);
		System.out.println(formatted);
	}
}
