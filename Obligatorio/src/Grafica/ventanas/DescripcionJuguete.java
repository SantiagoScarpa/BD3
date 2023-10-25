package Grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Grafica.controladores.controladora;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DescripcionJuguete {
	private controladora c;
	
	private JFrame frmDescJugete;
	private JTextField txt_descJuguete;
	private JTextField txt_cedulaNino;
	private JTextField txt_numeroJuguete;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescripcionJuguete window = new DescripcionJuguete();
					window.frmDescJugete.setVisible(true);
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
		frmDescJugete = new JFrame();
		frmDescJugete.setTitle("DESCRIPCION DE JUGUETE");
		frmDescJugete.setBounds(100, 100, 450, 300);
		frmDescJugete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDescJugete.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		lblNewLabel_1.setBounds(20, 165, 93, 16);
		frmDescJugete.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CEDULA NIÑO");
		lblNewLabel_2.setBounds(20, 75, 93, 16);
		frmDescJugete.getContentPane().add(lblNewLabel_2);
		
		txt_descJuguete = new JTextField();
		txt_descJuguete.setEnabled(false);
		txt_descJuguete.setEditable(false);
		txt_descJuguete.setBounds(125, 160, 305, 26);
		frmDescJugete.getContentPane().add(txt_descJuguete);
		txt_descJuguete.setColumns(10);
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setBounds(125, 70, 305, 26);
		frmDescJugete.getContentPane().add(txt_cedulaNino);
		txt_cedulaNino.setColumns(10);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_descJuguete.setText(c.darDescripcion(txt_cedulaNino.getText().trim(), txt_numeroJuguete.getText().trim()));
			}
		});
		btn_aceptar.setBounds(20, 105, 410, 29);
		frmDescJugete.getContentPane().add(btn_aceptar);
		
		JLabel lblNewLabel_1_1 = new JLabel("NUMERO");
		lblNewLabel_1_1.setBounds(20, 37, 93, 16);
		frmDescJugete.getContentPane().add(lblNewLabel_1_1);
		
		txt_numeroJuguete = new JTextField();
		txt_numeroJuguete.setColumns(10);
		txt_numeroJuguete.setBounds(125, 32, 305, 26);
		frmDescJugete.getContentPane().add(txt_numeroJuguete);
		
		// MENU
				menuBar = new JMenuBar();
				menuBar.setBounds(0, 0, 450, 22);
				frmDescJugete.getContentPane().add(menuBar);
				
				JMenu mnNinos = new JMenu("Ninos");
				menuBar.add(mnNinos);

				JMenuItem mniNinoNuevo = new JMenuItem("Nuevo Nino");
				mnNinos.add(mniNinoNuevo);
				mniNinoNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarNuevoNino();
					}
				});
				JMenuItem mniBorrarNino = new JMenuItem("Borrar Nino");
				mnNinos.add(mniBorrarNino);
				mniBorrarNino.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarBorrarNino();
					}
				});

				JMenu mnJuguete = new JMenu("Juguetes");
				menuBar.add(mnJuguete);

				JMenuItem mniNuevoJuguete = new JMenuItem("Nuevo Juguete");
				mnJuguete.add(mniNuevoJuguete);
				mniNuevoJuguete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarNuevoJuguete();
					}
				});

				JMenuItem mniDescJuguete = new JMenuItem("Descripcion Juguete");
				mnJuguete.add(mniDescJuguete);
				mniDescJuguete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarDescJuguete();
					}
				});

				JMenu mnListas = new JMenu("Listados");
				menuBar.add(mnListas);

				JMenuItem mniListaNinos = new JMenuItem("Lista Ninos");
				mnListas.add(mniListaNinos);
				mniListaNinos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarListaNino();
					}
				});


				JMenuItem mniListaJuguete = new JMenuItem("Lista Juguetes");
				mnListas.add(mniListaJuguete);
				mniListaJuguete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.mostrarListaJuguete();
					}
				});
				
				// FIN MENU
	}
	
	
	//METODOS GENERALES
	public void setControladora(controladora con) {
		c = con;
	}
	public void salir() {
		frmDescJugete.dispose();
	}

	public void setVisible(boolean b) {
		frmDescJugete.setVisible(b);
	}
}
