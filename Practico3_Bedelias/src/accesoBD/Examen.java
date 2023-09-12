package accesoBD;

public class Examen {
	String codigo ;
	String materia ;
	String periodo;
	
	public Examen(String codigo, String materia, String periodo) {
		super();
		this.codigo = codigo;
		this.materia = materia;
		this.periodo = periodo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
