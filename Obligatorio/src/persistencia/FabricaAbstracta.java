package persistencia;

import logica.excepciones.ExcepcionPersistencia;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;

public interface FabricaAbstracta {
	
	//crear ipool 
	public IPoolConexiones crearIPoolConexiones() throws ExcepcionPersistencia;
	
	public IDAONinos crearIDAONinos() throws ExcepcionPersistencia;
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) throws ExcepcionPersistencia;
}
