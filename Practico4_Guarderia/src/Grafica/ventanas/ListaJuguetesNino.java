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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ListaJuguetesNino {
	private controladora c;

	private JFrame frmListaJugete;
	private JTextField txt_cedulaNino;
	private JScrollPane scl_lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaJuguetesNino window = new ListaJuguetesNino();
					window.frmListaJugete.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaJuguetesNino() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaJugete = new JFrame();
		frmListaJugete.setTitle("LISTA JUGUETES DE NIÑO");
		frmListaJugete.setBounds(100, 100, 450, 300);
		frmListaJugete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn_listar = new JButton("LISTAR");

		scl_lista = new JScrollPane();

		JLabel lblNewLabel = new JLabel("CELULA NIÑO");

		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmListaJugete.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
										.addGap(12)
										.addComponent(txt_cedulaNino, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
										.addGap(12)
										.addComponent(btn_listar, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
								.addComponent(scl_lista, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(5)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(txt_cedulaNino, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_listar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(4)
						.addComponent(scl_lista, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
				);
		frmListaJugete.getContentPane().setLayout(groupLayout);

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
				c.mostrarNuevoNino();
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
				c.mostrarNuevoJuguete();
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
		GroupLayout groupLayout1 = new GroupLayout(frmListaJugete.getContentPane());
		groupLayout1.setHorizontalGroup(
				groupLayout1.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
				.addGroup(groupLayout1.createSequentialGroup()
						.addGap(3)
						.addGroup(groupLayout1.createParallelGroup(Alignment.LEADING)
								.addComponent(scl_lista, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
				);
		groupLayout1.setVerticalGroup(
				groupLayout1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout1.createSequentialGroup()
						.addGroup(groupLayout1.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout1.createSequentialGroup()
										.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(219)
										)
								.addGroup(groupLayout1.createSequentialGroup()
										.addGap(25)
										.addComponent(scl_lista, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
//		frmListaNino.getContentPane().setLayout(groupLayout);
		mniListaJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarListaJuguete();
			}
		});
		// FIN MENU
	}

	public void cargarTabla(JTable tblTabla) {
		tblTabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int ancho = 413/tblTabla.getColumnCount();
		for (int i=0; i<tblTabla.getColumnCount(); i++) {
			if (i == tblTabla.getColumnCount()-1 && i==1)
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho+200);
			else
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho);
		}
		scl_lista.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}

}
