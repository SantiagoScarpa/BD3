package logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class MainLogica {
	
	public static void main(String[] args) {
		try
		{
			Properties prop = new Properties();
			String nomArch = "config/config.properties.txt";
			// Variables de conexion a BD
			String driver = null;
			String url = null;
			String usuario = null;
			String pass = null;
			
			/*Variables de conexion para RMI*/
			Fachada fac = Fachada.getInstancia();
			String ip = null; 
			String port = null;
			try {
				prop.load(new FileInputStream(nomArch));
				driver 	= prop.getProperty("driver");
				url 	= prop.getProperty("url");
				usuario = prop.getProperty("usuario");
				pass 	= prop.getProperty("password");
				
				/*Cargo variables para RMI*/
				ip = prop.getProperty("ip");
				port = prop.getProperty("port");
			} catch (IOException e) {
				System.out.println("Error al leer archivo de conexion, contacte al administrador");
			}
			if (driver == null || url == null || usuario == null || pass == null)
				System.out.println("Error al leer archivo de conexion, contacte al administrador");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			/* 2. una vez cargado el driver, me conecto con la base de datos */
			Connection con = 	DriverManager.getConnection(url,usuario, pass);

			//CREO BASE DE DATOS 
			Statement stmt = con.createStatement();
			String query = "CREATE DATABASE IF NOT EXISTS Guarderia";
			stmt.executeUpdate(query);
			
			query = "USE Guarderia";
			stmt.executeUpdate(query);
			
			query = "CREATE TABLE IF NOT EXISTS Ninos (cedula INT Primary Key, nombre VARCHAR(45), apellido VARCHAR(45))";
			stmt.executeUpdate(query);

			query = "CREATE TABLE IF NOT EXISTS Juguetes (numero INT, descripcion VARCHAR(45) NOT NULL, cedulaNino int , PRIMARY KEY (numero, cedulaNino), CONSTRAINT fk_nino FOREIGN KEY (cedulaNino) REFERENCES Ninos(cedula))";
			stmt.executeUpdate(query);
						
			//query = "INSERT INTO Ninos VALUES(1234567,'Kevin','McCallister'),(2345678,'Matilda','Wormwood'),(3456789,'Harry','Potter'),(4567890,'Merlina','Adams')";
			//stmt.executeUpdate(query);
			
			//Empiezo coneccion RMI
			LocateRegistry.createRegistry(Integer.parseInt(port));
			String path = "//"+ip.trim()+":"+port.trim()+"/practico4";
			Naming.rebind(path, fac);
			JOptionPane.showMessageDialog (null, "Servicio iniciado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(SQLException | ClassNotFoundException e ) {
			JOptionPane.showMessageDialog (null, "Error al conectarse a los datos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog (null, "Error al iniciar el servicio", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
		
}
}

