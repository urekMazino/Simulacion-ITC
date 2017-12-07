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
import pruebas.componentes.PanelEntradaHuecos;
import pruebas.componentes.PanelTabla;
import pruebas.componentes.PanelTextArea;
import pruebas.logica.ChiPrueba;
import pruebas.logica.HuecosPrueba;
import pruebas.logica.ParesPrueba;
import pruebas.logica.PokerPrueba;

public class PanelHuecos extends JFrame{

	String titulo = "Huecos";
	public PanelEntradaHuecos panelEntrada;
	PanelTextArea panelCentro;
	private HuecosPrueba huecosPrueba;
	
	public PanelHuecos(){
		iniFrame();
		panelEntrada = new PanelEntradaHuecos();
		this.add(panelEntrada,BorderLayout.WEST);
		
		panelCentro = new PanelTextArea(titulo);
//		String [] titulos = {"i","Probabilidad","Oi","Ei","(Oi-Ei)^2/2"};
		panelCentro.crearArea();
		
		panelEntrada.btnGenerar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				correrMetodo();				
			}
			
		});
		huecosPrueba = new HuecosPrueba();
		this.add(panelCentro,BorderLayout.CENTER);
		this.getRootPane().setDefaultButton(panelEntrada.btnGenerar);
	}
	public void correrMetodo(){

		try{
			int n = Integer.parseInt(panelEntrada.tfDatos.getText());
			double porcentaje = Double.parseDouble(panelEntrada.tfError.getText());
			double alpha = Double.parseDouble(panelEntrada.tfAlpha.getText());
			double beta = Double.parseDouble(panelEntrada.tfBeta.getText());
			double theta = Double.parseDouble(panelEntrada.tfTheta.getText());
			huecosPrueba.metodo(n, porcentaje,alpha,beta,theta, panelCentro,panelEntrada.textArea);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void iniFrame(){
		this.setLocationRelativeTo(null);
		this.setSize(850,750);
		this.setTitle(titulo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
}
