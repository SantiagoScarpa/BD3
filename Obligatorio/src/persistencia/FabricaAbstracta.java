package persistencia;

import logica.excepciones.ExcepcionPersistencia;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.IDAONinos;

public interface FabricaAbstracta {
	public IDAONinos crearIDAONinos() throws ExcepcionPersistencia;
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) throws ExcepcionPersistencia;
}
