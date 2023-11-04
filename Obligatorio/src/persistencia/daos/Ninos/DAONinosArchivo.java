package persistencia.daos.Ninos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import logica.Juguete;
import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VONino;
import persistencia.poolConexiones.IConexion;

public class DAONinosArchivo implements IDAONinos{
	private String nombreRuta;
	
	public DAONinosArchivo() throws ExcepcionPersistencia {
		
		//GUARDO RUTA
		Properties prop = new Properties();
		String nomConfig = "config/config.properties.txt";
		try {
			prop.load(new FileInputStream(nomConfig));
			nombreRuta = prop.getProperty("FilesPath");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA100");
		}
		if(!(nombreRuta==null)) {
			try {
				//POR LAS DUDAS CREO DIRECTORIO SI NO EXISTE 
				Files.createDirectories(Paths.get(nombreRuta));
			} catch (IOException e) {
				throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA101");
			}
		}
		else
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA103");
	}
	
	@Override
	public boolean member(IConexion icon, int cedula) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(IConexion icon, Nino nino) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Nino find(IConexion icon, int ci) throws ExcepcionGenerica, ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(IConexion icon, int ci) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VONino> listarNinos(IConexion icon) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return null;
	}

}
