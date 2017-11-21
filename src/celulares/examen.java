package celulares;

import java.util.Scanner;

public class examen {
	static int r1,r2,r3;
	public static void main(String [] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("ingrese r1:");
		r1 = scan.nextInt();
		System.out.println("ingrese r2: ");
		r2 = scan.nextInt();
		System.out.println("itera\tR1\tR2\tR3\tNormalizado");
		for (int i=0;i<50;i++){
			r3 = r1*r2;
			String str = r3+"";
			while (str.length()<8){
				str = "0"+str;
			}
			r3 = Integer.parseInt(str.substring(2,6));
			String str2 = "0."+str.substring(2, 6);
			System.out.println(i+"\t"+r1+"\t"+r2+"\t"+r3+"\t"+str2);
			r1=r2;
			r2=r3;
		}
	}
}
