package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import logica.Persona;
import logica.VOPersona;

public class DAOPersonas {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public DAOPersonas() throws ExcepcionPersistencia {
		try {
			factory = Persistence.createEntityManagerFactory("pruebaJPA");
			manager = factory.createEntityManager();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExcepcionPersistencia("error persistencia");
		}
	}

	public Persona find(int i) throws ExcepcionPersistencia{
		try {
			return (Persona) manager.find(Persona.class, i);
		}catch(Exception e) {
			throw new ExcepcionPersistencia("error persistencia");
		}
	}

	public boolean member(int i) throws ExcepcionPersistencia{
		try{
			Persona p = find(i);
			if(p==null)
				return false;
			else
				return true;
		}catch(Exception e) {
			throw new ExcepcionPersistencia("error persistencia");
		}
	}

	public void insert(Persona p) throws ExcepcionPersistencia{
		try {
			manager.persist(p);			
		}catch(Exception e) {
			throw new ExcepcionPersistencia("error persistencia");
		}
	}



	public List<VOPersona> listarMayores(int e)throws ExcepcionPersistencia{
		try {
			Query query = manager.createNamedQuery("Persona.personaMayorA", Persona.class);
			@SuppressWarnings("unchecked")
			List<Persona> lista = query.getResultList();
			List<VOPersona> listaVOp= new ArrayList<VOPersona>();
			for (Persona p:lista){
				listaVOp.add(new VOPersona(p.getCedula(),p.getNombre(),p.getEdad()));
			}
			return listaVOp;
		}catch(Exception ex) {
			throw new ExcepcionPersistencia("error persistencia");
		}
	}
	
}
