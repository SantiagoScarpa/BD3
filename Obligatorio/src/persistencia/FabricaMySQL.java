package persistencia;

import logica.excepciones.ExcepcionPersistencia;
import persistencia.daos.Juguetes.DAOJuguetes;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.DAONinos;
import persistencia.daos.Ninos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class FabricaMySQL implements FabricaAbstracta {
	
	public IDAONinos crearIDAONinos() {
		return new DAONinos();
	}
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) {
		return new DAOJuguetes(ci);
	}

	@Override
	public IPoolConexiones crearIPoolConexiones() throws ExcepcionPersistencia {
		return new PoolConexiones();
	}

}
