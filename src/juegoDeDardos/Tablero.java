package juegoDeDardos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Tablero extends JPanel{
	
	public ArrayList<Dardo> dardos;

	public Tablero(ArrayList<Dardo> dardos){
		this.dardos = dardos;
		this.setMinimumSize(new Dimension(800,400));
		this.setPreferredSize(new Dimension(800,400));
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);	
		g.setColor(Color.decode("#3498db"));
		g.fillRect(200, 0, 400, 400);
		g.setColor(Color.decode("#2980b9"));
		g.fillOval(200, 0, 400, 400);

		for (int i=0;i<dardos.size();i++){
			Dardo x = dardos.get(i);
			g.setColor(x.c);
			g.fillOval(x.p.x-2, x.p.y-2, 4,4);
		}
	}
}
