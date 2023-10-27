package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import logica.Fachada;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionJuguete;
import logica.excepciones.ExcepcionNino;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VOJuguete2;
import logica.valueObjects.VONino;
import logica.IFachada;
import persistencia.daos.DAONinos;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia;
	private DAONinos daoN;
	
	private Fachada() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica {
		daoN = new DAONinos();
	}
	
	public static Fachada getInstancia () throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica {
		if (instancia == null) {
				instancia = new Fachada();
		}
		return instancia;
	}
	
	public void nuevoNino(VONino vNino)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino, RemoteException{
		System.out.println("ci=="+vNino.getCedula());
		if(!daoN.member(vNino.getCedula())) {
			System.out.println("adentro");
			Nino n = new Nino(vNino.getCedula(),vNino.getNombre(),vNino.getApellido());
			daoN.insert(n);
		}else
			throw new ExcepcionNino("Nino ya existe en el sistema");
	}
	
	public void nuevoJuguete( VOJuguete vJuguete)throws RemoteException,  ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino{
		int ci = vJuguete.getCedulaNino();
				
		if(daoN.member(ci)) {
			Nino n = daoN.find(ci);
			int numJ = n.cantidadJuguetes();
			Juguete j = new Juguete(numJ+1,vJuguete.getDescripcion());
			n.addJuguete(j);
			
		}else
			throw new ExcepcionNino("Nino no existe en el sistema");
	}
	
	public List<VONino> listarNinos() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica{
		List<VONino> lista = new ArrayList<VONino>();		
		lista = daoN.listarNinos();
		return lista;
	}
	
	public List<VOJuguete2> listarJuguetes(int ci) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino{
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();		
		if(daoN.member(ci)) {
			Nino n = daoN.find(ci);
			lista = n.listarJuguetes();
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
		
		return lista;
	}
	
	public String darDescripcion(int ci, int num) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete{
		String desc = null;
		if(daoN.member(ci)) {
			Nino n = daoN.find(ci);
			if(n.tieneJuguete(num)) {
				Juguete j = n.obtenerJuguete(num);
				desc = j.getDescripcion();
			}else {
				throw new ExcepcionJuguete("Nino no tiene juguete");
			}
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
		
		return desc;
	}
	
	public void borrarNinoJuguetes(int ci) throws  RemoteException,ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica{
		if(daoN.member(ci)) {
			Nino n = daoN.find(ci);
			n.borrarJuguetes();
			daoN.delete(ci);
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
	}

}
