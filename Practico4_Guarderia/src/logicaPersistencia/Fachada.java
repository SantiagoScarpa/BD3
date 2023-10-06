package logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
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
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VOJuguete2;
import logicaPersistencia.valueObjects.VONino;

public class Fachada {
		
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
			url 	= prop.getProperty("url");
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

	public Fachada() {
		
	}
	public void nuevoNino(VONino vNino)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino {
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		if(!acc.existeNino(con, vNino.getCedula()))
			acc.nuevoNino(con, vNino);
		else
			throw new ExcepcionNino("Nino ya existe en el sistema");

		finalizoConeccion(con);
	}
	
	public void nuevoJuguete(String desc, VOJuguete vJuguete)throws ExcepcionGenerica,ExcepcionPersistencia, ExcepcionNino {
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		int ci = vJuguete.getCedulaNino();
		if(acc.existeNino(con, ci)) {
			int num = acc.obtengoNumJuguete(con, ci);
			VOJuguete2 vJuguete2 = new VOJuguete2(num+1,vJuguete.getDescripcion(),ci);
			acc.nuevoJuguete(con, vJuguete2);
		}
		else
			throw new ExcepcionNino("Nino no existe en el sistema");

		finalizoConeccion(con);
	}
	
	public List<VONino> listarNinos() throws ExcepcionPersistencia, ExcepcionGenerica{
		List<VONino> lista = new ArrayList<VONino>();
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		lista = acc.listarNinos(con);
		finalizoConeccion(con);
		return lista;
	}
	
	public List<VOJuguete2> listarJuguetes(int ci) throws ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino{
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		if(acc.existeNino(con, ci))
			lista = acc.listarJuguetes(con,ci);
		else
			throw new ExcepcionNino("Nino no existe en el sistema");
		
		finalizoConeccion(con);
		return lista;
		
		
	}
	
	public String darDescripcion(int ci, int num) throws ExcepcionPersistencia, ExcepcionGenerica, ExcepcionNino, ExcepcionJuguete {
		String desc = null;
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		if(acc.existeNino(con, ci)) {
			desc = acc.darDescripcion(con, ci, num);
			if(desc == null)
				throw new ExcepcionJuguete("Juguete no existe en el sistema");
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
			
		finalizoConeccion(con);	
		return desc;
	}
	
	public void borrarNinoJuguetes(int ci) throws ExcepcionNino, ExcepcionPersistencia, ExcepcionGenerica {
		accesoBD acc = new accesoBD();
		Connection con = crearConeccion();
		if(acc.existeNino(con, ci)) {
			acc.borrarJuguetes(con, ci);
			acc.borrarNino(con, ci);
			
		}else {
			throw new ExcepcionNino("Nino no existe en el sistema");
		}
		finalizoConeccion(con);
	}
	
}
