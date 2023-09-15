package Grafica.Usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;


public class envioMensaje {

	private JFrame frmEnvioMensaje;
	private JTextField txtMensaje;

	private controladoraUsuario c;
	private JTextField txtEnvio;
	private JButton btnListaMensajes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					envioMensaje window = new envioMensaje();
					window.frmEnvioMensaje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public envioMensaje() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnvioMensaje = new JFrame();
		frmEnvioMensaje.setTitle("Envio Mensaje");
		frmEnvioMensaje.setBounds(100, 100, 450, 300);
		frmEnvioMensaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnvioMensaje.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnEnvio = new JButton("Envio Mensaje");
		btnEnvio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = txtMensaje.getText().toLowerCase();
				c.enviarMensaje(mensaje);
				txtMensaje.setText("");
			}
		});
		
		txtMensaje = new JTextField();
		frmEnvioMensaje.getContentPane().add(txtMensaje);
		txtMensaje.setColumns(10);
		frmEnvioMensaje.getContentPane().add(btnEnvio);
		
		txtEnvio = new JTextField();
		txtEnvio.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnvio.setForeground(Color.RED);
		txtEnvio.setEditable(false);
		txtEnvio.setColumns(10);
		txtEnvio.setBorder(null);
		txtEnvio.setBackground(new Color(238, 238, 238));
		frmEnvioMensaje.getContentPane().add(txtEnvio);
		
		btnListaMensajes = new JButton("Lista Mensajes");
		btnListaMensajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mostrarMensajes();
			}
		});
		frmEnvioMensaje.getContentPane().add(btnListaMensajes);
	}

	public void setMessage(String s) {
		txtEnvio.setText(s);
	}
	
	public void setControladora(controladoraUsuario con) {
		c = con;
	}
	public void salir() {
		frmEnvioMensaje.dispose();
	}
	
	public void setVisible(boolean b) {
		frmEnvioMensaje.setVisible(b);
	}
}
