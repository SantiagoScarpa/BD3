package Logica;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface IFachadaAdmin extends Remote {
	  
	  public ArrayList<String> listarMensajes() throws Exception;
	  
	  public void respaldarMensajes() throws Exception;
	  
	  public void recuperarMensajes() throws Exception;
}
