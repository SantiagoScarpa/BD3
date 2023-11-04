package logica;

import java.util.List;

import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.daos.Juguetes.DAOJuguetes;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.poolConexiones.IConexion;

public class Nino {
	private int cedula;
	private String nombre;
	private String apellido;
	private IDAOJuguetes secuencia;
	
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
	
	public boolean tieneJuguete(IConexion icon,int numJ) throws ExcepcionPersistencia {
		if(secuencia.kesimo(icon, numJ) == null)
			return false;
		else
			return true;
	}
	
	public int cantidadJuguetes(IConexion icon) throws ExcepcionPersistencia {
		return secuencia.largo(icon);
	}
	
	public void addJuguete(IConexion icon, Juguete j) throws ExcepcionPersistencia {
		secuencia.insback(icon, j);
		
	}
	
	public Juguete obtenerJuguete(IConexion icon, int numJ) throws ExcepcionPersistencia {
		return secuencia.kesimo(icon, numJ);
	}
	
	public List<VOJuguete2> listarJuguetes(IConexion icon) throws ExcepcionPersistencia{
		return secuencia.listarJuguetes(icon);
	}

	public void borrarJuguetes(IConexion icon) throws ExcepcionPersistencia {
		secuencia.borrarJuguetes(icon);
	}
}
