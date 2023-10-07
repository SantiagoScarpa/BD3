package Grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Grafica.controladores.controladora;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoJuguete {
	private controladora c;

	private JFrame frmNuevoJuguete;
	private JTextField txt_desc;
	private JTextField txt_cedulaNino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoJuguete window = new NuevoJuguete();
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
	public NuevoJuguete() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoJuguete = new JFrame();
		frmNuevoJuguete.setTitle("NUEVO JUGUETE");
		frmNuevoJuguete.setBounds(100, 100, 450, 300);
		frmNuevoJuguete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoJuguete.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		lblNewLabel_1.setBounds(66, 45, 93, 16);
		frmNuevoJuguete.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CEDULA NIÑO");
		lblNewLabel_2.setBounds(66, 87, 93, 16);
		frmNuevoJuguete.getContentPane().add(lblNewLabel_2);
		
		txt_desc = new JTextField();
		txt_desc.setBounds(171, 40, 200, 26);
		frmNuevoJuguete.getContentPane().add(txt_desc);
		txt_desc.setColumns(10);
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setBounds(171, 82, 200, 26);
		frmNuevoJuguete.getContentPane().add(txt_cedulaNino);
		txt_cedulaNino.setColumns(10);
		
		JButton btn_limpiar = new JButton("LIMPIAR");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_desc.setText(null);
				txt_cedulaNino.setText(null);
			}
		});
		btn_limpiar.setBounds(71, 177, 117, 29);
		frmNuevoJuguete.getContentPane().add(btn_limpiar);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.setBounds(234, 177, 117, 29);
		frmNuevoJuguete.getContentPane().add(btn_aceptar);
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoJuguete(txt_desc.getText().trim(), txt_cedulaNino.getText().trim());
			}

		});
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
