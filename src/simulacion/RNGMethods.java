package simulacion;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RNGMethods {

	public static void main(String[] args){		
		long opcion=1;
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
					otroMetodo( ThreadLocalRandom.current().nextLong((1000000000), (1000000000*10)));
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
	public static void otroMetodo(long semilla){
		printSemilla(semilla);
		ArrayList<Long> numeros = new ArrayList<>();
		long x0=getOtroMetodo(semilla),i=1,num;
		boolean continuar=true;
		do{
			printNumber(x0,i++);
			x0=getOtroMetodo(x0*x0);
			continuar = condicion(x0,numeros);
			numeros.add(x0);
		}while(continuar);
		printNumber(x0,i++);
		printSemilla(semilla);

	}
	public static long getOtroMetodo(long entrada){
		long square = entrada;
		long limite = 1000000000*10;
		System.out.println("cuadrado - "+square);
		System.out.println();
		while(square>limite){
			square /= 10;
			if (square>limite){
				square = removeDigit(square);
			}
		};
		return square;
		
	}
	
	//Medios Cuadrados
	public static void mediosCuadrados(long semilla){
		System.out.println("Semilla: "+semilla);

		ArrayList<Long> numeros = new ArrayList<>();
		long x0=semilla,i=1,num;
		boolean continuar=true;
		do{
			printNumber(x0,i++);
			x0=getMedioCuadrado(x0);
			continuar = condicion(x0,numeros);
			numeros.add(x0);
		}while(continuar);
		printNumber(x0,i++);

	}
	public static boolean condicion(long num,ArrayList<Long> numeros){
		if (num==0){
			System.out.println("llego a 0");
			return false;
		}
		for (int i=0;i<numeros.size();i++){
			long x = numeros.get(i);
			if (x==num){
				System.out.println("Se repitio con el numero de indice - "+(i+1));
				return false;
			}
		}
		return true;
	}
	public static long getMedioCuadrado(long entrada){
		long square = entrada*entrada;
		System.out.println("cuadrado - "+square);
		while(square>10000){
			square = removeDigit(square);
			if (square>10000){
				square /= 10;
			}
		};
		return square;
	}
	private static long removeDigit(long n){
		return n %= (long) Math.pow(10, (long) Math.log10(n));
	}
	private static void printNumber(long n, long index){
		String formatted = String.format("%d - %05d",index ,n);
		System.out.println(formatted);
	}
	private static void printSemilla(long n){
		String formatted = String.format("semilla - %05d",n);
		System.out.println(formatted);
	}
}
