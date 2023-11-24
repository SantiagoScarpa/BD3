package grafica;

import javax.swing.JOptionPane;

import logica.Fachada;
import logica.LogicaException;
import logica.VOPersona;
import persistencia.ExcepcionPersistencia;

public class ControladoraPersonas {
	private Fachada fac;
	private VentanaPersonas winPrincipal;
		
	public ControladoraPersonas() {
			fac = new Fachada();
			winPrincipal = new VentanaPersonas();
			winPrincipal.setControladora(this);
			winPrincipal.setVisible(true);
	}
	
	public void nuevaPersona(String ciStr, String nom, String edadStr) {
		int ci = Integer.parseInt(ciStr);
		int edad = Integer.parseInt(edadStr);
		VOPersona vPer = new VOPersona(ci,nom,edad);
		try {
			fac.nuevaPersona(vPer);
			JOptionPane.showMessageDialog (null, "Agregado", "Peticion realizada", JOptionPane.INFORMATION_MESSAGE);	
		} catch (LogicaException | ExcepcionPersistencia e) {
			e.printStackTrace();
		}
	}
	
	public void obtenerPersona(String ciStr) {
		int ci = Integer.parseInt(ciStr);
		try {
			VOPersona p = fac.obtenerPersona(ci);
			winPrincipal.cargarPersona(p);
		} catch (LogicaException e) {
			e.printStackTrace();
		}
	}
	
}
