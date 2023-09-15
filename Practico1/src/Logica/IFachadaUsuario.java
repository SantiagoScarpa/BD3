package Logica;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface IFachadaUsuario extends Remote{
	
	public void ingrasarMensaje(String s) throws Exception;
	  
	public ArrayList<String> listarMensajes() throws Exception;
}
