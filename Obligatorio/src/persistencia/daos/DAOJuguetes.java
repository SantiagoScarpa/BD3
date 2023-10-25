package persistencia.daos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import logica.Juguetes.Juguete;
import logica.valueObjects.VOJuguete;
import persistencia.consultas.Consultas;


public class DAOJuguetes {
	private String driver;
	private String url;
	private String usuario;
	private String pass;
	
	private int cedulaNino;
	
	public DAOJuguetes(int cedN) {
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
	
	public int obtengoNumJuguete(int ci)  {
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
			//throw new ExcepcionPersistencia("Error al acceder a los datos 03");
		}
		return num;
	}
	
	public void insback(Juguete jug) {		
		try {
			Connection con = DriverManager.getConnection (url,usuario,pass);
			Consultas consu = new Consultas();
			String query = consu.insertoJuguete();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, jug.getNumero());
			pstmt.setString(2, jug.getDescripcion());
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			//throw new ExcepcionPersistencia("Error al acceder a los datos 03");
		}
	}
	
	public int largo() {
		return 1;
	}
	
	public Juguete kesimo(int i) {
		return null;
	}
	
	public List<VOJuguete> listarJuguetes(){
		return null;
	}
	
	public void borrarJuguetes() {
		
	}
	
	
}
