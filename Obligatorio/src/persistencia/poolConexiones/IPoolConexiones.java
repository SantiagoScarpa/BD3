package persistencia.poolConexiones;

import logica.excepciones.ExcepcionPersistencia;

public interface IPoolConexiones {
	public IConexion obtenerConexion(boolean b) throws ExcepcionPersistencia;
	
	public void liberarConexion(IConexion con, boolean ok) throws ExcepcionPersistencia;
}
