package accesoBD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class accesoBD {
//	
	public List<Examen> listarExamenes (){
		List<Examen> examenes = new ArrayList<Examen>();
		Connection con = this.crearConeccion();
		if (con != null) {
			Consultas cons = new Consultas();
			String query = cons.listarExamenes();
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					Examen examen = new Examen(rs.getString(1),rs.getString(2),rs.getString(3));
					examenes.add(examen);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.finalizoConeccion(con);

		}else {
			System.out.println("La conexion esta mal");
		}
		return examenes;
	}
	/* devuelve un listado de todos los exámenes registrados en la BD */
	/* de cada examen se tiene código, asignatura y período */
	
	
	public void ingresarResultado (Resultado resu) {
		Connection con = this.crearConeccion();
		if (con != null) {
			Consultas cons = new Consultas();
			String query = cons.insertarResultado();
			try {
				PreparedStatement ptmt = con.prepareStatement(query);
				ptmt.setInt(1, resu.getCedula());
				ptmt.setString(2, resu.getCodigo());
				ptmt.setInt(3, resu.getCalificacion());
				ptmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finalizoConeccion(con);
		}
	}
	/* ingresa el resultado de un examen en la BD, los datos del */
	/* resultado vienen almacenados en el objeto resu */

	
	public List <Resultado> listarResultados (int cedula){
		List<Resultado> resu = new ArrayList<Resultado>();
		Connection con = this.crearConeccion();
		if (con != null) {
			Consultas cons = new Consultas();
			String query = cons.listarResultados();
			PreparedStatement ptmt;
			try {
				ptmt = con.prepareStatement(query);
				ptmt.setInt(1, cedula);
				ResultSet rs = ptmt.executeQuery();
				while(rs.next()) {
					Resultado r = new Resultado(rs.getInt(1),rs.getString(2),rs.getInt(3));
					resu.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.finalizoConeccion(con);

		}else {
			System.out.println("La conexion esta mal");
		}
		return resu;
	}
	/* devuelve un listado de todos los exámenes registrados en la BD */
	/* correspondientes al número de cédula ingresado. En caso de no */
	/* haber ninguno, devuelve el listado vacío */
	
	
	
	private Connection crearConeccion() {
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
		/* 1. cargo dinamicamente el driver de MySQL */
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,usuario, pass);
			return con;
		} catch (ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		

	}
	
	private void finalizoConeccion(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
