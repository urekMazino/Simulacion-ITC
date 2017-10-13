package juegoDeDardos;

import java.awt.BorderLayout;import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame{
	
	JLabel aproxLabel = new JLabel("Aproximacion");
	PanelPrincipal AP = new PanelPrincipal(aproxLabel);
	
	public Main(){
		this.setSize(800,900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.setTitle("Juego de los dardos");
		createInput();
		createBoard();
	}
	public void createBoard(){
		this.add(AP,BorderLayout.CENTER);
	}
	public void createInput(){
		JPanel topPanel = new JPanel();
		JPanel input = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		aproxLabel.setFont(new Font(aproxLabel.getFont().getName(),Font.PLAIN,30));
		aproxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel numAgujasLabel = new JLabel("Numero de Agujas");
		numAgujasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField numAgujasField = new JTextField(15);
		numAgujasField.setMaximumSize(new Dimension(200,30));
		
		JLabel tiempoLabel = new JLabel("Tiempo (segundos)");
		tiempoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField tiempoField = new JTextField();
		tiempoField.setMaximumSize(new Dimension(200,30));
		
		
		
		input.add(numAgujasLabel);
		input.add(numAgujasField);
		
//		entrada.add(tiempoLabel);
//		entrada.add(tiempoField);
		JButton button = new JButton("Ejecutar");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int agujas = Integer.parseInt(numAgujasField.getText());
				
				AP.startSimulation(agujas);
			}
			
		});
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		input.add(button);
		topPanel.add(input);
		topPanel.add(aproxLabel);
		this.add(topPanel,BorderLayout.NORTH);
	}
	
	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
}
