package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrarNino {

	private JFrame frmNuevoNino;
	private JTextField txt_cedula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarNino window = new BorrarNino();
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
	public BorrarNino() {
		initialize();
	}
	
	public void setVisible (boolean visible) {
		this.setVisible(visible);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("BORRAR NIÑO");
		frmNuevoNino.setBounds(100, 100, 450, 300);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoNino.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEDULA");
		lblNewLabel.setBounds(78, 66, 61, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel);
		
		txt_cedula = new JTextField();
		txt_cedula.setBounds(151, 61, 200, 26);
		frmNuevoNino.getContentPane().add(txt_cedula);
		txt_cedula.setColumns(10);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.setBounds(78, 129, 273, 29);
		frmNuevoNino.getContentPane().add(btn_aceptar);
	}
}
