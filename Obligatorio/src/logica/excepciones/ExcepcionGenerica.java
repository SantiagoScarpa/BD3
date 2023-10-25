package logica.excepciones;

public class ExcepcionGenerica extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
		
		public ExcepcionGenerica(String m) {
			this.mensaje = m;
		}
		
		public String darMensaje() {
			return mensaje;
			}
}
