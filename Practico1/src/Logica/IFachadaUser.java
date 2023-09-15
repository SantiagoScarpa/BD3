package Logica;

import java.rmi.Remote;
import java.util.List;

public interface IFachadaUser extends Remote{
	public void ingrasarMensaje(String s);
	  
	  public List<String> listarMensajes() throws Exception;
}
