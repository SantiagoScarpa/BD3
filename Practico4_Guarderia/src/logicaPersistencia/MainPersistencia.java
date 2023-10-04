package logicaPersistencia;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.swing.JOptionPane;

public class MainPersistencia {
	public static void main(String[] args) {
		try {
			Persistencia per = Persistencia.getInstancia();
			String ip = "192.168.1.11"; 
			String portBD = "43";
		
			LocateRegistry.createRegistry(Integer.parseInt(portBD));
			String path = "//"+ip.trim()+":"+portBD.trim()+"/practico1BD";
			Naming.rebind(path, per);
			JOptionPane.showMessageDialog (null, "Servicio iniciado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog (null, "Error al iniciar el servicio", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
