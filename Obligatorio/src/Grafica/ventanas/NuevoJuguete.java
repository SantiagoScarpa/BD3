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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		
		JLabel lblNewLabel_2 = new JLabel("CEDULA NIÑO");
		
		txt_desc = new JTextField();
		txt_desc.setColumns(10);
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setColumns(10);
		
		JButton btn_limpiar = new JButton("LIMPIAR");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_desc.setText(null);
				txt_cedulaNino.setText(null);
			}
		});
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoJuguete(txt_desc.getText().trim(), txt_cedulaNino.getText().trim());
			}

		});
		
		
		// MENU 
		JMenuBar menuBar = new JMenuBar();
		
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
		GroupLayout groupLayout = new GroupLayout(frmNuevoJuguete.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(txt_desc, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(txt_cedulaNino, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(btn_limpiar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(46)
					.addComponent(btn_aceptar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_desc, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_cedulaNino, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_limpiar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_aceptar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
		);
		frmNuevoJuguete.getContentPane().setLayout(groupLayout);
		mniListaJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarListaJuguete();
			}
		});
		//FIN MENU
	}
	

	//METODOS GENERALES
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
