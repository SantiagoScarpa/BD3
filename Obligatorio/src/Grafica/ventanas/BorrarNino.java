package Grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;

import Grafica.controladores.controladora;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BorrarNino {

	private controladora c;
	private JFrame frmBorrarNino;
	private JTextField txt_cedula;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarNino window = new BorrarNino();
					window.frmBorrarNino.setVisible(true);
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBorrarNino = new JFrame();
		frmBorrarNino.setTitle("BORRAR NIÑO");
		frmBorrarNino.setBounds(100, 100, 450, 300);
		frmBorrarNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBorrarNino.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEDULA");
		lblNewLabel.setBounds(78, 66, 61, 16);
		frmBorrarNino.getContentPane().add(lblNewLabel);
		
		txt_cedula = new JTextField();
		txt_cedula.setBounds(151, 61, 200, 26);
		frmBorrarNino.getContentPane().add(txt_cedula);
		txt_cedula.setColumns(10);
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.borrarNino(txt_cedula.getText().trim());
			}
		});
		btn_aceptar.setBounds(78, 129, 273, 29);
		frmBorrarNino.getContentPane().add(btn_aceptar);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 22);
		frmBorrarNino.getContentPane().add(menuBar);
		
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
	}
	
	//METODOS GENERALES
		public void setControladora(controladora con) {
			c = con;
		}
		public void salir() {
			frmBorrarNino.dispose();
		}

		public void setVisible(boolean b) {
			frmBorrarNino.setVisible(b);
		}
}
