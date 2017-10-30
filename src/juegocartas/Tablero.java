package juegocartas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tablero extends JPanel{
	
	public Image mesaImg,cartaVolteada;
	public int[] scoreInGame=new int[4];
	public int[] scoreGames=new int[4];
	
	public Image[] cartas = new Image[48];
	public int[] cartasActuales = {1000,1000,1000,1000};
	public String anuncio="";
	public Rectangle[] jugadoresPosicionesScore = {
			new Rectangle(10,300,190,20),
			new Rectangle(400,680,190,20),
			new Rectangle(980,680,190,20),
			new Rectangle(1380,300,190,20)
	};
	public Point[] jugadoresPosicionCartas = {
			new Point(350,250),
			new Point(606,350),
			new Point(862,350),
			new Point(1120,250)};
	
	public Tablero(){
		this.setBackground(Color.BLACK);
		mesaImg = new ImageIcon("res/table.png").getImage();
		for (int i=1;i<=48;i++){
			String numberStr = String.format("%02d", i);
//			System.out.println(numberStr);
			cartas[i-1] =  new ImageIcon("res/cartas/chicas/carta_"+numberStr+".png").getImage();
		}
		cartaVolteada =  new ImageIcon("res/cartas/chicas/carta_50.png").getImage();
	}
	public void reset(){
		scoreInGame=new int[4];
		scoreGames=new int[4];
		hideCards();
		hideAnuncio();
	}
	public void updateScores(int[] scoreInGame, int[] scoreGames){
		this.scoreGames = scoreGames;
		this.scoreInGame = scoreInGame;
		repaint();

	}
	private void hideCards(){
		for (int i=0;i<cartasActuales.length;i++){
			cartasActuales[i] = 1000;
		}
	}
	private void hideAnuncio(){
		anuncio = "";
	}
	public void mostrarNuevaRonda(int i){
		anuncio = "Inicia la Ronda "+i;
		hideCards();
	}
	public void mostrarNuevoJuego(int i){
		anuncio = "Inicia el Juego "+i;
		hideCards();
	}
	public void mostrarCartas(int[] cartasActuales){
		this.cartasActuales =cartasActuales;
		hideAnuncio();
	}
	public void ganoCarta(int[] cartasActuales){
		this.cartasActuales =cartasActuales;
		hideAnuncio();
	}
	public void ganoRondaJugador(int i){
		anuncio = "El jugador "+i+" gano la ronda";
		hideCards();
	}
	public void ganoJuegoJugador(String anuncioGanador){
		anuncio = anuncioGanador;
		hideCards();
	}
	public void drawCenteredString(Graphics g, String text) {
		Rectangle rect = new Rectangle(500,200,600,250);
		drawCenteredString(g,text,rect,40);
	}

	public void drawCenteredString(Graphics g, String text,Rectangle rect,int size) {
		g.setColor(Color.WHITE);
		Font font = new Font("Helvetica", Font.BOLD, size);
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
	public void drawString(Graphics g, String text,Rectangle rect,int size) {
		g.setColor(Color.WHITE);
		Font font = new Font("Helvetica", Font.BOLD, size);
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x - size/2 + 15;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);	
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(mesaImg, 200,0,null);
		dibujarCartas(g);
		drawCenteredString(g,anuncio);
		drawScores(g);
//		g2.drawString(anuncio, , y);
	}
	public void drawScores(Graphics g){
		for (int i=0;i<4;i++){
			Rectangle rectPuntos1 = (Rectangle) jugadoresPosicionesScore[i].clone();
			rectPuntos1.setLocation(rectPuntos1.x, rectPuntos1.y+40);
			Rectangle rectPuntos2 = (Rectangle) jugadoresPosicionesScore[i].clone();
			rectPuntos2.setLocation(rectPuntos2.x, rectPuntos2.y+70);
			drawCenteredString(g,"Jugador "+(i+1),jugadoresPosicionesScore[i],40);
			drawString(g,"Juegos: "+(scoreGames[i]),rectPuntos1,25);
			drawString(g,"Rondas: "+(scoreInGame[i]),rectPuntos2,20);
		}
	}
	public void dibujarCartas(Graphics g){
		for (int i=0;i<cartasActuales.length;i++){
			g.drawImage(decifrarImagen(cartasActuales[i]), jugadoresPosicionCartas[i].x, jugadoresPosicionCartas[i].y, null);
		}
	}
	public Image decifrarImagen(int i){
		if (i<0){
			return cartaVolteada;
		} else if (i==1000){
			return null;
		} else {
			return cartas[equivalente(i)];
		}
	}
	public int equivalente(int i){
		return ((i/4))+((i%4)*12);
	}
}
