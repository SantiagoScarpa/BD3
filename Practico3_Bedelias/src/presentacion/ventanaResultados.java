package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import accesoBD.Resultado;
import accesoBD.accesoBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ventanaResultados {

	private JFrame frame;
	private JTextField txtCedula;
	private JScrollPane srlPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaResultados window = new ventanaResultados();
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
	public ventanaResultados() {
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
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(76, 11, 86, 20);
		frame.getContentPane().add(txtCedula);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblCedula);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtCedula.getText().isEmpty()) {
					accesoBD a = new accesoBD();
					List<Resultado> lista = new ArrayList<Resultado>();
					try {
						int ci = Integer.parseInt(txtCedula.getText());
						lista = a.listarResultados(ci);

						if (!lista.isEmpty()) {
							String[] colMedHdr = { "CEDULA", "CODIGO", "CALIFICACION" };
							DefaultTableModel tblModel = new DefaultTableModel(colMedHdr, 0);
							JTable tblTabla = new JTable(tblModel) {
								// seteo color de rows y pongo en bold el jugador actual
								public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
									Component comp = super.prepareRenderer(renderer, row, column);
									Color alternateColor = new Color(82, 108, 141);
									Color whiteColor = Color.WHITE;

									comp.setFont(new Font(getFont().getFamily(), getFont().getStyle(), 14));
									if (!comp.getBackground().equals(getSelectionBackground())) {
										Color c = (row % 2 == 0 ? alternateColor : whiteColor);
										Color f = (row % 2 == 0 ? whiteColor : Color.black); // fuente
										comp.setForeground(f);
										comp.setBackground(c);
										c = null;
									}
									return comp;
								}
							};
							for (Resultado r : lista) {
								Object[] data = { r.getCedula(), r.getCodigo(), r.getCalificacion() };
								tblModel.addRow(data);
							}
							cargarTabla(tblTabla);
						} else {
							JOptionPane.showMessageDialog(null, "CI no tiene resultados en la base",
									"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "El campo CI solo puede ser numerico",
								"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo CI no puede estar vacio", "Ha ocurrido un error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(10, 42, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		srlPrincipal = new JScrollPane();
		srlPrincipal.setBounds(10, 76, 414, 174);
		frame.getContentPane().add(srlPrincipal);
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
		srlPrincipal.setViewportView(tblTabla);
		tblTabla.setEnabled(false);
	}
}
