package logicaPersistencia.valueObjects;

public class VOJuguete {
	private int numero;
	private String descripcion;
	private int ccedulaNino;
	public VOJuguete(int numero, String descripcion, int ccedulaNino) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.ccedulaNino = ccedulaNino;
	}
	public int getNumero() {
		return numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getCcedulaNino() {
		return ccedulaNino;
	}
	
}
