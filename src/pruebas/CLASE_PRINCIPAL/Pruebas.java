package pruebas.CLASE_PRINCIPAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pruebas.paneles.PanelChi;
import pruebas.paneles.PanelHuecos;
import pruebas.paneles.PanelKolmogorov;
import pruebas.paneles.PanelPares;
import pruebas.paneles.PanelPoker;

public class Pruebas extends JFrame {

	public Pruebas() {
	
		setTitle("Sistema De Facturacion");
		setSize(170,200);
		setLocationRelativeTo(null);
		IniciarComponentes();
	}

	private void IniciarComponentes() {
		
		JPanel panel = new JPanel();
		JButton boton1 = new JButton("Chi Cuadrada");
		JButton boton2 = new JButton("Kolmogorov");
		JButton boton3 = new JButton("Pares");
		JButton boton4 = new JButton("Huecos");
		JButton boton5 = new JButton("Poker");
	
		
		boton1.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				new PanelChi().setVisible(true);
				
			}
			
		});
		boton2.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PanelKolmogorov().setVisible(true);
				
			}
			
		});
		boton3.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PanelPares().setVisible(true);
				
			}
			
		});
		boton4.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PanelHuecos().setVisible(true);
				
			}
			
		});
		boton5.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PanelPoker().setVisible(true);
				
			}
			
		});
		panel.add(boton1);
		panel.add(boton2);
		panel.add(boton3);
		panel.add(boton4);
		panel.add(boton5);	
		this.add(panel);
	
		
	}
	
	
	public static void main(String[] args){
		new Pruebas().setVisible(true);
	}

}
