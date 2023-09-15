package Persistencia;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface IPersistencia extends Remote{
	
	public void guardarMensajes(ArrayList<String> s) throws Exception ;

	public ArrayList<String> levantarMensajes() throws Exception;
}
