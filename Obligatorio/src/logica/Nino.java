package logica;

import java.util.List;

import logica.valueObjects.VOJuguete;
import persistencia.daos.DAOJuguetes;

public class Nino {
	private int cedula;
	private String nombre;
	private String apellido;
	private DAOJuguetes secuencia;
	
	public Nino() {
		// TODO Auto-generated constructor stub
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public DAOJuguetes getSecuencia() {
		return secuencia;
	}
	
	public boolean tieneJuguete(int numJ) {
		return false;
	}
	
	public int cantidadJuguetes() {
		return 1;
	}
	
	public void addJuguete(Juguete j) {
		
	}
	
	public Juguete obtenerJuguete(int numJ) {
		return null;
	}
	
	public List<VOJuguete> listarJuguetes(){
		return null;
	}

	public void borrarJuguetes() {
		
	}
}
