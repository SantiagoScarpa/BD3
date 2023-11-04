package persistencia.daos.Juguetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Juguete;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;


public class DAOJuguetes implements IDAOJuguetes {
	
	private int cedulaNino;
	
	public DAOJuguetes(int cedN) throws ExcepcionGenerica, ExcepcionPersistencia{
		cedulaNino = cedN;
	}
	
	public int obtengoNumJuguete(IConexion icon,int ci) throws ExcepcionPersistencia  {
		int num = 0;
		Consultas consu = new Consultas();
		String query = consu.obtengoNumJuguete();
		try {
			Connection con = ((Conexion) icon).getConection();
			
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
	
	public void insback(IConexion icon, Juguete jug) throws ExcepcionPersistencia {		
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.insertoJuguete();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, jug.getNumero());
			pstmt.setString(2, jug.getDescripcion());
			pstmt.setInt(3, cedulaNino);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ05");
		}
	}
	
	public int largo(IConexion icon) throws ExcepcionPersistencia {
		return obtengoNumJuguete(icon,cedulaNino);
	}
	
	public Juguete kesimo(IConexion icon,int numJuguete) throws ExcepcionPersistencia {
		Juguete j = null;
		try {
			Connection con = ((Conexion) icon).getConection();
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
			
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ06");
		}
		return j;
	}
	
	public List<VOJuguete2> listarJuguetes(IConexion icon) throws ExcepcionPersistencia{
		List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
		try {
			Connection con = ((Conexion) icon).getConection();
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
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ07");
		}
		return lista;
	}
	
	public void borrarJuguetes(IConexion icon) throws ExcepcionPersistencia {
		try {
			Connection con = ((Conexion) icon).getConection();
			Consultas consu = new Consultas();
			String query = consu.borrarJuguetes();
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos DJ08");
		}
	}
	
	
}
