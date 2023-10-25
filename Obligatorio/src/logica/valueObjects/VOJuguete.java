package logica.valueObjects;

import java.io.Serializable;

public class VOJuguete  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	private int cedulaNino;
	public VOJuguete( String descripcion, int cedulaNino) {
		this.descripcion = descripcion;
		this.cedulaNino = cedulaNino;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public int getCedulaNino() {
		return cedulaNino;
	}
	
}
