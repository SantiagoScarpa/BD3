package Grafica;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.swing.JOptionPane;

import Logica.Fachada;


public class MainServer {
	
	// appServer
	public static void main(String[] args) throws Exception {
		try {
			Fachada cp = Fachada.getInstancia();
			String ip = "localhost"; 
			String port = "40";

			LocateRegistry.createRegistry(Integer.parseInt(port));
			String path = "//"+ip.trim()+":"+port.trim()+"/practico4";
			Naming.rebind(path, cp);
			JOptionPane.showMessageDialog (null, "Servicio iniciado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);

		}catch (Exception e) {
			JOptionPane.showMessageDialog (null, "Error al iniciar el servicio", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
}