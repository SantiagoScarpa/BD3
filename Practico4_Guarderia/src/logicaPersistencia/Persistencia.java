package logicaPersistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Logica.IFachadaAdmin;


public class Persistencia extends UnicastRemoteObject implements  IPersistencia{
	static final String ruta="C:\\Users\\santi\\Documents\\archivo.txt";
	
	private static Persistencia instancia;

	private Persistencia() throws RemoteException{
	  
	}
  
	public static Persistencia getInstancia() throws RemoteException {
		if (instancia == null) {
			instancia = new Persistencia();
		}
		return instancia;
	}
	
	public void guardarMensajes(ArrayList<String> s) throws Exception {
		try {
			FileOutputStream f = new FileOutputStream(ruta);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(s);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			//throw new Exception("ERROR_RESPALDO");
		}
	}
	
	public ArrayList<String> levantarMensajes() throws Exception{
		ArrayList<String> c= null;
		try {
			FileInputStream f = new FileInputStream(ruta);
			ObjectInputStream o = new ObjectInputStream(f);
			
			try {
				c  = (ArrayList<String>) o.readObject();
			} catch (ClassNotFoundException e) {
				throw new Exception("ERROR AL IMPORTAR");
			}finally { 
				o.close();
				f.close();
			}
				
		}catch(IOException e) {
			throw new Exception("ERROR ABRIR ARCHIVO IMPORTAR");
		}
		return c;		
	}
}
