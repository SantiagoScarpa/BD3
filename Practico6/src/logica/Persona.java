package logica;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Persona.personaMayorA", query="SELECT p FROM Persona p WHERE p.edad > :edad")
public class Persona {
	@Id
	private int cedula;
	private String nombre;
	private int edad;
	
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
