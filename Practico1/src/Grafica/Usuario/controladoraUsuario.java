package Grafica.Usuario;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logica.IFachadaUser;
import Logica.IFachadaUsuario;

public class controladoraUsuario {
	private IFachadaUsuario fachadaUsu;
	private envioMensaje winUsuario;
	private listaMensajes winMensajes;

	public controladoraUsuario() throws Exception{
		try {
			String ip = "localhost"; 
			String port = "40";
			
			String path = "//" + ip.trim() + ":" + port.trim() + "/practico1";
			fachadaUsu = (IFachadaUsuario) Naming.lookup(path);

			winUsuario = new envioMensaje();
			winUsuario.setControladora(this);
			winUsuario.setVisible(true);
			
			winMensajes = new listaMensajes();
			winMensajes.setControladora(this);
			winMensajes.setVisible(false);
		
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String mensaje){
		if(!mensaje.isEmpty()) {
			try {
				fachadaUsu.ingrasarMensaje(mensaje);
				winUsuario.setMessage("Bien");
			}catch(Exception e){
				e.printStackTrace();
				winUsuario.setMessage("Error envio");
			}
		}else {
			winUsuario.setMessage("Mensaje no puede estar vacio");
		}
	}
	
	public void mostrarMensajes() {
		winUsuario.salir();
		winMensajes.setVisible(true);
		
		try {
			ArrayList<String> lista = new ArrayList<String>();
			lista = fachadaUsu.listarMensajes();
			String[] colMedHdr = { "MENSAJE"};
			DefaultTableModel tblModel = new DefaultTableModel(colMedHdr, 0);
			JTable tblMen = new JTable(tblModel);
			for (String j : lista) {
				Object[] data = { j};
				tblModel.addRow(data);
			}
			winMensajes.cargaMensajes(tblMen);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void mosrtarEnvioMensaje() {
		winMensajes.salir();
		winUsuario.setVisible(true);
	}
	
}
