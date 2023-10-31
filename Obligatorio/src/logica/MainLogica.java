package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.swing.JOptionPane;

import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;

public class MainLogica {

	public static void main(String[] args) {
		try
		{
			Properties prop = new Properties();
			String nomArch = "config/config.properties.txt";
		
			
			/*Variables de conexion para RMI*/
			Fachada fac = Fachada.getInstancia();
			String ip = null; 
			String port = null;
			try {
				prop.load(new FileInputStream(nomArch));
				
				/*Cargo variables para RMI*/
				ip = prop.getProperty("ip");
				port = prop.getProperty("port");
			} catch (IOException e) {
				System.out.println("Error al leer archivo de conexion ML01, contacte al administrador");
			}
			
						
			//Empiezo coneccion RMI
			LocateRegistry.createRegistry(Integer.parseInt(port));
			String path = "//"+ip.trim()+":"+port.trim()+"/practico4";
			Naming.rebind(path, fac);
			JOptionPane.showMessageDialog (null, "Servicio iniciado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(ExcepcionGenerica | ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, "Error al conectarse a los datos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);

		}catch(Exception e2) {
			JOptionPane.showMessageDialog (null, "Error al iniciar el servicio", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
