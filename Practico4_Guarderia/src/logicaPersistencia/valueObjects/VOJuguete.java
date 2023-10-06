package logicaPersistencia.valueObjects;

public class VOJuguete {
	
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
