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

public class ListaNinos {
	controladora c;
	
	private JFrame frmListaNino;
	private JScrollPane scl_lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaNinos window = new ListaNinos();
					window.frmListaNino.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaNinos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaNino = new JFrame();
		frmListaNino.setTitle("LISTA NIÑO");
		frmListaNino.setBounds(100, 100, 433, 310);
		frmListaNino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn_refresh = new JButton("ACTUALIZAR");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.cargoListaNino();
			}
		});

		scl_lista = new JScrollPane();

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
		
		GroupLayout groupLayout = new GroupLayout(frmListaNino.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_refresh, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(scl_lista, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(219)
							.addComponent(btn_refresh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(scl_lista, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frmListaNino.getContentPane().setLayout(groupLayout);
		
		
	}
	
	public void cargarTabla(JTable tblTabla) {
//		tblTabla.setAutoResizeMode(JTable.AUTO_RESIZE_);
		int ancho = 413/tblTabla.getColumnCount();
		for (int i=0; i<tblTabla.getColumnCount(); i++) {
			if (i == tblTabla.getColumnCount()-1 && i==1)
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho+200);
			else
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho);
		}
		scl_lista.remove(tblTabla);
		scl_lista.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}
	
	
	
	//METODOS GENERALES
	public void setControladora(controladora con) {
		c = con;
	}
	public void salir() {
		frmListaNino.dispose();
	}
	
	public void setVisible(boolean b) {
		frmListaNino.setVisible(b);
	}

}
