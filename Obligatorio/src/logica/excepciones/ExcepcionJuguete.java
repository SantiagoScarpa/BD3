package logica.excepciones;

public class ExcepcionJuguete  extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
		
		public ExcepcionJuguete(String m) {
			this.mensaje = m;
		}
		
		public String darMensaje() {
			return mensaje;
			}
}
