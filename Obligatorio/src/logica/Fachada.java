package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logica.Fachada;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionJuguete;
import logica.excepciones.ExcepcionNino;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VOJuguete2;
import logica.valueObjects.VONino;
import logica.IFachada;
import persistencia.FabricaAbstracta;
import persistencia.daos.Ninos.DAONinos;
import persistencia.daos.Ninos.IDAONinos;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia;
	private IDAONinos daoN;
	private IPoolConexiones ipool;
	
	private Fachada() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica {
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		String nomFabrica = null;
		try {
			prop.load(new FileInputStream(nomArch));
			nomFabrica = prop.getProperty("fabricaNom");
			
		} catch (IOException e) {
			throw new ExcepcionGenerica("Error al leer archivo de conexion F01, contacte al administrador");
		}
		
		if (nomFabrica == null)
			throw new ExcepcionGenerica("Error al crear pool conexiones F01, contacte al administrador");
				
		try {
			FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).newInstance();
			daoN = fabrica.crearIDAONinos();
			ipool = fabrica.crearIPoolConexiones();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ExcepcionGenerica("Error al crear pool conexiones F03, contacte al administrador");
		}
			
	}
	
	public static Fachada getInstancia () throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica {
		if (instancia == null) {
				instancia = new Fachada();
		}
		return instancia;
	}
	
	public void nuevoNino(VONino vNino)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino, RemoteException{
		IConexion icon = null; 
		try {
		 icon = ipool.obtenerConexion(true);
		 if(!daoN.member(icon, vNino.getCedula())) {
			 Nino n = new Nino(vNino.getCedula(),vNino.getNombre(),vNino.getApellido());
			 daoN.insert(icon, n);
			 ipool.liberarConexion(icon, true);
		 }else {
			 ipool.liberarConexion(icon, false); // libero conexion antes de lanzar exception
			 throw new ExcepcionNino("Nino ya existe en el sistema");
		 }

		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
	}
	
	public void nuevoJuguete( VOJuguete vJuguete)throws RemoteException,  ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino{
		int ci = vJuguete.getCedulaNino();
		IConexion icon = null; 		
		try {
			icon = ipool.obtenerConexion(true);
			if(daoN.member(icon,ci)) {
				Nino n = daoN.find(icon,ci);
				int numJ = n.obtengoNumJuguetes(icon);
				Juguete j = new Juguete(numJ+1,vJuguete.getDescripcion());
				n.addJuguete(icon, j);
				ipool.liberarConexion(icon, true);

			}else {
				ipool.liberarConexion(icon, false); // libero conexion antes de lanzar exception
				throw new ExcepcionNino("Nino no existe en el sistema");
			}
		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
	}
	
	public List<VONino> listarNinos() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica{
		IConexion icon = null;
		try {
			icon = ipool.obtenerConexion(false);
			List<VONino> lista = new ArrayList<VONino>();		
			lista = daoN.listarNinos(icon);
			ipool.liberarConexion(icon, true);
			return lista;
		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
	}
	
	public List<VOJuguete2> listarJuguetes(int ci) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino{
		IConexion icon = null;
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
		try {
			icon = ipool.obtenerConexion(false);		
			if(daoN.member(icon,ci)) {
				Nino n = daoN.find(icon,ci);
				lista = n.listarJuguetes(icon);
				ipool.liberarConexion(icon, false);
			}else {
				ipool.liberarConexion(icon, false);
				throw new ExcepcionNino("Nino no existe en el sistema");
			}
		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
		return lista;
	}
	
	public String darDescripcion(int ci, int num) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete{
		IConexion icon = null;
		String desc = null;
		try {
			icon = ipool.obtenerConexion(false);
			if(daoN.member(icon,ci)) {
				Nino n = daoN.find(icon,ci);
				if(n.tieneJuguete(icon, num)) {
					Juguete j = n.obtenerJuguete(icon, num);
					desc = j.getDescripcion();
					ipool.liberarConexion(icon, false);
				}else {
					ipool.liberarConexion(icon, false);
					throw new ExcepcionJuguete("Nino no tiene juguete");
				}
			}else {
				ipool.liberarConexion(icon, false);
				throw new ExcepcionNino("Nino no existe en el sistema");
			}
		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
		return desc;
	}
	
	public void borrarNinoJuguetes(int ci) throws  RemoteException,ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica{
		IConexion icon = null;
		try {
			icon = ipool.obtenerConexion(true);
			if(daoN.member(icon,ci)) {
				Nino n = daoN.find(icon,ci);
				n.borrarJuguetes(icon);
				daoN.delete(icon,ci);
				ipool.liberarConexion(icon, true);
			}else {
				ipool.liberarConexion(icon, false);
				throw new ExcepcionNino("Nino no existe en el sistema");
			}
		}catch(ExcepcionPersistencia e) {
			ipool.liberarConexion(icon, false);
			throw new ExcepcionPersistencia(e.darMensaje());
		}
	}

}
