package persistencia.daos.Juguetes;

import java.util.List;

import logica.Juguete;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VOJuguete2;
import persistencia.poolConexiones.IConexion;

public interface IDAOJuguetes  {

	int obtengoNumJuguete(IConexion icon, int ci) throws ExcepcionPersistencia;

	void insback(IConexion icon, Juguete jug) throws ExcepcionPersistencia;

	int largo(IConexion icon) throws ExcepcionPersistencia;

	Juguete kesimo(IConexion icon, int numJuguete) throws ExcepcionPersistencia;

	List<VOJuguete2> listarJuguetes(IConexion icon) throws ExcepcionPersistencia;

	void borrarJuguetes(IConexion icon) throws ExcepcionPersistencia;

}