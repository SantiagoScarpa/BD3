package Grafica.ventanas;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		
		JLabel lblNewLabel = new JLabel("CEDULA");
		
		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		
		JLabel lblNewLabel_2 = new JLabel("APELLIDO");
		
		txt_cedula = new JTextField();
		txt_cedula.setColumns(10);
		
		txt_nombre = new JTextField();
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setColumns(10);
		
		JButton btn_limpiar = new JButton("LIMPIAR ");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_cedula.setText(null);
				txt_nombre.setText(null);
				txt_apellido.setText(null);
			}
		});
		
		JButton btn_aceptar = new JButton("ACEPTAR");
		btn_aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoNino(txt_cedula.getText().trim(), txt_nombre.getText().trim(), txt_apellido.getText().trim());
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

		JMenu mnListas2 = new JMenu("Listados");
		menuBar.add(mnListas2);


		JMenuItem mniListaNinos = new JMenuItem("Lista Ninos");
		mnListas2.add(mniListaNinos);
		mniListaNinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarListaNino();
			}
		});

		JMenuItem mniListaJuguete = new JMenuItem("Lista Juguetes");
		mnListas2.add(mniListaJuguete);
		GroupLayout groupLayout = new GroupLayout(frmNuevoNino.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(txt_cedula, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(txt_nombre, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(txt_apellido, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(btn_limpiar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(46)
					.addComponent(btn_aceptar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(99))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_cedula, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_nombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_apellido, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_limpiar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_aceptar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
		);
		frmNuevoNino.getContentPane().setLayout(groupLayout);
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
		frmNuevoNino.dispose();
	}
	
	public void setVisible(boolean b) {
		frmNuevoNino.setVisible(b);
	}
}
