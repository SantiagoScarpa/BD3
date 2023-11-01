package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VONino;
import persistencia.poolConexiones.Conexion;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.IConexion;

public class DAONinos {
	
	public DAONinos() throws ExcepcionPersistencia, ExcepcionGenerica {

	}
	
	public boolean member(IConexion icon, int cedula) throws ExcepcionPersistencia {
		boolean existe = false;
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedula);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existe = true;
			
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN04");
		}
		
		return existe;
	}
	
	public void insert (IConexion icon,Nino nino) throws ExcepcionPersistencia {
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.insertoNino();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nino.getCedula());
			pstmt.setString(2, nino.getNombre());
			pstmt.setString(3, nino.getApellido());
			
			pstmt.executeUpdate();
			
			pstmt.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN05");
		}
		
	}
	
	public Nino find(IConexion icon,int ci) throws ExcepcionGenerica, ExcepcionPersistencia {
		Nino n = null;
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				n = new Nino(rs.getInt(1),rs.getString(2),rs.getString(3));
			
			pstmt.close();			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN06");
		}
		return n;	
	}
	
	//Precondicion, borrar primero los juguetes del nino
	public void delete(IConexion icon,int ci) throws ExcepcionPersistencia {
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.borrarNino();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN07");
		}
	}
	
	public List<VONino> listarNinos(IConexion icon) throws ExcepcionPersistencia{
		List<VONino> lista = new ArrayList<VONino>();
		try {
			Connection con = ((Conexion) icon).getConection();
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
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DN08");
		}
		return lista;
	}
}
