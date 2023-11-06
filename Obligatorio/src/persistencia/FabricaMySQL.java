package persistencia;

import persistencia.daos.Juguetes.DAOJuguetes;
import persistencia.daos.Juguetes.IDAOJuguetes;
import persistencia.daos.Ninos.DAONinos;
import persistencia.daos.Ninos.IDAONinos;

public class FabricaMySQL implements FabricaAbstracta {
	
	public IDAONinos crearIDAONinos() {
		return new DAONinos();
	}
	
	public IDAOJuguetes crearIDAOJuguetes(int ci) {
		return new DAOJuguetes(ci);
	}

}
