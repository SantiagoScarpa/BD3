package logica;

import java.io.Serializable;

public class Juguete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String descripcion;
	
	public Juguete(int n, String desc) {
		numero 		= n;
		descripcion	= desc;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
