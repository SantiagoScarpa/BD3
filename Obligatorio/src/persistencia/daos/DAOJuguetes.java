package persistencia.daos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logica.Juguete;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VOJuguete2;
import persistencia.consultas.Consultas;


public class DAOJuguetes {
	private String driver;
	private String url;
	private String usuario;
	private String pass;
	
	private int cedulaNino;
	
	public DAOJuguetes(int cedN) throws ExcepcionGenerica, ExcepcionPersistencia{
		cedulaNino = cedN;
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
			throw new ExcepcionGenerica("Error al leer archivo de conexion DJ01, contacte al administrador");
		}
		
		if (driver == null || url == null || usuario == null || pass == null)
			throw new ExcepcionGenerica("Error al leer archivo de conexion DJ02, contacte al administrador");
		
		/* 1. cargo dinamicamente el driver de MySQL */
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExcepcionPersistencia("Error en carga de driver DJ03, contacte al administrador");
		}	
	}
	
	public int obtengoNumJuguete(int ci) throws ExcepcionPersistencia  {
		int num = 0;
		Consultas consu = new Consultas();
		String query = consu.obtengoNumJuguete();
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				num = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ04");
		}
		return num;
	}
	
	public void insback(Juguete jug) throws ExcepcionPersistencia {		
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.insertoJuguete();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, jug.getNumero());
			pstmt.setString(2, jug.getDescripcion());
			pstmt.setInt(3, cedulaNino);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ05");
		}
	}
	
	public int largo() throws ExcepcionPersistencia {
		return obtengoNumJuguete(cedulaNino);
	}
	
	public Juguete kesimo(int numJuguete) throws ExcepcionPersistencia {
		Juguete j = null;
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.descripcionJuguete();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			pstmt.setInt(2,numJuguete);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				j = new Juguete(numJuguete,rs.getString(1));
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ06");
		}
		return j;
	}
	
	public List<VOJuguete2> listarJuguetes() throws ExcepcionPersistencia{
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.listoJuguete();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				VOJuguete2 voJ = new VOJuguete2(rs.getInt(1),rs.getString(2),cedulaNino);
				lista.add(voJ);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ07");
		}
		return lista;
	}
	
	public void borrarJuguetes() throws ExcepcionPersistencia {
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.borrarJuguetes();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ08");
		}
	}
	
	
}
