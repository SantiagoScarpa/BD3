package persistencia.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import logica.Juguete;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.poolConexiones.IConexion;

public class DAOJuguetesArchivo implements IDAOJuguetes {
	private int cedulaNino;
	private String nombreArchivo;
	
	private DAOJuguetesArchivo(int ci) {
		cedulaNino = ci;
		nombreArchivo = "juguetes-"+ci+".txt";
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

		return largo;
	}

	@Override
	public void borrarJuguetes(IConexion icon) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		
	}

}
