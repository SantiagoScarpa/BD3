package persistencia;

import logica.excepciones.ExcepcionPersistencia;
import persistencia.daos.Juguetes.DAOJuguetes;
import persistencia.daos.Juguetes.DAOJuguetesArchivo;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.DAONinosArchivo;
import persistencia.daos.Ninos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements FabricaAbstracta {
	public IDAONinos crearIDAONinos() throws ExcepcionPersistencia {
		return new DAONinosArchivo();
	}
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) throws ExcepcionPersistencia {
		return new DAOJuguetesArchivo(ci);
	}

	@Override
	public IPoolConexiones crearIPoolConexiones() throws ExcepcionPersistencia {
		//la excepcion aca no seria necesaria, pero se deja para que mantenga la uniformidad con la interface
		return new PoolConexionesArchivo();
	}

}
