package pruebas.componentes;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JPanel{
	
	public DefaultTableModel model;
	public JTable table;
	public JLabel lbResultado;
	
	public PanelTabla(String titulo){
		this.setLayout(new BorderLayout());
		
		
		JLabel lbTitulo = new JLabel(titulo,SwingConstants.CENTER);
		Font fontTitulo = lbTitulo.getFont();
		lbTitulo.setFont(new Font(fontTitulo.getName(),Font.BOLD,20));
		this.add(lbTitulo,BorderLayout.NORTH);
		
		lbResultado = new JLabel("",SwingConstants.CENTER);
		lbResultado.setFont(new Font(fontTitulo.getName(),Font.PLAIN,16));
		this.add(lbResultado, BorderLayout.SOUTH);
	}
	public void crearTabla(String[] titulos){
		
		model = new DefaultTableModel(0, titulos.length) ;
		model.setColumnIdentifiers(titulos);
		table = new JTable(model){
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return  false;
		    }
		};
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		this.add(scroll,BorderLayout.CENTER);
	}
}
