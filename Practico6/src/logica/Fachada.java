package logica;

import java.util.List;

import persistencia.DAOPersonas;
import persistencia.ExcepcionPersistencia;

public class Fachada {
	private DAOPersonas daoP;
	
	public Fachada() {
		try {
			daoP = new DAOPersonas();
		} catch (ExcepcionPersistencia e) {
			e.printStackTrace();
		}
	}
	
	public void nuevaPersona(VOPersona voP) throws LogicaException, ExcepcionPersistencia {
		Persona p = new Persona(voP.getCedula(),voP.getNombre(),voP.getEdad());
		if(!daoP.member(p.getCedula())) {
			daoP.insert(p);
		}
		else {
			throw new LogicaException("error logica");
		} 
	}
	
		
	public VOPersona obtenerPersona(int i) throws LogicaException{
		VOPersona voP = null;
		try {
			if(daoP.member(i)) {
				Persona p = daoP.find(i);
				voP = new VOPersona(p.getCedula(),p.getNombre(),p.getEdad());
			}else
				throw new LogicaException("error logica");			
		} catch (ExcepcionPersistencia e) {
			e.printStackTrace();
		}
		return voP;
	}
	
	public List<VOPersona> listarMayores(int edad){
		try {
			return daoP.listarMayores(edad);
		} catch (ExcepcionPersistencia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
