package accesoBD;

public class Resultado {
	int cedula;
	String codigo;
	int calificacion;
	
	public Resultado(int cedula, String codigo, int calificacion) {
		super();
		this.cedula = cedula;
		this.codigo = codigo;
		this.calificacion = calificacion;
	}
	
	public int getCedula() {
		return cedula;
	}
	
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
}
