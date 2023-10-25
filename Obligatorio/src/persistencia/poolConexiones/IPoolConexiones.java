package persistencia.poolConexiones;


public interface IPoolConexiones {
	public IConexion obtenerConexion(boolean b);
	
	public void liberarConexion(IConexion con, boolean ok);
}
