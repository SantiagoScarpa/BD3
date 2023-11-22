package logica;

public class LogicaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public LogicaException(String m) {
		this.mensaje = m;
	}

	public String darMensaje() {
		return mensaje;
	}

}
