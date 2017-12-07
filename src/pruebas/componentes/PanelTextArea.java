package pruebas.componentes;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelTextArea extends JPanel{
	
	public JTextArea textArea;
	public JLabel lbResultado;
	
	public PanelTextArea(String titulo){
		this.setLayout(new BorderLayout());
		
		
		JLabel lbTitulo = new JLabel(titulo,SwingConstants.CENTER);
		Font fontTitulo = lbTitulo.getFont();
		lbTitulo.setFont(new Font(fontTitulo.getName(),Font.BOLD,20));
		this.add(lbTitulo,BorderLayout.NORTH);
		
		lbResultado = new JLabel("",SwingConstants.CENTER);
		lbResultado.setFont(new Font(fontTitulo.getName(),Font.PLAIN,16));
		this.add(lbResultado, BorderLayout.SOUTH);
	}
	public void crearArea(){
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font(textArea.getFont().getName(),Font.BOLD,14));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(textArea);
		this.add(scroll,BorderLayout.CENTER);
	}
}
