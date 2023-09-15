package Grafica.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class winMenuAdmin {
	private controladoraAdmin c;
	private JFrame frame;
	private JTextField txtResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winMenuAdmin window = new winMenuAdmin();
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
	public winMenuAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLevantar = new JButton("Recuperar Mensajes");
		btnLevantar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.recuperoMensajes();
			}
		});
		frame.getContentPane().add(btnLevantar, BorderLayout.WEST);
		
		JButton btnLista = new JButton("Lista Mensajes");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarMensajes();
			}
		});
		frame.getContentPane().add(btnLista, BorderLayout.CENTER);
		
		JButton btnRespaldar = new JButton("Respaldar Mensajes");
		btnRespaldar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.respaldoMensajes();
			}
		});
		frame.getContentPane().add(btnRespaldar, BorderLayout.EAST);
		
		txtResultado = new JTextField();
		txtResultado.setForeground(new Color(0, 0, 0));
		txtResultado.setEditable(false);
		frame.getContentPane().add(txtResultado, BorderLayout.SOUTH);
		txtResultado.setColumns(10);
	}

	public void setControladora(controladoraAdmin con) {
		c = con;
	}
	
	public void setVisible(Boolean b) {
		frame.setVisible(b);
	}
	
	public void salir() {
		frame.dispose();
	}
	
	public void setText(String s) {
		txtResultado.setText(s);
	}
}
