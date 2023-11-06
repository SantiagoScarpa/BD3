package persistencia.daos.Ninos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import logica.Juguete;
import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VONino;
import persistencia.poolConexiones.IConexion;

public class DAONinosArchivo implements IDAONinos, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		boolean existe = false;
		String nombreArchivo = nombreRuta + "Nino-"+cedula+".txt";
		File f = new File(nombreArchivo);
		if(f.exists())
			existe = true;
		
		return existe;
	}

	@Override
	public void insert(IConexion icon, Nino nino) throws ExcepcionPersistencia {
		String nombreArchivo = nombreRuta + "Nino-"+nino.getCedula()+".txt";
		
		try {
			FileOutputStream f = new FileOutputStream(nombreArchivo);
			ObjectOutputStream o = new ObjectOutputStream(f);	
			o.writeObject(nino);
			f.close();
			o.close();
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA01");
		}
	}

	@Override
	public Nino find(IConexion icon, int ci) throws ExcepcionGenerica, ExcepcionPersistencia {
		Nino resu = null;
		String nombreArchivo = nombreRuta + "Nino-"+ci+".txt";
		
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);
						
			resu = (Nino) o.readObject();
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA02");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA03");
		} catch (ClassNotFoundException e) {
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DNA04");
		}
		
		return resu;
	}

	@Override
	public void delete(IConexion icon, int ci) throws ExcepcionPersistencia {
		String nombreArchivo = nombreRuta + "Nino-"+ci+".txt";
		Path borrado = Paths.get(nombreArchivo);
		try {
			Files.delete(borrado);
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al borrar con los datos - DNA05");
		}		
	}

	@Override
	public List<VONino> listarNinos(IConexion icon) throws ExcepcionPersistencia {
		List<VONino> lista = new ArrayList<VONino>();
		
		//obtengo lista de archivos
		File folder = new File(nombreRuta);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			//Valido que el archivo, es un archivo realmente y no una folder y que es de un Nino y no juguete
			if (file.isFile() && (file.getName().contains("Nino-"))) {
				try {
					FileInputStream f = new FileInputStream(file.getPath());
					ObjectInputStream o = new ObjectInputStream(f);
					Nino n = (Nino) o.readObject();
					VONino voN = new VONino(n.getCedula(),n.getNombre(),n.getApellido());
					lista.add(voN);
					o.close();
					f.close();
				} catch (IOException | ClassNotFoundException e) {
					throw new ExcepcionPersistencia("Error al borrar con los datos - DNA06");
				}
			}
		}
		
		return lista;
	}

}
