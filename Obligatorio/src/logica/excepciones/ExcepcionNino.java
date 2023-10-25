package logica.excepciones;

public class ExcepcionNino  extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
		
		public ExcepcionNino(String m) {
			this.mensaje = m;
		}
		
		public String darMensaje() {
			return mensaje;
			}
}