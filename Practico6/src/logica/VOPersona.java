package logica;

public class VOPersona {
	private int cedula;
	private String nombre;
	private int edad;
	
	public VOPersona(int cedula, String nombre, int edad) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
	}
	public int getCedula() {
		return cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
}
