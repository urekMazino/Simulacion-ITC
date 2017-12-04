package pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class huecosPrueba {
	double alpha,beta,teta;
	int n;
	double porcentaje;
	double[] numeros;
	ArrayList<Integer> huecos = new ArrayList<Integer>();
	
	public void metodo(){
		leerDatos();
		generarNumeros();
		crearTabla();
		crearTabla2();
	}
	public void crearTabla(){
		System.out.println("n\tUi\tEps\ti");
		boolean abierto = false;
		for (int i=0;i<n;i++){
			int cumple;
			if (cumpleCondicion(i)){
				cumple = 1;
				abierto = false;
				huecos.add(0);
			} else {
				cumple = 0;
				if (abierto){
					huecos.set(huecos.size()-1, huecos.get(huecos.size()-1)+1);
				} else {
					huecos.add(1);
					abierto = true;
				}
			}
			System.out.printf("%d\t%f\t%d\t%s\n",i,numeros[i],cumple,(cumple==0)?(i+1==n || cumpleCondicion(i+1))?huecos.get(huecos.size()-1)+"":" ":"0");
		}
//		if (chiCuadrada.comparar(n, porcentaje, max)){
//			System.out.println("es menor por lo que los numeros son uniformes");
//			
//		} else {
//			System.out.println("es mayorpor lo que los numeros no son uniformes");
//		}
	}
	public void crearTabla2(){
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		  Collections.sort(huecos);
		  int pasado=-1;
		  boolean termino = false;
		  double chiCuadradaObtenida=0;
			for (int i=0;i<huecos.size();i++){
				if (!termino && (pasado+1==huecos.get(i) || pasado==huecos.get(i))){
					pasado = huecos.get(i);
				} else {
					if (!termino)
						pasado = pasado+1;
					termino=true;
				}
				if (hm.containsKey(pasado)){
					hm.put(pasado,hm.get(huecos.get(i))+1);
				} else {
					hm.put(pasado,1);
				}
			}
	    Iterator it = hm.entrySet().iterator();
	    
	    System.out.println("i\tprobabilidad \tOi\tEi\t\t(Oi-Ei)^2/2");
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        double p = Math.pow((1-teta),(int) pair.getKey())*((it.hasNext()?teta:1));
	        double esperada = p*huecos.size();
	        double resultado = Math.pow((int)pair.getValue()-esperada,2)/esperada;
	        System.out.printf("%s\t%f\t%d\t%f\t%f\n",(it.hasNext())?pair.getKey()+"":pair.getKey()+"+",p,(int)pair.getValue(),esperada,resultado);
	       chiCuadradaObtenida += resultado;
	    }
	    
	    System.out.println("sumatoria es igual a: "+chiCuadradaObtenida);
		if (chiCuadrada.comparar(hm.size(), porcentaje, chiCuadradaObtenida)){
			System.out.println("es menor por lo que los numeros son independientes");
			
		} else {
			System.out.println("es mayorpor lo que los numeros no son independientes");
		}
	}
	public boolean cumpleCondicion(int i){
		return (numeros[i]>= alpha && numeros[i]<= beta);
	}
	public void generarNumeros(){
		numeros = new double[n];
		for (int i=0;i<n;i++){
			numeros[i] = Math.random();
		}
	}
	
	public void leerDatos(){
		n = getInt("Ingresa el numero de numeros: ");
		porcentaje = getDouble("Ingresa el porcentaje de error: ");
		alpha = getDouble("Ingresa alpha: ");
		beta = getDouble("Ingresa beta: ");
		teta = getDouble("Ingresa tetha: ");
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
	public static void main(String args[]){
		new huecosPrueba().metodo();
	}
}
