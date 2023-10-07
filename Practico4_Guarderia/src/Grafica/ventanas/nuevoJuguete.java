package Grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Grafica.controladores.controladora;

import java.awt.BorderLayout;

public class nuevoJuguete {
	
	private controladora c;

	private JFrame frmNuevoJuguete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nuevoJuguete window = new nuevoJuguete();
					window.frmNuevoJuguete.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public nuevoJuguete() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoJuguete = new JFrame();
		frmNuevoJuguete.setBounds(100, 100, 450, 300);
		frmNuevoJuguete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("New label");
		frmNuevoJuguete.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}
	

	public void setControladora(controladora con) {
		c = con;
	}
	public void salir() {
		frmNuevoJuguete.dispose();
	}
	
	public void setVisible(boolean b) {
		frmNuevoJuguete.setVisible(b);
	}

}
