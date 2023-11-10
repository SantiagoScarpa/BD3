package Grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Grafica.ventanas.Principal;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionJuguete;
import logica.excepciones.ExcepcionNino;
import logica.excepciones.ExcepcionPersistencia;
import logica.IFachada;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VOJuguete2;
import logica.valueObjects.VONino;

public class controladora {

	private IFachada fachada;
	private Principal winPrincipal;
	
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
			
			winPrincipal = new Principal();
			winPrincipal.setControladora(this);
			winPrincipal.setVisible(true);
			cargoListaNino();
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new ExcepcionGenerica("Error de conexión al servidor, contacte al administrador ");
		}
	}
	
	private boolean esNumerico(String texto) {
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (!(c >= '0' && c <= '9'))
				return false;
		}
		return true;
	}
	
	private boolean esAlfabetico(String texto) {
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' '))
				return false;
		}
		return true;
	}
	
	
	//METODOS DE FUNCIONALIDADES
	public void ingresoNuevoNino(String ciStr, String nom, String ape) {
		try {
			if (esNumerico(ciStr) && esAlfabetico(nom) && esAlfabetico(ape) && !ciStr.isEmpty() && !nom.isEmpty() && !ape.isEmpty()) {
				int ci = Integer.parseInt(ciStr);
				VONino vNino = new VONino(ci,nom,ape);
				fachada.nuevoNino(vNino);
				JOptionPane.showMessageDialog (null, "Niño creado", "Peticion realizada", JOptionPane.INFORMATION_MESSAGE);	
				winPrincipal.resetNuevoNino();
			} else
				JOptionPane.showMessageDialog (null, "Los datos ingresados no son validos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
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
			if (esNumerico(ciStr) && !ciStr.isEmpty() && !desc.isEmpty()) {
				int ci = Integer.parseInt(ciStr);
				VOJuguete vJuguete = new VOJuguete(desc, ci);
				fachada.nuevoJuguete(vJuguete);
				JOptionPane.showMessageDialog (null, "Juguete creado", "Peticion realizada", JOptionPane.INFORMATION_MESSAGE);
				winPrincipal.resetNuevoJuguete();
			} else
				JOptionPane.showMessageDialog (null, "Los datos ingresados no son validos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, "Error al conectar al servidor, contacte al administrador","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionGenerica e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionNino e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e4) {
			JOptionPane.showMessageDialog (null, "CI debe tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargoListaNino() {
		try {
			List<VONino> lista = new ArrayList<VONino>();
			lista = fachada.listarNinos();
			String[] colMedHdr = { "CEDULA","NOMBRE","APELLIDO"};
			DefaultTableModel tblModel = new DefaultTableModel(colMedHdr, 0);
			JTable tblMen = new JTable(tblModel);
			for (VONino j : lista) {
				Object[] data = { j.getCedula(), j.getNombre(),j.getApellido()};
				tblModel.addRow(data);
			}
			winPrincipal.cargarTablaNinos(tblMen);
		}catch (ExcepcionGenerica e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, "Error al conectar al servidor, contacte al administrador","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargoListaJuguetes(String ciStr) {
		try {
			int ci = Integer.parseInt(ciStr);
			List<VOJuguete2> lista = new ArrayList<VOJuguete2>();
			lista = fachada.listarJuguetes(ci);
			String[] colMedHdr = { "NUMERO","DESCRIPCION","DUEÑO"};
			DefaultTableModel tblModel = new DefaultTableModel(colMedHdr, 0);
			JTable tblMen = new JTable(tblModel);
			for (VOJuguete2 j : lista) {
				//en teoria no tendriamos porque mostrar la CI del niño ya que estas buscando por el mismo
				//pero si no lo ponemos estariamos incumpliando en la funcionalidad del VO y deberiamos crear otro nuevo
				//como solo es un ejercicio de practico no vamos a crear uno nuevo.... asi que lo muestro 
				Object[] data = { j.getNumero(), j.getDescripcion(),j.getCedulaNino()};
				tblModel.addRow(data);
			}
			if(lista.isEmpty())
				JOptionPane.showMessageDialog (null, "No hay juguetes asignado a la ci","Ha ocurrido un error", JOptionPane.INFORMATION_MESSAGE);
			winPrincipal.cargarTablaJuguetes(tblMen);
		}catch (ExcepcionGenerica e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, "Error al conectar al servidor, contacte al administrador","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e4) {
			JOptionPane.showMessageDialog (null, "CI debe tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch (ExcepcionNino e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String darDescripcion(String ciStr, String numString) {
		String desc = null; 
		try {
			if (esNumerico(ciStr) && esNumerico(numString) && !ciStr.isEmpty() && !numString.isEmpty()) {
				int ci = Integer.parseInt(ciStr);
				int num = Integer.parseInt(numString);
				desc = fachada.darDescripcion(ci, num);
			} else
				JOptionPane.showMessageDialog (null, "Los datos ingresados no son validos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, "Error al conectar al servidor, contacte al administrador","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionGenerica e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionNino e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionJuguete e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e4) {
			JOptionPane.showMessageDialog (null, "CI y Numero deben tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
		return desc;
	}

	public void borrarNino(String ciStr) {
		try {
			if (esNumerico(ciStr) && !ciStr.isEmpty()) {
				int ci = Integer.parseInt(ciStr);
				fachada.borrarNinoJuguetes(ci);
				JOptionPane.showMessageDialog (null, "Niño borrado", "Peticion realizada", JOptionPane.INFORMATION_MESSAGE);	
				winPrincipal.resetBorrarNino();
			} else
				JOptionPane.showMessageDialog (null, "Los datos ingresados no son validos", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog (null, "CI debe tener un valor numerico","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, "Error al conectar al servidor, contacte al administrador","Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionGenerica e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionNino e) {
			JOptionPane.showMessageDialog (null, e.darMensaje(),"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
