package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Grafica.controladores.controladora;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NuevoNino {

	private controladora c;
	
	private JFrame frmNuevoNino;
	private JTextField txt_cedula;
	private JTextField txt_nombre;
	private JTextField txt_apellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoNino window = new NuevoNino();
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
	public NuevoNino() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("NUEVO NIÑO");
		frmNuevoNino.setBounds(100, 100, 450, 300);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoNino.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEDULA");
		lblNewLabel.setBounds(78, 29, 61, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		lblNewLabel_1.setBounds(78, 69, 61, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("APELLIDO");
		lblNewLabel_2.setBounds(78, 111, 61, 16);
		frmNuevoNino.getContentPane().add(lblNewLabel_2);
		
		txt_cedula = new JTextField();
		txt_cedula.setBounds(151, 24, 200, 26);
		frmNuevoNino.getContentPane().add(txt_cedula);
		txt_cedula.setColumns(10);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(151, 64, 200, 26);
		frmNuevoNino.getContentPane().add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setBounds(151, 106, 200, 26);
		frmNuevoNino.getContentPane().add(txt_apellido);
		txt_apellido.setColumns(10);
		
		JButton btn_limpiar = new JButton("LIMPIAR");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_cedula.setText(null);
				txt_nombre.setText(null);
				txt_apellido.setText(null);
			}
		});
		btn_limpiar.setBounds(71, 177, 117, 29);
		frmNuevoNino.getContentPane().add(btn_limpiar);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.setBounds(234, 177, 117, 29);
		frmNuevoNino.getContentPane().add(btn_aceptar);
		btn_aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoNino(txt_cedula.getText().trim(), txt_nombre.getText().trim(), txt_apellido.getText().trim());
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		frmNuevoNino.getContentPane().add(menuBar);
		
		JMenu mnNinos = new JMenu("Ninos");
		menuBar.add(mnNinos);
		
		JMenuItem mniNinoNuevo = new JMenuItem("Nuevo Nino");
		mnNinos.add(mniNinoNuevo);
		mniNinoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarNuevoNino();
			}
		});;
		
		JMenu mnJuguete = new JMenu("Juguetes");
		menuBar.add(mnJuguete);
		
		
		JMenuItem mniNuevoJuguete = new JMenuItem("Nuevo Juguete");
		mnJuguete.add(mniNuevoJuguete);
		mniNuevoJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarNuevoJuguete();
			}
		});;
	}
	public void setControladora(controladora con) {
		c = con;
	}
	public void salir() {
		frmNuevoNino.dispose();
	}
	
	public void setVisible(boolean b) {
		frmNuevoNino.setVisible(b);
	}
}
