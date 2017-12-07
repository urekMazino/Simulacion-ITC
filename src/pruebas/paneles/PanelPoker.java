package pruebas.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import pruebas.componentes.PanelEntrada;
import pruebas.componentes.PanelTabla;
import pruebas.logica.ChiPrueba;
import pruebas.logica.PokerPrueba;

public class PanelPoker extends JFrame{

	String titulo = "Poker";
	public PanelEntrada panelEntrada;
	PanelTabla panelCentro;
	private PokerPrueba pokerPrueba;
	
	public PanelPoker(){
		iniFrame();
		panelEntrada = new PanelEntrada();
		this.add(panelEntrada,BorderLayout.WEST);
		
		panelCentro = new PanelTabla(titulo);
		String [] titulos = {"i","Probabilidad","Oi","Ei","(Oi-Ei)^2/2"};
		panelCentro.crearTabla(titulos);
		
		panelEntrada.btnGenerar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				correrMetodo();				
			}
			
		});
		pokerPrueba = new PokerPrueba();
		this.add(panelCentro,BorderLayout.CENTER);
		this.getRootPane().setDefaultButton(panelEntrada.btnGenerar);
	}
	public void correrMetodo(){

		try{
			int n = Integer.parseInt(panelEntrada.tfDatos.getText());
			double porcentaje = Double.parseDouble(panelEntrada.tfError.getText());
			
			pokerPrueba.metodo(n, porcentaje, panelCentro,panelEntrada.textArea);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void iniFrame(){
		this.setLocationRelativeTo(null);
		this.setSize(850,550);
		this.setTitle(titulo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
}
