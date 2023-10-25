package persistencia.daos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import logica.Nino;
import logica.valueObjects.VONino;

public class DAONinos {
	private String driver;
	private String url;
	private String usuario;
	private String pass;
	
	public DAONinos() {
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		// Variables de conexion a BD
		driver = null;
		url = null;
		usuario = null;
		pass = null;
		
		try {
			prop.load(new FileInputStream(nomArch));
			driver 	= prop.getProperty("driver");
			url 	= prop.getProperty("url");
			usuario = prop.getProperty("usuario");
			pass 	= prop.getProperty("password");
			
		} catch (IOException e) {
			System.out.println("Error al leer archivo de conexion 01, contacte al administrador");
		}
		
		if (driver == null || url == null || usuario == null || pass == null)
			System.out.println("Error al leer archivo de conexion 02, contacte al administrador");
		
		/* 1. cargo dinamicamente el driver de MySQL */
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Error carga driver");
		}		
	}
	
	public boolean member(int cedula) {
		return false;
	}
	
	public void insert (Nino nino) {
		
	}
	
	public Nino find(int ci) {
		return null;	
	}
	
	public void delete(int ci) {
		
	}
	
	public List<VONino> listarNinos(){
		return null;
	}
}
