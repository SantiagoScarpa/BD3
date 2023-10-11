package logicaPersistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import logicaPersistencia.excepciones.ExcepcionGenerica;
import logicaPersistencia.excepciones.ExcepcionPersistencia;

public class Conexion implements IConexion{
	private Connection con; 
	
	public Conexion(Connection c){
		con = c;
	}
	
	public Connection getConection() {
		return con;
	}
}
