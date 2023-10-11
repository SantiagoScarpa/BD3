package logicaPersistencia.poolConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public interface IPoolConexiones {
	public IConexion obtenerConexion(boolean b);
	
	public void liberarConexion(IConexion con, boolean ok);
}
