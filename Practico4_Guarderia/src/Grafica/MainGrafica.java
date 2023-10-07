package Grafica;

import javax.swing.JOptionPane;

import Grafica.controladores.controladora;
import logicaPersistencia.excepciones.ExcepcionGenerica;
import logicaPersistencia.excepciones.ExcepcionPersistencia;

public class MainGrafica {
	public static void main(String[] args) {
		try {
			new controladora();
		} catch (ExcepcionPersistencia e) {
			JOptionPane.showMessageDialog(null, e.darMensaje(), "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionGenerica e) {
			JOptionPane.showMessageDialog(null, e.darMensaje(), "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
