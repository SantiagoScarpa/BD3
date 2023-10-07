package Grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.JOptionPane;

import Grafica.ventanas.NuevoJuguete;
import Grafica.ventanas.NuevoNino;
import logicaPersistencia.IFachada;
import logicaPersistencia.excepciones.ExcepcionGenerica;
import logicaPersistencia.excepciones.ExcepcionNino;
import logicaPersistencia.excepciones.ExcepcionPersistencia;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VONino;

public class controladora {
	private IFachada fachada;
	private NuevoNino winNuevoNino;
	private NuevoJuguete winNuevoJuguete;
	
	public controladora() throws ExcepcionPersistencia, ExcepcionGenerica {
		try {
			Properties prop = new Properties();
			String nomArch = "config/config.properties.txt";
			String ip = null;
			String port = null;
			try {
				prop.load(new FileInputStream(nomArch));
				ip = prop.getProperty("ip");
				port = prop.getProperty("port");
			} catch (IOException e) {
				throw new ExcepcionPersistencia("Error al leer archivo de conexion, contacte al administrador");
			}
			if (ip == null || port == null) 
				throw new ExcepcionPersistencia("Error al leer archivo de conexion, contacte al administrador-PUERTO");

			String path = "//" + ip.trim() + ":" + port.trim() + "/practico4";
			fachada = (IFachada) Naming.lookup(path);

			winNuevoNino = new NuevoNino();
			winNuevoNino.setControladora(this);
			winNuevoNino.setVisible(true);
			
			winNuevoJuguete = new NuevoJuguete();
			winNuevoJuguete.setControladora(this);
			winNuevoJuguete.setVisible(false);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new ExcepcionGenerica("Error de conexión al servidor, contacte al administrador ");
		}
	}
	
	
	public void mostrarNuevoNino() {
		cierroVentanas();
		winNuevoNino.setVisible(true);
	}
	
	public void mostrarNuevoJuguete() {
		cierroVentanas();
		winNuevoJuguete.setVisible(true);
		
	}
	
	private void cierroVentanas() {
		winNuevoNino.salir();
		winNuevoJuguete.salir();	
	}
	
	public void ingresoNuevoNino(String ciStr, String nom, String ape) {
			
		try {
			int ci = Integer.parseInt(ciStr);
			VONino vNino = new VONino(ci,nom,ape);
			fachada.nuevoNino(vNino);
			JOptionPane.showMessageDialog (null, "Nino creado", "Peticion realizada", JOptionPane.INFORMATION_MESSAGE);	
		} catch ( ExcepcionGenerica  e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(RemoteException e2) {
			JOptionPane.showMessageDialog (null, "Error al ejecutar accion", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
			
		}catch(ExcepcionPersistencia e1) {
			JOptionPane.showMessageDialog (null, e1.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionNino e3) {
			JOptionPane.showMessageDialog (null, e3.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e4) {
			JOptionPane.showMessageDialog (null, "CI debe tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ingresoNuevoJuguete(String desc, String ciStr) {
		try {
			int ci = Integer.parseInt(ciStr);
			VOJuguete vJuguete = new VOJuguete(desc, ci);
			fachada.nuevoJuguete(vJuguete);
			JOptionPane.showMessageDialog (null, "Juguete creado", "Peticion realizada", JOptionPane.OK_OPTION);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionGenerica e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionNino e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e4) {
			JOptionPane.showMessageDialog (null, "CI debe tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
