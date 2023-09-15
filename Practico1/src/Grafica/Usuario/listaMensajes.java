package Grafica.Usuario;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class listaMensajes {

	private JFrame frmListaMensajes;
	private controladoraUsuario c;
	private JScrollPane srlMensaje;
	private JButton btnEnvioMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listaMensajes window = new listaMensajes();
					window.frmListaMensajes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public listaMensajes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaMensajes = new JFrame();
		frmListaMensajes.setTitle("Lista Mensajes");
		frmListaMensajes.setBounds(100, 100, 450, 300);
		frmListaMensajes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		srlMensaje = new JScrollPane();
		
		frmListaMensajes.getContentPane().add(srlMensaje, BorderLayout.CENTER);
		
		btnEnvioMensaje = new JButton("Volver");
		btnEnvioMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mosrtarEnvioMensaje();
			}
		});
		frmListaMensajes.getContentPane().add(btnEnvioMensaje, BorderLayout.NORTH);
	}
	
	public void setVisible(Boolean b) {
		frmListaMensajes.setVisible(b);
	}
	
	public void setControladora(controladoraUsuario con) {
		c = con;
	}
	public void salir() {
		frmListaMensajes.dispose();
	}
	
	public void cargaMensajes(JTable tblMensajes) {
		tblMensajes.setEnabled(false);
		srlMensaje.remove(tblMensajes);
		srlMensaje.setViewportView(tblMensajes);
	}
	
	
	

}
