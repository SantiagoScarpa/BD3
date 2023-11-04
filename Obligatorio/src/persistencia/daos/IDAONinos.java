package persistencia.daos;

import java.util.List;

import logica.Nino;
import logica.excepciones.ExcepcionGenerica;
import logica.excepciones.ExcepcionPersistencia;
import logica.valueObjects.VONino;
import persistencia.poolConexiones.IConexion;

public interface IDAONinos {

	boolean member(IConexion icon, int cedula) throws ExcepcionPersistencia;

	void insert(IConexion icon, Nino nino) throws ExcepcionPersistencia;

	Nino find(IConexion icon, int ci) throws ExcepcionGenerica, ExcepcionPersistencia;

	//Precondicion, borrar primero los juguetes del nino
	void delete(IConexion icon, int ci) throws ExcepcionPersistencia;

	List<VONino> listarNinos(IConexion icon) throws ExcepcionPersistencia;

}