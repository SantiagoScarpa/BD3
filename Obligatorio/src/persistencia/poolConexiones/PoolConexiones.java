package persistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import logica.excepciones.ExcepcionPersistencia;

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
	
	public PoolConexiones() throws ExcepcionPersistencia {
		Properties prop = new Properties();
		String nomArch = "config/config.properties.txt";
		driver = null;
		url = null;
		user = null;
		password = null;
		try {
			prop.load(new FileInputStream(nomArch));
			driver 		= prop.getProperty("driver");
			url 		= prop.getProperty("url");
			user 		= prop.getProperty("usuario");
			password 	= prop.getProperty("password");
		} catch (IOException e) {
			throw new ExcepcionPersistencia("Error al leer archivo de conexion P01, contacte al administrador");
		}
		if (driver == null || url == null || user == null || password == null)
			throw new ExcepcionPersistencia("Error al leer archivo de conexion P02, contacte al administrador");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExcepcionPersistencia("ERROR - Pool Conexiones P03");
		}
		
		nivelTransaccionalidad = 8;
		
		tamanio = 3;
		creadas = 0;
		tope    = 0;
		conexiones = new Conexion[tamanio];
	}

	public synchronized IConexion obtenerConexion(boolean b) throws ExcepcionPersistencia {
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
						throw new ExcepcionPersistencia("Error al conectarse a los datos - P04");
					}
				}else {
					try {
						this.wait();
					} catch (InterruptedException e) {
						throw new ExcepcionPersistencia("Error de conexion P05");
					}
				}
			}
		}
		return resu;
	} 
	
	public synchronized void liberarConexion(IConexion con, boolean ok) throws ExcepcionPersistencia {
		if(con != null) {
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
				throw new ExcepcionPersistencia("Error al conectarse a los datos - P06");
			}
		}
		this.notifyAll();
	}
}
