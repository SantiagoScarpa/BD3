package Grafica.Admin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logica.IFachadaAdmin;

public class controladoraAdmin {
	private IFachadaAdmin fachadaAdmin;
	private listaMensajesAdmin winMensajes;
	private winMenuAdmin winMenu;
	
	public controladoraAdmin() throws Exception{
		try {
			String ip = "localhost"; 
			String port = "40";

			String path = "//" + ip.trim() + ":" + port.trim() + "/practico1";
			fachadaAdmin = (IFachadaAdmin) Naming.lookup(path);

			winMenu = new winMenuAdmin();
			winMenu.setControladora(this);
			winMenu.setVisible(true);
			
			winMensajes = new listaMensajesAdmin();
			winMensajes.setControladora(this);
			winMensajes.setVisible(false);

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void mostrarMensajes() {
		winMenu.salir();
		winMensajes.setVisible(true);
		
		try {
			ArrayList<String> lista = new ArrayList<String>();
			lista = fachadaAdmin.listarMensajes();
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
	
	public void mostrarMenu() {
		winMensajes.salir();
		winMenu.setVisible(true);
	}
	
	public void respaldoMensajes() {
		try {
			fachadaAdmin.respaldarMensajes();
			winMenu.setText("Respaldo satisfactorio");
		} catch (Exception e) {
			winMenu.setText("Error al respaldoar");
			e.printStackTrace();
		}
	}
	
	public void recuperoMensajes() {
		try {
			fachadaAdmin.recuperarMensajes();
			winMenu.setText("Mensajes recuperados");
		} catch (Exception e) {
			winMenu.setText("Error al recuperar");
			e.printStackTrace();
		}
	}
}
