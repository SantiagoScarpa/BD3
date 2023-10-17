package logicaPersistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class PoolConexiones implements IPoolConexiones{
	private String driver;
	private String url;
	private String user;
	private String password;

	private int nivelTransaccionalidad;
	private Conexion conexiones[];
	private int tamanio;
	private int creadas;
	private int tope;
	
	public PoolConexiones() {
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		driver = null;
		url = null;
		user = null;
		password = null;
		try {
			prop.load(new FileInputStream(nomArch));
			driver 		= prop.getProperty("driver");
			url 		= prop.getProperty("urlBD");
			user 		= prop.getProperty("usuario");
			password 	= prop.getProperty("password");
		} catch (IOException e) {
			System.out.println("Error al leer archivo de conexion, contacte al administrador");
		}
		if (driver == null || url == null || user == null || password == null)
			System.out.println("Error al leer archivo de conexion, contacte al administrador");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1 - Pool Conexiones");
		}
		
		nivelTransaccionalidad = 8;
		
		tamanio = 3;
		creadas = 0;
		tope    = 0;
		conexiones = new Conexion[tamanio];
	}

	public synchronized IConexion obtenerConexion(boolean b) {
		Conexion resu = null;
		while(resu ==null) {
			if(tope > 0 ) {
				tope -= 1;
				return conexiones[tope];
			}else {
				if(creadas<tamanio) {
					Connection con;
					try {
						con = DriverManager.getConnection(url,user, password);
						con.setAutoCommit(false);
						con.setTransactionIsolation(nivelTransaccionalidad);
						creadas +=1;
						resu =new Conexion(con);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog (null, "Error al conectarse a los datos - pool 1", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					try {
						this.wait();
//						wait();
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog (null, "Error al 	 - pool 2", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		return resu;
	} 
	
	public synchronized void liberarConexion(IConexion con, boolean ok) {
		conexiones[tope] = (Conexion) con;
		tope++;
		try {
			if(ok) {
				((Conexion) con).getConection().commit();
			}else {
				((Conexion) con).getConection().rollback();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog (null, "Error al conectarse a los datos - pool 3", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
		this.notifyAll();
	}
}
