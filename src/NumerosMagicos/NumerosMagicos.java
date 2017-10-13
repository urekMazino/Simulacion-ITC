package NumerosMagicos;

import java.util.ArrayList;
import java.util.Scanner;

public class NumerosMagicos {

	
	static int x0,a,c,m,it;
	static long limite = (long) Math.pow(2, 15);
	public static void main(String[] args) {
		
		do{
			leerDatos();
			metodo();
		} while (true);
	}
	public static void metodo(){
		ArrayList<Long> numeros = new ArrayList<>();
		long x = x0;boolean option;int i = 0;
		boolean continuar= true;
		System.out.printf("iteracion\t\tXn\t\t\taXn+C\t\ta(Xn+C)/m\tXn+1\n");

		numeros.add(x);

			do {
				x = getNext(x);
				continuar = condicion(x,numeros);
				numeros.add(x);
			}while(continuar);
	}
	public static long getNext(long x){
		long val1,val2,res;
		val1 = x*a+c;
		val2 = val1/m;
		res = val1%m;
		System.out.printf("%d\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\n",it++,x,val1,val2,res);
		return res;
	}
	public static boolean condicion(Long num,ArrayList<Long> numeros){
		for (int i=0;i<numeros.size();i++){
			Long x = numeros.get(i);
			if (x.equals(num)){
				System.out.println("Se repitio con el numero de indice: "+(i));
				return false;
			}
		}
		return true;
	}
	public static void leerDatos(){
		Scanner scan = new Scanner(System.in);
		System.out.println("\ningresa datos...");
		x0 = getInt("Ingresa la semilla:");
		a = getInt("Ingresa el valor de a:");
		while (a%2==0 || a%3==0 || a%5==0){
			System.out.println(a+" no cumple con las condiciones, no debe ser par, ni divisible entre 3 o 5");
			a = getInt("Ingresa el valor de a:");
		}
		c = getInt("Ingresa el valor de c:");
		while(c%8!=5){
			System.out.println("El residuo de "+c+" entre 8, no es igual a 5.");
			c = getInt("Ingresa el valor de c:");
		}
		m = getInt("Ingresa el valor de m:");
		while (m>limite || !isPrime(m)){
			System.out.println("m es demasiado grande o no es primo");
			m = getInt("Ingresa el valor de m:");
		}
	}

	static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    return true;
	}
	public static int getInt(String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextInt();
	}
	
}
