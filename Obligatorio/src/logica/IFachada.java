package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionJuguete;
import logica.excepciones.ExcepcionNino;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VOJuguete2;
import logica.valueObjects.VONino;

public interface IFachada extends Remote{
	
	public void nuevoNino(VONino vNino)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino, RemoteException ;
	
	public void nuevoJuguete( VOJuguete vJuguete)throws RemoteException,  ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino ;
	
	public List<VONino> listarNinos() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica;
	
	public List<VOJuguete2> listarJuguetes(int ci) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino;
	
	public String darDescripcion(int ci, int num) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete ;
	
	public void borrarNinoJuguetes(int ci) throws  RemoteException,ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica;
}
