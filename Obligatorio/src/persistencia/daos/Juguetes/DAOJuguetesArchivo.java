package persistencia.daos.Juguetes;

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
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.poolConexiones.IConexion;

public class DAOJuguetesArchivo implements IDAOJuguetes, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cedulaNino;
	private String nombreArchivo;
	
	public DAOJuguetesArchivo(int ci) throws ExcepcionPersistencia {
		cedulaNino = ci;
			
		//CREO ARCHIVO DE JUGUETES DE ESE NINO OBTENIENDO LA RUTA DEL CONFIG FILE
		Properties prop = new Properties();
		String nomConfig = "config/config.properties.txt";
		try {
			prop.load(new FileInputStream(nomConfig));
			nombreArchivo = prop.getProperty("FilesPath");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DJA100");
		}
		if(!(nombreArchivo==null)) {
			nombreArchivo = nombreArchivo + "juguetes-"+ci+".txt";
			try {
				//CHECKEO QUE NO EXISTA PARA CREAR EL ARCHIVO
				File fi = new File(nombreArchivo);
				if(!fi.exists()) {
					Files.createDirectories(Paths.get(nombreArchivo).getParent());
	
					//GUARDO UNA LINKED LIST VACIA
					LinkedList<Juguete> j = new LinkedList<Juguete>();
					FileOutputStream f = new FileOutputStream(nombreArchivo);
					ObjectOutputStream o = new ObjectOutputStream(f);
					
					o.writeObject(j);
					o.close();
					f.close();
				}
				
			} catch (IOException e) {
				throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DJA101");
			}
		}
		else
			throw new ExcepcionPersistencia("Error de persistencia, contacte al administrador - DJA102");
	}
	
	@Override
	public int obtengoNumJuguete(IConexion icon, int ci) throws ExcepcionPersistencia {
		int numero = 0;
		
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);	
			
			try{
				LinkedList<Juguete> j = new LinkedList<Juguete>();
				j = (LinkedList<Juguete>) o.readObject();
				
				//obtengo el numero del ultimo
				numero = j.getLast().getNumero();
				
			}catch (ClassNotFoundException e) {
				throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA01");
			}finally{
				o.close();
				f.close();
			}
		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA02");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA03");
		} 

		return numero;
	}

	@Override
	public void insback(IConexion icon, Juguete jug) throws ExcepcionPersistencia {
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);	
			try {
				//abro el archivo para tener la lista de juguetes ya guardados y agregar al nuevo
				LinkedList<Juguete> j  = (LinkedList<Juguete>) o.readObject();
				j.add(jug);
				
			 	//guardo arreglo con juguete nuevo
				FileOutputStream fp = new FileOutputStream(nombreArchivo);
				ObjectOutputStream op = new ObjectOutputStream(fp);
				op.writeObject(j);
				op.close();
				fp.close();
			} catch (ClassNotFoundException e) {
				throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA04");
			}finally{
				o.close();
				f.close();
				
			}
 		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA05");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA06");
		}
	}

	@Override
	public int largo(IConexion icon) throws ExcepcionPersistencia {
		int largo = 0;
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);	
			
			try{
				LinkedList<Juguete> j = new LinkedList<Juguete>();
				j = (LinkedList<Juguete>) o.readObject();
				
				//obtengo el largo del arreglo
				largo = j.size();
				
			}catch (ClassNotFoundException e) {
				throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA07");
			}finally{
				o.close();
				f.close();
			}
		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA08");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA09");
		} 

		return largo;
	}

	@Override
	public Juguete kesimo(IConexion icon, int numJuguete) throws ExcepcionPersistencia {
		Juguete itemJ = null;
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);	
			
			try{
				LinkedList<Juguete> j = new LinkedList<Juguete>();
				j = (LinkedList<Juguete>) o.readObject();
				
				//obtengo el juguete
				itemJ = j.get(numJuguete);
				
			}catch (ClassNotFoundException e) {
				throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA10");
			}finally{
				o.close();
				f.close();
			}
		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA11");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA12");
		} 

		return itemJ;
	}

	@Override
	public List<VOJuguete2> listarJuguetes(IConexion icon) throws ExcepcionPersistencia {
		List<VOJuguete2> listaJ = new ArrayList<VOJuguete2>();
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);	
			
			try{
				LinkedList<Juguete> j = new LinkedList<Juguete>();
				j = (LinkedList<Juguete>) o.readObject();
				
				for(int i =0; i < j.size(); i++) {
					VOJuguete2 voJug = new VOJuguete2(j.get(i).getNumero(), j.get(i).getDescripcion(), cedulaNino);
					listaJ.add(voJug);
				}
				
			}catch (ClassNotFoundException e) {
				throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA13");
			}finally{
				o.close();
				f.close();
			}
		} catch (FileNotFoundException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA14");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al conectarse con los datos - DJA15");
		} 

		return listaJ;
	}

	@Override
	public void borrarJuguetes(IConexion icon) throws ExcepcionPersistencia {
		Path borrado = Paths.get(nombreArchivo);
				
		try {
			Files.delete(borrado);
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al borrar con los datos - DJA16");
		}
	}

}
