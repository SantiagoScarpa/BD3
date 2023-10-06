package logicaPersistencia.valueObjects;

public class VOJuguete2 extends VOJuguete {
	int numero;
	
	public VOJuguete2(int num, String descripcion, int cedulaNino) {
		super(descripcion, cedulaNino);
		numero = num;
	}

	public int getNumero() {
		return numero;
	}
}
