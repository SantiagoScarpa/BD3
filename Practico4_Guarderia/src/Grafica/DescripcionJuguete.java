package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DescripcionJuguete {

	private JFrame frmNuevoNino;
	private JTextField txt_descJuguete;
	private JTextField txt_cedulaNino;
	private JTextField txt_numeroJuguete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescripcionJuguete window = new DescripcionJuguete();
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
	public DescripcionJuguete() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("DESCRIPCION DE JUGUETE");
		frmNuevoNino.setBounds(100, 100, 450, 300);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoNino.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		lblNewLabel_1.setBounds(20, 165, 93, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CEDULA NIÑO");
		lblNewLabel_2.setBounds(20, 75, 93, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel_2);
		
		txt_descJuguete = new JTextField();
		txt_descJuguete.setEnabled(false);
		txt_descJuguete.setEditable(false);
		txt_descJuguete.setBounds(125, 160, 305, 26);
		frmNuevoNino.getContentPane().add(txt_descJuguete);
		txt_descJuguete.setColumns(10);
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setBounds(125, 70, 305, 26);
		frmNuevoNino.getContentPane().add(txt_cedulaNino);
		txt_cedulaNino.setColumns(10);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.setBounds(20, 105, 410, 29);
		frmNuevoNino.getContentPane().add(btn_aceptar);
		
		JLabel lblNewLabel_1_1 = new JLabel("NUMERO");
		lblNewLabel_1_1.setBounds(20, 37, 93, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel_1_1);
		
		txt_numeroJuguete = new JTextField();
		txt_numeroJuguete.setColumns(10);
		txt_numeroJuguete.setBounds(125, 32, 305, 26);
		frmNuevoNino.getContentPane().add(txt_numeroJuguete);
	}
}
