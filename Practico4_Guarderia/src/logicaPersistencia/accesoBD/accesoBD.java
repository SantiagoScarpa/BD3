package logicaPersistencia.accesoBD;

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

import logicaPersistencia.excepciones.ExcepcionPersistencia;
import logicaPersistencia.poolConexiones.IConexion;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VOJuguete2;
import logicaPersistencia.valueObjects.VONino;
import logicaPersistencia.poolConexiones.Conexion;

public class accesoBD {
	
	public boolean existeNino(IConexion icon, int ci) throws ExcepcionPersistencia {
		boolean existe = false;
		Consultas consu = new Consultas();
		String query = consu.existeNino();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				existe = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExcepcionPersistencia("Error al acceder a los datos 01");
		}
		return existe;
	}

	public void nuevoNino(IConexion icon, VONino n) throws ExcepcionPersistencia {
		Consultas consu = new Consultas();
		String query = consu.insertoNino();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, n.getCedula());
			pstmt.setString(2, n.getNombre());
			pstmt.setString(3, n.getApellido());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 02");
		}
	}
	
	public int obtengoNumJuguete(IConexion icon, int ci) throws ExcepcionPersistencia {
		int num = 0;
		Consultas consu = new Consultas();
		String query = consu.obtengoNumJuguete();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				num = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 03");
		}
		return num;
	}
	
	public void nuevoJuguete(IConexion icon, VOJuguete2 j)throws ExcepcionPersistencia  {
		Consultas consu = new Consultas();
		String query = consu.insertoJuguete();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,j.getNumero());
			pstmt.setString(2, j.getDescripcion());
			pstmt.setInt(3, j.getCedulaNino());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 04");
		}
	}
	
	public List<VONino> listarNinos(IConexion icon)throws ExcepcionPersistencia {
		List<VONino> listaN = new ArrayList<VONino>();
		Consultas consu = new Consultas();
		String query = consu.listoNino();
		Connection con = ((Conexion) icon).getConection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				VONino vNino = new VONino(rs.getInt(1),rs.getString(2),rs.getString(3));	
				listaN.add(vNino);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 05");
		}
		
		return listaN;
	}
	
	public List<VOJuguete2> listarJuguetes(IConexion icon, int ci)throws ExcepcionPersistencia {
		List<VOJuguete2> listaJ = new ArrayList<VOJuguete2>();
		Consultas consu = new Consultas();
		String query = consu.listoJuguete();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				VOJuguete2 vJuguete= new VOJuguete2(rs.getInt(1),rs.getString(2),rs.getInt(3));	
				listaJ.add(vJuguete);
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 06");
		}
		
		return listaJ;
	}
	
	public String darDescripcion(IConexion icon, int ci, int numJ) throws ExcepcionPersistencia {
		Consultas consu = new Consultas();
		String desc = null;
		String query = consu.descripcionJuguete();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			pstmt.setInt(2, numJ);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				desc = rs.getString(1);
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			throw new ExcepcionPersistencia("Problema de acceso a los datos 07");
		}
		return desc;
	}
	
	public void borrarJuguetes(IConexion icon, int ci) throws ExcepcionPersistencia {
		Consultas consu = new Consultas();
		String query = consu.borrarJuguetes();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 08");
		}
	}
	
	public void borrarNino(IConexion icon, int ci) throws ExcepcionPersistencia {
		Consultas consu = new Consultas();
		String query = consu.borrarNino();
		Connection con = ((Conexion) icon).getConection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ci);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new ExcepcionPersistencia("Error al acceder a los datos 09");
		}
	}
	
}
