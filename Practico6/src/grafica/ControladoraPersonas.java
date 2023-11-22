package grafica;

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
		} catch (LogicaException | ExcepcionPersistencia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
