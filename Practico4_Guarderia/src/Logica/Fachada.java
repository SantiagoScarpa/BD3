package Logica;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.IPersistencia;


public class Fachada extends UnicastRemoteObject implements  IFachadaAdmin{
  /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private ArrayList<String> mensajes;
	private static Fachada instancia;

  private Fachada() throws RemoteException{
	  mensajes = new ArrayList<String>();
  }
  
  public static Fachada getInstancia() throws RemoteException {
	if (instancia == null) {
		instancia = new Fachada();
	}
	return instancia;
  }

  public void ingrasarMensaje(String s) throws Exception{
	mensajes.add(s);  
  }
  
  public ArrayList<String> listarMensajes() throws Exception{
	  return mensajes;	  
  }
  
  private IPersistencia lookupBD() throws Exception{
	IPersistencia pers;
	String pathBD = "//" + "192.168.1.11" + ":" + "43" + "/practico1BD";
	return pers =(IPersistencia) Naming.lookup(pathBD);
  }
  public void respaldarMensajes() throws Exception{
	  lookupBD().guardarMensajes(mensajes);
  }
  
  public void recuperarMensajes() throws Exception{
	 mensajes = lookupBD().levantarMensajes();
  }
}
