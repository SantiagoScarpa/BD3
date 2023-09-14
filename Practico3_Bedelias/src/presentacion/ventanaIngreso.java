package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import accesoBD.Examen;
import accesoBD.Resultado;
import accesoBD.accesoBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class ventanaIngreso {

	private JFrame frame;
	private JComboBox<String> cbxListaMaterias;
	private JTextField txtCi;
	private JLabel lblCedula;
	private JLabel lblNewLabel_2;
	private JTextField txtCal;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaIngreso window = new ventanaIngreso();
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
	public ventanaIngreso() {
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
		
		cbxListaMaterias = new JComboBox();
		cbxListaMaterias.setBounds(10, 38, 414, 32);
		frame.getContentPane().add(cbxListaMaterias);
		
		this.cargarComboBoxMaterias();
		
		JLabel lblNewLabel = new JLabel("Seleccione examen");
		lblNewLabel.setBounds(10, 11, 348, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtCi = new JTextField();
		txtCi.setBounds(76, 81, 86, 20);
		frame.getContentPane().add(txtCi);
		txtCi.setColumns(10);
		
		lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 81, 46, 14);
		frame.getContentPane().add(lblCedula);
		
		lblNewLabel_2 = new JLabel("Calificación");
		lblNewLabel_2.setBounds(10, 119, 56, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtCal = new JTextField();
		txtCal.setBounds(76, 116, 86, 20);
		frame.getContentPane().add(txtCal);
		txtCal.setColumns(10);
		
		JButton btnIngresarResultado = new JButton("Ingresar Resultado");
		btnIngresarResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = cbxListaMaterias.getSelectedItem().toString();
				codigo = codigo.substring(0, codigo.indexOf(" "));
				if (!codigo.isEmpty() && !txtCi.getText().isEmpty() && !txtCal.getText().isEmpty()) {
					accesoBD a = new accesoBD();
					try {
						Resultado r = new Resultado(Integer.parseInt(txtCi.getText()), codigo,
								Integer.parseInt(txtCal.getText()));
						a.ingresarResultado(r);
					} catch (Exception err) {
						JOptionPane.showMessageDialog(null, "Error al enviar parametros", "Ha ocurrido un error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnIngresarResultado.setBounds(10, 170, 414, 80);
		frame.getContentPane().add(btnIngresarResultado);
	}
	
	public void cargarComboBoxMaterias () {
		accesoBD a = new accesoBD();
		List<Examen> listaEx = a.listarExamenes();
		cbxListaMaterias.removeAllItems();
		for (Examen ex : listaEx) {
			String item = ex.getCodigo().trim()+" - "+ex.getMateria().trim()+" - "+ex.getPeriodo().trim();
			cbxListaMaterias.addItem(item);
		}
		}
}


