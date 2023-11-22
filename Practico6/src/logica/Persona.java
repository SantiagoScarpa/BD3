package logica;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Persona.personaMayorA", query="SELECT p FROM Persona p WHERE p.edad > :edad")
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int cedula;
	private String nombre;
	private int edad;
	
	public Persona() {
		//solo para el JPA
	}
	
	public Persona(int cedula, String nombre, int edad) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}
