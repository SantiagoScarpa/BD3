package logicaPersistencia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.excepciones.ExcepcionGenerica;
import logicaPersistencia.excepciones.ExcepcionJuguete;
import logicaPersistencia.excepciones.ExcepcionNino;
import logicaPersistencia.excepciones.ExcepcionPersistencia;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VOJuguete2;
import logicaPersistencia.valueObjects.VONino;

public interface IFachada extends Remote{
	
	public void nuevoNino(VONino vNino)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino, RemoteException ;
	
	public void nuevoJuguete(String desc, VOJuguete vJuguete)throws RemoteException,  ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino ;
	
	public List<VONino> listarNinos() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica;
	
	public List<VOJuguete2> listarJuguetes(int ci) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino;
	
	public String darDescripcion(int ci, int num) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete ;
	
	public void borrarNinoJuguetes(int ci) throws  RemoteException,ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica;
}
