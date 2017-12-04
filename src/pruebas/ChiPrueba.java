package pruebas;

import java.util.Scanner;

public class ChiPrueba {
	int n;
	double porcentaje;
	Intervalo[] intervalos;
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
			System.out.printf("%d\t%f\t%d\t%f\t%f\n",i,intervalos[i].superior,intervalos[i].ocurrencias,intervalos[i].esperada,intervalos[i].resultado);
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
		new ChiPrueba().metodo();
	}
	
}
class Intervalo{
	double inferior,superior;
	String evento;
	int ocurrencias=0;
	double esperada,resultado;
	public Intervalo(double inferior,double superior,String evento,double esperada){
		this.inferior = inferior;
		this.superior = superior;
		this.evento = evento;
		this.esperada = esperada;
	}
	public Intervalo(double inferior,double superior,String evento){
		this(inferior,superior,evento,0);
	}
	public void nuevaOcurrencia(){
		ocurrencias++;
		resultado = Math.pow(ocurrencias-esperada,2)/esperada;
	}
	
}