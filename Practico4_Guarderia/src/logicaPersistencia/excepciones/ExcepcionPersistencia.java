package logicaPersistencia.excepciones;

public class ExcepcionPersistencia extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ExcepcionPersistencia(String m) {
		this.mensaje = m;
	}
	
	public String darMensaje() {
		return mensaje;
	}

}
