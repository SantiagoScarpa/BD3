package persistencia.daos;

import java.util.List;

import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VONino;
import persistencia.poolConexiones.IConexion;

public class DAONinosArchivo implements IDAONinos{

	@Override
	public boolean member(IConexion icon, int cedula) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(IConexion icon, Nino nino) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Nino find(IConexion icon, int ci) throws ExcepcionGenerica, ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(IConexion icon, int ci) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VONino> listarNinos(IConexion icon) throws ExcepcionPersistencia {
		// TODO Auto-generated method stub
		return null;
	}

}
