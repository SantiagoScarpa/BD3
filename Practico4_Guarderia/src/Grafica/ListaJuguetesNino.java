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

public class ListaJuguetesNino {

	private JFrame frmNuevoNino;
	private JTextField txt_cedulaNino;
	private JScrollPane scl_lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaJuguetesNino window = new ListaJuguetesNino();
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
	public ListaJuguetesNino() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("LISTA JUGUETES DE NIÑO");
		frmNuevoNino.setBounds(100, 100, 450, 300);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoNino.getContentPane().setLayout(null);
		
		JButton btn_listar = new JButton("LISTAR");
		btn_listar.setBounds(333, 6, 111, 29);
		frmNuevoNino.getContentPane().add(btn_listar);
		
		scl_lista = new JScrollPane();
		scl_lista.setBounds(6, 39, 438, 227);
		frmNuevoNino.getContentPane().add(scl_lista);
		
		JLabel lblNewLabel = new JLabel("CELULA NIÑO");
		lblNewLabel.setBounds(6, 11, 96, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel);
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setBounds(114, 6, 207, 26);
		frmNuevoNino.getContentPane().add(txt_cedulaNino);
		txt_cedulaNino.setColumns(10);
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
