package logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.accesoBD.accesoBD;
import logicaPersistencia.excepciones.ExcepcionGenerica;
import logicaPersistencia.excepciones.ExcepcionJuguete;
import logicaPersistencia.excepciones.ExcepcionNino;
import logicaPersistencia.excepciones.ExcepcionPersistencia;
import logicaPersistencia.poolConexiones.Conexion;
import logicaPersistencia.poolConexiones.IConexion;
import logicaPersistencia.poolConexiones.IPoolConexiones;
import logicaPersistencia.poolConexiones.PoolConexiones;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VOJuguete2;
import logicaPersistencia.valueObjects.VONino;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia;
	private IPoolConexiones pool;
	
	private Fachada() throws RemoteException {
		pool = new PoolConexiones();
	}
		
	public static Fachada getInstancia () throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica {
		if (instancia == null) {
				instancia = new Fachada();
		}
		return instancia;
	}

	private Connection crearConeccion() throws ExcepcionPersistencia, ExcepcionGenerica{
		Connection con = null;
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		String driver = null;
		String url = null;
		String usuario = null;
		String pass = null;
		try {
			prop.load(new FileInputStream(nomArch));
			driver 	= prop.getProperty("driver");
			url 	= prop.getProperty("urlBD");
			usuario = prop.getProperty("usuario");
			pass 	= prop.getProperty("password");
		} catch (IOException e) {
			System.out.println("Error al leer archivo de conexion, contacte al administrador");
		}
		if (driver == null || url == null || usuario == null || pass == null)
			System.out.println("Error al leer archivo de conexion, contacte al administrador");
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,usuario, pass);
		} catch (ClassNotFoundException e ) {
			throw new ExcepcionGenerica("Error al conectarse a los datos");
		}catch(SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos");
		}
		return con;
	}
	
	private void finalizoConeccion(Connection c) throws ExcepcionPersistencia {
		if(c!=null) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ExcepcionPersistencia("Error al acceder a los datos");
			}
		}
	}

	public void nuevoNino(VONino vNino)throws RemoteException, ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino {
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);	
		//Connection con = ((Conexion) icon).getConection();
		if(!acc.existeNino(icon, vNino.getCedula())) {
			acc.nuevoNino(icon, vNino);
		} 
		else
			throw new ExcepcionNino("Nino ya existe en el sistema");

		pool.liberarConexion(icon, true);
		//finalizoConeccion(con);
	}

	public void nuevoJuguete(VOJuguete vJuguete)throws RemoteException, ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino {
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);	
		int ci = vJuguete.getCedulaNino();
		if(acc.existeNino(icon, ci)) {
			int num = acc.obtengoNumJuguete(icon, ci);
			VOJuguete2 vJuguete2 = new VOJuguete2(num+1,vJuguete.getDescripcion(),ci);
			acc.nuevoJuguete(icon, vJuguete2);
		}
		else
			throw new ExcepcionNino("Nino no existe en el sistema");

		pool.liberarConexion(icon, true);
	}

	public List<VONino> listarNinos() throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica{
		List<VONino> lista = new ArrayList<VONino>();
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);	
		lista = acc.listarNinos(icon);
		pool.liberarConexion(icon, true);	
		return lista;
	}
	
	public List<VOJuguete2> listarJuguetes(int ci) throws  RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino{
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);	
		if(acc.existeNino(icon, ci))
			lista = acc.listarJuguetes(icon,ci);
		else
			throw new ExcepcionNino("Nino no existe en el sistema");

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.liberarConexion(icon, true);	
		return lista;
		
	}
	
	public String darDescripcion(int ci, int num) throws RemoteException, ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete {
		String desc = null;
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);	
		if(acc.existeNino(icon, ci)) {
			desc = acc.darDescripcion(icon, ci, num);
			if(desc == null)
				throw new ExcepcionJuguete("Juguete no existe en el sistema");
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
			
		pool.liberarConexion(icon, true);	
		return desc;
	}
	
	public void borrarNinoJuguetes(int ci) throws RemoteException, ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica {
		accesoBD acc = new accesoBD();
		IConexion icon = pool.obtenerConexion(true);			
		if(acc.existeNino(icon, ci)) {
			acc.borrarJuguetes(icon, ci);
			acc.borrarNino(icon, ci);
			
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
		
		pool.liberarConexion(icon, true);	
	}
	
}
