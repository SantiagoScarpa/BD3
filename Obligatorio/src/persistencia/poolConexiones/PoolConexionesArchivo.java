package persistencia.poolConexiones;

import logica.excepciones.ExcepcionPersistencia;

public class PoolConexionesArchivo implements IPoolConexiones{
	private int cantLectores;
	private boolean escribiendo;
	
	public PoolConexionesArchivo() {
		cantLectores = 0;
		escribiendo = false;
	}
	
	/**
	 * Comienza lectura: espera si se esta escribiendo
	 * @throws ExcepcionGenerica Error generico
	 */
	private synchronized void comienzoLectura () throws ExcepcionPersistencia {
		while (escribiendo) {
			try {
				this.wait();
			} 
			catch (InterruptedException e) {
				throw new ExcepcionPersistencia("ERROR DEL SISTEMA PA01, POR MAS INFORMACION CONTACTE AL ADMINISTRADOR");
			}
		cantLectores++;
		}
	}
	
	/**
	 * Termina lectura: se decrementa la cantidad de lectores y se notifica a todos los que esperan que termino
	 * @throws ExcepcionGenerica Error generico
	 */
	private synchronized void terminoLectura () {
		cantLectores--;
		this.notifyAll();
	}
	
	/**
	 * Comienza escritura: espera mientras hay otro escribiendo o hay lectores leyendo y luego indica que va a escribir
	 * @throws ExcepcionGenerica Error generico
	 */
	private synchronized void comienzoEscritura () throws ExcepcionPersistencia {
		while (escribiendo || cantLectores>0) {
			try {
				this.wait();
			} 
			catch (InterruptedException e) {
				throw new ExcepcionPersistencia("ERROR DEL SISTEMA PA02, POR MAS INFORMACION CONTACTE AL ADMINISTRADOR");
			}
		}
		escribiendo = true;
	}
	
	
	/**
	 * Termina escritura: indica que ya no va a escribir y se notifica a todos los que esperan que termino
	 * @throws ExcepcionGenerica Error generico
	 */
	private synchronized void terminoEscritura () {
		escribiendo = false;
		this.notifyAll();
	}
	
	
	@Override
	public IConexion obtenerConexion(boolean b) throws ExcepcionPersistencia {
		IConexion icon=null;
		if(b) {
			comienzoEscritura();
		}else {
			comienzoLectura();
		}
		
		// Archivos no va a tener conexiones perse, por lo que devuelve nulo
		return icon;
	}
	
	

	@Override
	public void liberarConexion(IConexion con, boolean ok) throws ExcepcionPersistencia {
			
		if(escribiendo) {
			terminoEscritura();
		}else {
			terminoLectura();
		}
	}

}
