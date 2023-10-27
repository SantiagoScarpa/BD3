package logica;

import java.util.List;

import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.daos.DAOJuguetes;

public class Nino {
	private int cedula;
	private String nombre;
	private String apellido;
	private DAOJuguetes secuencia;
	
	public Nino(int ci, String nom, String ape) throws ExcepcionGenerica, ExcepcionPersistencia {
		cedula = ci;
		nombre = nom;
		apellido = ape;
		secuencia = new DAOJuguetes(ci);
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
	
	public boolean tieneJuguete(int numJ) throws ExcepcionPersistencia {
		if(secuencia.kesimo(numJ) == null)
			return false;
		else
			return true;
	}
	
	public int cantidadJuguetes() throws ExcepcionPersistencia {
		return secuencia.largo();
	}
	
	public void addJuguete(Juguete j) throws ExcepcionPersistencia {
		secuencia.insback(j);
		
	}
	
	public Juguete obtenerJuguete(int numJ) throws ExcepcionPersistencia {
		return secuencia.kesimo(numJ);
	}
	
	public List<VOJuguete2> listarJuguetes() throws ExcepcionPersistencia{
		return secuencia.listarJuguetes();
	}

	public void borrarJuguetes() throws ExcepcionPersistencia {
		secuencia.borrarJuguetes();
	}
}
