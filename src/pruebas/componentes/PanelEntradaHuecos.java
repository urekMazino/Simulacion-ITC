package pruebas.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelEntradaHuecos extends JPanel{

	public JTextField tfDatos;
	public JTextField tfError;
	public JTextArea textArea;
	public JButton btnGenerar;
	public JTextField tfAlpha;
	public JTextField tfBeta;
	public JTextField tfTheta;

	public PanelEntradaHuecos(){
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(175,this.getPreferredSize().height));
//		this.setBackground(Color.BLUE);
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets= new Insets(5,5,5,5);
		 gbc2.fill = GridBagConstraints.BOTH;
		 gbc2.weighty = 1;
		 gbc2.weightx = 1;
		 gbc2.gridx = 1;

		JPanel panelDatos = new JPanel();
//		panelDatos.setBackground(Color.RED);
		
		panelDatos.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		 gbc.weighty = 0;
		 gbc.gridx = 1;
		 gbc.weightx = 1;
		 gbc.insets= new Insets(1,0,1,0);
		tfDatos = new JTextField(15);
		tfError = new JTextField(15);
		tfAlpha = new JTextField(15);
		tfBeta = new JTextField(15);
		tfTheta = new JTextField(15);

        panelDatos.add(new JLabel("Cantidad de numeros"),gbc);
        panelDatos.add(tfDatos,gbc);
        panelDatos.add(new JLabel("Porcentaje de error"),gbc);
        panelDatos.add(tfError,gbc);
        panelDatos.add(new JLabel("Alpha"),gbc);
        panelDatos.add(tfAlpha,gbc);
        panelDatos.add(new JLabel("Beta"),gbc);
        panelDatos.add(tfBeta,gbc);
        panelDatos.add(new JLabel("Theta"),gbc);
        panelDatos.add(tfTheta,gbc);
		 gbc.insets= new Insets(10,0,10,0);
		 btnGenerar = new JButton("Generar");
        panelDatos.add(btnGenerar,gbc);
        gbc.insets= new Insets(1,0,1,0);
        panelDatos.add(new JLabel("Numeros"),gbc);

        
		 gbc.weighty = 1;
		 gbc.fill = GridBagConstraints.BOTH;
		 textArea = new JTextArea();
	        JScrollPane scroll = new JScrollPane(textArea);

		 panelDatos.add(scroll,gbc);

        
		 this.add(panelDatos,gbc2);
		 
	}
	
}
