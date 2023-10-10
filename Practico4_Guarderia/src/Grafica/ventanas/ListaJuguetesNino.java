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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListaJuguetesNino {
	
	private controladora c;

	private JFrame frmListaJuguetes;
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
					window.frmListaJuguetes.setVisible(true);
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
		frmListaJuguetes = new JFrame();
		frmListaJuguetes.setTitle("LISTA JUGUETES DE NIÑO");
		frmListaJuguetes.setBounds(100, 100, 450, 310);
		frmListaJuguetes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn_listar = new JButton("LISTAR");
		btn_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.cargoListaJuguetes(txt_cedulaNino.getText().trim());
			}
		});
		
		scl_lista = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("CELULA NIÑO");
		
		txt_cedulaNino = new JTextField();
		txt_cedulaNino.setColumns(10);
		
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
		GroupLayout groupLayout = new GroupLayout(frmListaJuguetes.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(txt_cedulaNino, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(btn_listar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
						.addComponent(menuBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(scl_lista, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addGap(5))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_cedulaNino, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_listar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(scl_lista, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addContainerGap())
		);
		frmListaJuguetes.getContentPane().setLayout(groupLayout);
		

				
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
	
	
	//METODOS GENERALES
		public void setControladora(controladora con) {
			c = con;
		}
		public void salir() {
			frmListaJuguetes.dispose();
		}
		
		public void setVisible(boolean b) {
			frmListaJuguetes.setVisible(b);
		}
}
