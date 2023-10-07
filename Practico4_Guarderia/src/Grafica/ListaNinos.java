package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListaNinos {

	private JFrame frmNuevoNino;
	private JScrollPane scl_lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaNinos window = new ListaNinos();
					window.frmNuevoNino.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaNinos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("LISTA NIÑO");
		frmNuevoNino.setBounds(100, 100, 450, 300);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoNino.getContentPane().setLayout(null);
		
		JButton btn_refresh = new JButton("ACTUALIZAR");
		btn_refresh.setBounds(6, 237, 438, 29);
		frmNuevoNino.getContentPane().add(btn_refresh);
		
		scl_lista = new JScrollPane();
		scl_lista.setBounds(6, 6, 438, 228);
		frmNuevoNino.getContentPane().add(scl_lista);
	}
	
	public void cargarTabla(JTable tblTabla) {
		tblTabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int ancho = 413/tblTabla.getColumnCount();
		for (int i=0; i<tblTabla.getColumnCount(); i++) {
			if (i == tblTabla.getColumnCount()-1 && i==1)
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho+200);
			else
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho);
		}
		scl_lista.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}
	
}
