package Grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Grafica.controladores.controladora;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Principal {

	private controladora c;
	private JFrame frame;
	
	private JPanel pnlNuevoNino;
	private JPanel pnlBorrarNino;
	private JPanel pnlListarNinos;
	private JPanel pnlNuevoJuguete;
	private JPanel pnlDescJuguete;
	private JPanel pnlListarJuguetes;

	private JTextField txtNNCedula;
	private JTextField txtNNNombre;
	private JTextField txtNNApellido;
	private JTextField txtBNCedula;
	private JScrollPane sclLNLista;
	private JScrollPane sclLJLista;
	private JTextField txtLJCedula;
	private JTextField txtNJDesc;
	private JTextField txtNJCedula;
	private JTextField txtDJNumero;
	private JTextField txtDJCedula;
	private JTextField txtDJDesc;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("GUARDERIA");
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// MENU
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNinos = new JMenu("Niños");
		menuBar.add(mnNinos);
		
		JMenuItem mniNuevoNino = new JMenuItem("Nuevo Niño");
		mnNinos.add(mniNuevoNino);
		mniNuevoNino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetNuevoNino();
				pnlNuevoNino.setVisible(true);
			}
		});
		
		JMenuItem mniBorrarNino = new JMenuItem("Borrar Niño");
		mnNinos.add(mniBorrarNino);
		mniBorrarNino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetBorrarNino();
				pnlBorrarNino.setVisible(true);
			}
		});
		
		JMenu mnJuguetes = new JMenu("Juguetes");
		menuBar.add(mnJuguetes);
		
		JMenuItem mniNuevoJuguete = new JMenuItem("Nuevo Juguete");
		mnJuguetes.add(mniNuevoJuguete);
		mniNuevoJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetNuevoJuguete();
				pnlNuevoJuguete.setVisible(true);
			}
		});
		
		JMenuItem mniDescJuguete = new JMenuItem("Ver descripcion");
		mnJuguetes.add(mniDescJuguete);
		mniDescJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetDescJuguete();
				pnlDescJuguete.setVisible(true);
			}
		});
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mniListarNinos = new JMenuItem("Listar Niños");
		mnListados.add(mniListarNinos);
		mniListarNinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetListarNinos();
				pnlListarNinos.setVisible(true);
			}
		});
		
		JMenuItem mniListarJuguetes = new JMenuItem("Listar Juguetes");
		mnListados.add(mniListarJuguetes);
		mniListarJuguetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPaneles();
				resetListarJuguetes();
				pnlListarJuguetes.setVisible(true);
			}
		});
		
		// Nuevo Nino
		pnlNuevoNino = new JPanel();
		pnlNuevoNino.setBounds(6, 6, 488, 288);
		pnlNuevoNino.setVisible(false);
		pnlNuevoNino.setLayout(null);
		
		JLabel lblNNTitulo = new JLabel("NUEVO NIÑO");
		lblNNTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNNTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNNTitulo.setBounds(61, 25, 334, 16);
		pnlNuevoNino.add(lblNNTitulo);
				
		JLabel lblNNCedula = new JLabel("Cedula");
		lblNNCedula.setBounds(71, 67, 90, 16);
		pnlNuevoNino.add(lblNNCedula);
		
		JLabel lblNNNombre = new JLabel("Nombre");
		lblNNNombre.setBounds(71, 112, 90, 16);
		pnlNuevoNino.add(lblNNNombre);
		
		JLabel lblNNApellido = new JLabel("Apellido");
		lblNNApellido.setBounds(71, 159, 90, 16);
		pnlNuevoNino.add(lblNNApellido);
		
		txtNNCedula = new JTextField();
		txtNNCedula.setBounds(201, 62, 200, 26);
		pnlNuevoNino.add(txtNNCedula);
		txtNNCedula.setColumns(10);
		
		txtNNNombre = new JTextField();
		txtNNNombre.setBounds(201, 107, 200, 26);
		pnlNuevoNino.add(txtNNNombre);
		txtNNNombre.setColumns(10);
		
		txtNNApellido = new JTextField();
		txtNNApellido.setBounds(201, 154, 200, 26);
		pnlNuevoNino.add(txtNNApellido);
		txtNNApellido.setColumns(10);
		
		JButton btnNNLimpiar = new JButton("LIMPIAR");
		btnNNLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetNuevoNino();
			}
		});
		btnNNLimpiar.setBounds(87, 216, 117, 29);
		pnlNuevoNino.add(btnNNLimpiar);
		
		JButton btnNNAceptar = new JButton("ACEPTAR");
		btnNNAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoNino(txtNNCedula.getText().trim(), txtNNNombre.getText().trim(), txtNNApellido.getText().trim());
			}
		});
		btnNNAceptar.setBounds(252, 216, 117, 29);
		pnlNuevoNino.add(btnNNAceptar);
		
		
		// Borrar Nino
		pnlBorrarNino = new JPanel();
		pnlBorrarNino.setBounds(6, 6, 488, 288);
		pnlBorrarNino.setVisible(false);
		pnlBorrarNino.setLayout(null);
		
		JLabel lblBNTitulo = new JLabel("BORRAR NIÑO");
		lblBNTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBNTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBNTitulo.setBounds(61, 25, 334, 16);
		pnlBorrarNino.add(lblBNTitulo);
				
		JLabel lblBNCedula = new JLabel("Cedula");
		lblBNCedula.setBounds(61, 105, 90, 16);
		pnlBorrarNino.add(lblBNCedula);
		
		txtBNCedula = new JTextField();
		txtBNCedula.setBounds(184, 100, 200, 26);
		pnlBorrarNino.add(txtBNCedula);
		txtBNCedula.setColumns(10);
		
		JButton btnBNAceptar = new JButton("ACEPTAR");
		btnBNAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.borrarNino(txtBNCedula.getText().trim());
			}
		});
		btnBNAceptar.setBounds(170, 200, 117, 29);
		pnlBorrarNino.add(btnBNAceptar);
		
		// Nuevo Juguete
		pnlNuevoJuguete = new JPanel();
		pnlNuevoJuguete.setBounds(6, 6, 488, 288);
		pnlNuevoJuguete.setVisible(false);
		pnlNuevoJuguete.setLayout(null);
		
		JLabel lblNJTitulo = new JLabel("NUEVO JUGUETE");
		lblNJTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNJTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNJTitulo.setBounds(61, 25, 334, 16);
		pnlNuevoJuguete.add(lblNJTitulo);
				
		JLabel lblNJCedula = new JLabel("Cedula Niño");
		lblNJCedula.setBounds(75, 138, 90, 16);
		pnlNuevoJuguete.add(lblNJCedula);
		
		JLabel lblNJDesc = new JLabel("Descripcion");
		lblNJDesc.setBounds(75, 93, 90, 16);
		pnlNuevoJuguete.add(lblNJDesc);
				
		txtNJDesc = new JTextField();
		txtNJDesc.setBounds(205, 88, 200, 26);
		txtNJDesc.setColumns(10);
		pnlNuevoJuguete.add(txtNJDesc);
		
		txtNJCedula = new JTextField();
		txtNJCedula.setBounds(205, 133, 200, 26);
		txtNJCedula.setColumns(10);
		pnlNuevoJuguete.add(txtNJCedula);
		
		JButton btnNJLimpiar = new JButton("LIMPIAR");
		btnNJLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetNuevoJuguete();
			}
		});
		btnNJLimpiar.setBounds(87, 216, 117, 29);
		pnlNuevoJuguete.add(btnNJLimpiar);
		
		JButton btnNJAceptar = new JButton("ACEPTAR");
		btnNJAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ingresoNuevoJuguete(txtNJDesc.getText().trim(), txtNJCedula.getText().trim());
			}
		});
		btnNJAceptar.setBounds(252, 216, 117, 29);
		pnlNuevoJuguete.add(btnNJAceptar);		

		// Descripcion Juguete
		pnlDescJuguete = new JPanel();
		pnlDescJuguete.setBounds(6, 6, 488, 288);
		pnlDescJuguete.setVisible(false);
		pnlDescJuguete.setLayout(null);
		
		JLabel lblDJTitulo = new JLabel("DESCRIPCION JUGUETE");
		lblDJTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDJTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDJTitulo.setBounds(61, 25, 334, 16);
		pnlDescJuguete.add(lblDJTitulo);
				
		JLabel lblDJCedula = new JLabel("Cedula Niño");
		lblDJCedula.setBounds(74, 125, 90, 16);
		pnlDescJuguete.add(lblDJCedula);
		
		JLabel lblDJNumero = new JLabel("Numero Juguete");
		lblDJNumero.setBounds(74, 80, 118, 16);
		pnlDescJuguete.add(lblDJNumero);
				
		txtDJNumero = new JTextField();
		txtDJNumero.setBounds(204, 75, 200, 26);
		txtDJNumero.setColumns(10);
		pnlDescJuguete.add(txtDJNumero);
		
		txtDJCedula = new JTextField();
		txtDJCedula.setBounds(204, 120, 200, 26);
		txtDJCedula.setColumns(10);
		pnlDescJuguete.add(txtDJCedula);
		
		JLabel lblDJDesc = new JLabel("Descripcion");
		lblDJDesc.setBounds(74, 220, 118, 16);
		pnlDescJuguete.add(lblDJDesc);
		
		txtDJDesc = new JTextField();
		txtDJDesc.setEditable(false);
		txtDJDesc.setBounds(204, 215, 200, 26);
		txtDJDesc.setColumns(10);
		pnlDescJuguete.add(txtDJDesc);
		
		JButton btnDJAceptar = new JButton("ACEPTAR");
		btnDJAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDJDesc.setText(c.darDescripcion(txtDJCedula.getText().trim(), txtDJNumero.getText().trim()));
			}
		});
		btnDJAceptar.setBounds(173, 158, 117, 29);
		pnlDescJuguete.add(btnDJAceptar);
		
		// Listar Ninos
		pnlListarNinos = new JPanel();
		pnlListarNinos.setBounds(6, 6, 488, 288);
		pnlListarNinos.setVisible(true);
		pnlListarNinos.setLayout(null);
		
		JLabel lblLNTitulo = new JLabel("LISTADO DE NIÑOS");
		lblLNTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLNTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLNTitulo.setBounds(6, 10, 476, 16);
		pnlListarNinos.add(lblLNTitulo);
		
		JButton btnLNRefresh = new JButton("ACTUALIZAR");
		btnLNRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.cargoListaNino();
			}
		});
		btnLNRefresh.setBounds(6, 253, 476, 29);
		pnlListarNinos.add(btnLNRefresh);
				
		sclLNLista = new JScrollPane();
		sclLNLista.setBounds(6, 35, 476, 210);
		pnlListarNinos.add(sclLNLista);

		// Listar Juguetes
		pnlListarJuguetes = new JPanel();
		pnlListarJuguetes.setBounds(6, 6, 488, 288);
		pnlListarJuguetes.setVisible(false);
		pnlListarJuguetes.setLayout(null);
		
		JLabel lblLJTitulo = new JLabel("LISTADO DE JUGUETES");
		lblLJTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLJTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLJTitulo.setBounds(6, 10, 476, 16);
		pnlListarJuguetes.add(lblLJTitulo);
		
		JLabel lblLJCedula = new JLabel("Cedula Niño");
		lblLJCedula.setBounds(21, 60, 100, 16);
		pnlListarJuguetes.add(lblLJCedula);
		
		txtLJCedula = new JTextField();
		txtLJCedula.setBounds(129, 55, 200, 26);
		pnlListarJuguetes.add(txtLJCedula);
		txtLJCedula.setColumns(10);
		
		JButton btnLJListar = new JButton("LISTAR");
		btnLJListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.cargoListaJuguetes(txtLJCedula.getText().trim());
			}
		});
		btnLJListar.setBounds(341, 55, 117, 29);
		pnlListarJuguetes.add(btnLJListar);
				
		sclLJLista = new JScrollPane();
		sclLJLista.setBounds(6, 103, 476, 179);
		pnlListarJuguetes.add(sclLJLista);
		
		
		frame.getContentPane().add(pnlNuevoNino);
		frame.getContentPane().add(pnlBorrarNino);
		frame.getContentPane().add(pnlNuevoJuguete);
		frame.getContentPane().add(pnlDescJuguete);
		frame.getContentPane().add(pnlListarNinos);
		frame.getContentPane().add(pnlListarJuguetes);
		
	}
	
	public void setControladora(controladora con) {
		c = con;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public void ocultarPaneles() {
		pnlNuevoNino.setVisible(false);
		pnlBorrarNino.setVisible(false);
		pnlNuevoJuguete.setVisible(false);
		pnlDescJuguete.setVisible(false);
		pnlListarNinos.setVisible(false);
		pnlListarJuguetes.setVisible(false);
	}
	
	public void resetNuevoNino() {
		txtNNCedula.setText(null);
		txtNNNombre.setText(null);
		txtNNApellido.setText(null);
		txtNNCedula.requestFocus();
	}
	
	public void resetBorrarNino() {
		txtBNCedula.setText(null);
		txtBNCedula.requestFocus();
	}
	
	public void resetNuevoJuguete() {
		txtNJDesc.setText(null);
		txtNJCedula.setText(null);
		txtNJDesc.requestFocus();
	}
	
	public void resetDescJuguete() {
		txtDJNumero.setText(null);
		txtDJCedula.setText(null);
		txtDJDesc.setText(null);
		txtDJNumero.requestFocus();
	}

	public void resetListarJuguetes() {
		txtLJCedula.setText(null);
		sclLJLista.setViewportView(null);
		txtLJCedula.requestFocus();
	}
	
	public void resetListarNinos() {
		c.cargoListaNino();
	}
	
	public void cargarTablaNinos(JTable tblTabla) {
		int ancho = sclLNLista.getWidth()/tblTabla.getColumnCount();
		for (int i=0; i<tblTabla.getColumnCount(); i++) {
			if (i == tblTabla.getColumnCount()-1 && i==1)
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho+200);
			else
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho);
		}
		sclLNLista.remove(tblTabla);
		sclLNLista.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}


	public void cargarTablaJuguetes(JTable tblTabla) {
		tblTabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int ancho = sclLJLista.getWidth()/tblTabla.getColumnCount();
		for (int i=0; i<tblTabla.getColumnCount(); i++) {
			if (i == tblTabla.getColumnCount()-1 && i==1)
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho+200);
			else
				tblTabla.getColumnModel().getColumn(i).setMinWidth(ancho);
		}
		sclLJLista.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}
	
}
