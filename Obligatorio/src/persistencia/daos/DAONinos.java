package persistencia.daos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import logica.valueObjects.VONino;
import persistencia.consultas.Consultas;

public class DAONinos {
	private String driver;
	private String url;
	private String usuario;
	private String pass;
	
	public DAONinos() throws ExcepcionPersistencia, ExcepcionGenerica {
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
			throw new ExcepcionGenerica("Error al leer archivo de conexion DN01, contacte al administrador");
		}
		
		if (driver == null || url == null || usuario == null || pass == null)
			System.out.println("Error al leer archivo de conexion DN02, contacte al administrador");
		
		/* 1. cargo dinamicamente el driver de MySQL */
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExcepcionPersistencia("Error en carga de driver DN03, contacte al administrador");
		}		
	}
	
	public boolean member(int cedula) throws ExcepcionPersistencia {
		boolean existe = false;
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedula);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existe = true;
			
			pstmt.close();
			con.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN04");
		}
		
		return existe;
	}
	
	public void insert (Nino nino) throws ExcepcionPersistencia {
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.insertoNino();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nino.getCedula());
			pstmt.setString(2, nino.getNombre());
			pstmt.setString(3, nino.getApellido());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN05");
		}
		
	}
	
	public Nino find(int ci) throws ExcepcionGenerica, ExcepcionPersistencia {
		Nino n = null;
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				n = new Nino(rs.getInt(1),rs.getString(2),rs.getString(3));
			
			pstmt.close();
			con.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN06");
		}
		return n;	
	}
	
	//Precondicion, borrar primero los juguetes del nino
	public void delete(int ci) throws ExcepcionPersistencia {
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.borrarNino();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN07");
		}
	}
	
	public List<VONino> listarNinos() throws ExcepcionPersistencia{
		List<VONino> lista = new ArrayList<VONino>();
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.listoNino();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				VONino voN = new VONino(rs.getInt(1),rs.getString(2),rs.getString(3));
				lista.add(voN);
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN08");
		}
		return lista;
	}
}
