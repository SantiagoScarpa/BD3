package logica.valueObjects;

import java.io.Serializable;

public class VOJuguete2 extends VOJuguete  implements Serializable {

	private static final long serialVersionUID = 1L;
	int numero;
	
	public VOJuguete2(int num, String descripcion, int cedulaNino) {
		super(descripcion, cedulaNino);
		numero = num;
	}

	public int getNumero() {
		return numero;
	}
}
