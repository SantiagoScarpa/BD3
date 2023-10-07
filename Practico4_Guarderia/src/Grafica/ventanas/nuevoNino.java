package Grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import Grafica.controladores.controladora;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class nuevoNino {
	
	private controladora c;
	
	private JFrame frmNuevoNino;
	private JTextField txtCi;
	private JTextField txtApellido;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nuevoNino window = new nuevoNino();
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
	public nuevoNino() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoNino = new JFrame();
		frmNuevoNino.setTitle("Practico 4");
		frmNuevoNino.setBounds(100, 100, 329, 248);
		frmNuevoNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCi = new JLabel("Cedula:");
		lblCi.setBounds(5, 2, 50, 26);
		
		txtCi = new JTextField();
		txtCi.setBounds(71, 2, 95, 26);
		txtCi.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(5, 75, 50, 26);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(71, 37, 95, 26);
		txtNombre.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(40, 124, 100, 25);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmNuevoNino.getContentPane().setLayout(null);
		frmNuevoNino.getContentPane().add(lblCi);
		frmNuevoNino.getContentPane().add(txtCi);
		frmNuevoNino.getContentPane().add(lblApellido);
		frmNuevoNino.getContentPane().add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(5, 37, 50, 26);
		frmNuevoNino.getContentPane().add(lblNombre);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(71, 75, 95, 26);
		txtApellido.setColumns(10);
		frmNuevoNino.getContentPane().add(txtApellido);
		frmNuevoNino.getContentPane().add(btnIngresar);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoNino(txtCi.getText().trim(), txtNombre.getText().trim(), txtApellido.getText().trim());
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmNuevoNino.setJMenuBar(menuBar);
		
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
