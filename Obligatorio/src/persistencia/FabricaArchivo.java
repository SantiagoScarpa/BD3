package persistencia;

import logica.excepciones.ExcepcionPersistencia;
import persistencia.daos.Juguetes.DAOJuguetes;
import persistencia.daos.Juguetes.DAOJuguetesArchivo;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.DAONinosArchivo;
import persistencia.daos.Ninos.IDAONinos;

public class FabricaArchivo implements FabricaAbstracta {
	public IDAONinos crearIDAONinos() throws ExcepcionPersistencia {
		return new DAONinosArchivo();
	}
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) throws ExcepcionPersistencia {
		return new DAOJuguetesArchivo(ci);
	}

}
