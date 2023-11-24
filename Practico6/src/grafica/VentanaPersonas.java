package grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import logica.VOPersona;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPersonas {

	private JFrame frame;
	private JTextField txt_ci;
	private JTextField txt_nom;
	private JTextField txt_edad;
	private ControladoraPersonas con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPersonas window = new VentanaPersonas();
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
	public VentanaPersonas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt_ci = new JTextField();
		txt_ci.setBounds(169, 11, 182, 20);
		frame.getContentPane().add(txt_ci);
		txt_ci.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CI");
		lblNewLabel.setBounds(35, 14, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NOM");
		lblNewLabel_1.setBounds(35, 50, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EDAD");
		lblNewLabel_2.setBounds(35, 109, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		txt_nom = new JTextField();
		txt_nom.setColumns(10);
		txt_nom.setBounds(169, 47, 182, 20);
		frame.getContentPane().add(txt_nom);
		
		txt_edad = new JTextField();
		txt_edad.setColumns(10);
		txt_edad.setBounds(169, 106, 182, 20);
		frame.getContentPane().add(txt_edad);
		
		JButton btnIng = new JButton("ING");
		btnIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con.nuevaPersona(txt_ci.getText(), txt_nom.getText(), txt_edad.getText());
			}
		});
		btnIng.setBounds(80, 154, 89, 23);
		frame.getContentPane().add(btnIng);
		
		JButton btnObt = new JButton("OBT");
		btnObt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con.obtenerPersona(txt_ci.getText());
			}
		});
		btnObt.setBounds(223, 154, 89, 23);
		frame.getContentPane().add(btnObt);
	}
	
	public void limpiar() {
		txt_ci.setText(null);
		txt_nom.setText(null);
		txt_edad.setText(null);
	}
	
	public void cargarPersona(VOPersona p) {
		limpiar();
		txt_ci.setText(((Integer) p.getCedula()).toString());
		txt_nom.setText(p.getNombre());
		txt_edad.setText(((Integer) p.getEdad()).toString());
	}
	
	public void setControladora(ControladoraPersonas c) {
		con = c;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
