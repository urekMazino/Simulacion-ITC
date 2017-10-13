package Buffon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AgujasPanelDraw extends JPanel{
	
	public ArrayList<Needle> needles;

	public AgujasPanelDraw(ArrayList<Needle> needles){
		this.needles = needles;
		this.setMinimumSize(new Dimension(800,400));
		this.setPreferredSize(new Dimension(800,400));
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);	
		for (int i=0;i<8;i++){
			if (i%2==0){
				g.setColor(Color.decode("#3498db"));
			} else {
				g.setColor(Color.decode("#2980b9"));
			}
			g.fillRect(i*100,0,100,400);
		}
		for (Needle x : needles){
			g.setColor(x.c);
			g.drawLine(x.p1.x, x.p1.y, x.p2.x, x.p2.y);
		}
	}
}
