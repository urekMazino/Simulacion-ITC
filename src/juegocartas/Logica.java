package juegocartas;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Logica {
	
	public int[] scoreInGame,cartasActuales;
	public int[] scoreGames = new int[4];
	public int[] cartas = new int[48];
	boolean visual=true;
	int numDeJuegos,juegoActual=0,ronda,ganadorRonda;
	public boolean ended = false,rondaInicio = false,juegoInicio=false,repartioCartas=false,repartioCartas2=false,checarGanador=false;
	Tablero tablero;
	
	public Logica(int Juegos,Tablero tablero){
		numDeJuegos = Juegos;
		this.tablero = tablero;
		for (int i=0;i<48;i++){
			cartas[i] = i;
		}
	}
	
	
	public boolean next(){
		if (hasEnded()){
			terminarSimulacion();
			return false;
		}
		siguienteRonda();
		if(visual){
			tablero.updateScores(scoreInGame, scoreGames);
		} 
		return true;
	}
	public boolean hasEnded(){
		return juegoActual>=numDeJuegos;
	}
	public void iniciarJuego(){
		scoreInGame = new int[4];
		ronda =0;
		shuffleCards();
		juegoInicio = true;
		tablero.mostrarNuevoJuego(juegoActual+1);
	}
	public boolean siguienteRonda(){
		if (!juegoInicio){
			iniciarJuego();
			return true;
		}
		if (!rondaInicio && ronda<=9){
			iniciarRonda();
			return true;
		}
		if (ronda<=9){
			if (!repartioCartas){
				
				cartasActuales = repartirCartas(ronda);
				tablero.mostrarCartas(cartasActuales);
				repartioCartas = true;
				return true;
			}
			if (!repartioCartas2){
				repartioCartas2 = true;
				return true;
			}
			if (!checarGanador){
				checarGanadorRonda(cartasActuales,ronda);
				tablero.ganoCarta(cartasActuales);
				checarGanador = true;
				return true;
			}
			tablero.ganoRondaJugador(ganadorRonda+1);
			ronda++;
			rondaInicio = false;
			return true;
		} else {
			checarGanadorJuego();
			juegoActual++;
			juegoInicio = false;
			rondaInicio = false;

			return true;
		}
			
	}
	public void iniciarRonda(){
		tablero.mostrarNuevaRonda(ronda+1);
		rondaInicio = true;
		repartioCartas=false;
		repartioCartas2=false;
		checarGanador = false;
	}
	public int[] repartirCartas(int ronda){
		int[] cartasRonda = Arrays.copyOfRange(cartas, ronda*4, (ronda*4) +4);
		imprimirCartas(cartasRonda);
		return cartasRonda;
	}
	public void checarGanadorRonda(int[] cartas,int ronda){
		ArrayList<Integer> maxs = new ArrayList<>();
		int mayor = 0;
		for (int i=0;i<4;i++){
			if (mayor<cartas[i]){
				maxs.clear();
				mayor = cartas[i];
				maxs.add(i);
			} else if(mayor==cartas[i]){
				maxs.add(i);
			}
		}
		for(Integer mayorIndex:maxs){
			scoreInGame[mayorIndex]++;
			ganadorRonda = mayorIndex;
			for (int i=0;i<cartas.length;i++){
				if (mayorIndex!=i){
					cartas[i]=-1;
				}
			}
		}

	}
	public void imprimirCartas(int[] cartasRonda){
//		for (int i=0;i<cartasRonda.length;i++){
//			System.out.println("Jugador "+(i+1)+" tiene la carta numero: "+cartasRonda[i]);
//		}
	}

	public void checarGanadorJuego(){
		ArrayList<Integer> maxs = new ArrayList<>();
		int mayor = 0;
		for (int i=0;i<4;i++){
			if (mayor<scoreInGame[i]){
				maxs.clear();
				mayor = scoreInGame[i];
				maxs.add(i);
			} else if(mayor==scoreInGame[i]){
				maxs.add(i);
			}
		}

		String anuncio="El juego "+(juegoActual+1)+" lo gano el jugador ";
		if (maxs.size()>1){
			anuncio = "El juego "+(juegoActual+1)+" lo empataron los jugadores ";
		} 
		boolean coma=false;
		for(Integer mayorIndex:maxs){
			scoreGames[mayorIndex]++;
			anuncio+= (maxs.size()>1)?((coma)?","+(mayorIndex+1):(mayorIndex+1)):(mayorIndex+1);
			if (coma==false){
				coma=true;
			}
		}
		tablero.ganoJuegoJugador(anuncio);
		
	}
	public void terminarSimulacion(){
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		crearGrafica(frame);
		frame.setVisible(true);
	}
	public void setVisual(boolean visual){
		this.visual= visual;
	}
	public void crearGrafica(JFrame frame){
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (int i=0;i<scoreGames.length;i++){
			dataset.setValue( "Jugador "+(i+1), scoreGames[i]);  
		}
		JFreeChart chart = ChartFactory.createPieChart(      
		         "Juegos de cartas",   // chart title 
		         dataset,          // data    
		         true,             // include legend   
		         true, 
		         false);
		 PiePlot plot = (PiePlot) chart.getPlot();
		 PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
		            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.000%"));
         plot.setLabelGenerator(gen);
//	      plot.setSimpleLabels(true);
		frame.add(new ChartPanel( chart ),BorderLayout.CENTER);

	}
	 private void shuffleCards()
	  {
		
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = new Random();
	    for (int i = cartas.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = cartas[index];
	      cartas[index] = cartas[i];
	      cartas[i] = a;
	    }
	    
	  }
}
