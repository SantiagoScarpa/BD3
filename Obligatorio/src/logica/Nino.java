package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.FabricaAbstracta;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.poolConexiones.IConexion;

public class Nino implements Serializable 	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	private String apellido;
	private IDAOJuguetes secuencia;
	
	public Nino(int ci, String nom, String ape) throws ExcepcionGenerica, ExcepcionPersistencia {
		//PREGUNTAR SI ESTA BIEN
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		String nomFabrica = null;
		try {
			prop.load(new FileInputStream(nomArch));
			nomFabrica = prop.getProperty("fabricaNom");
			
		} catch (IOException e) {
			throw new ExcepcionGenerica("Error al leer archivo de conexion N01, contacte al administrador");
		}
		
		if (nomFabrica == null)
			throw new ExcepcionGenerica("Error al crear pool conexiones N02, contacte al administrador");
		
		try {
			FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).newInstance();
			secuencia = fabrica.crearIDAOJuguetes(ci);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ExcepcionGenerica("Error al crear pool conexiones N03, contacte al administrador");
		}
			
		cedula = ci;
		nombre = nom;
		apellido = ape;
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
	
	public int obtengoNumJuguetes(IConexion icon) throws ExcepcionPersistencia {
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
