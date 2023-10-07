package logicaPersistencia.accesoBD;

public class Consultas {
/*
 * 	El método nuevoNiño ingresa un nuevo niño al sistema, chequeando que no existiera.
 	El método nuevoJuguete ingresa un nuevo juguete al sistema, chequeando que el niño que le	
		corresponde esté registrado. El programa asignará automáticamente al nuevo juguete el número
		siguiente al del último juguete que ya tenía el niño. Por ejemplo, si tenía 5 juguetes, asignará el nº 6.
 	El método listarNiños devuelve un listado de todos los niños registrados, ordenado por cédula.
	El método listarJuguetes devuelve un listado de todos los juguetes de un niño determinado,
		(chequeando que dicho niño esté registrado) ordenado por número de juguete.
 	El método darDescripción devuelve la descripción de un juguete, dados su número y la cédula
		del niño que lo tiene asignado (chequeando que el niño exista y tenga un juguete con ese número).
	El método borrarNiñoJuguetes elimina del sistema al niño con la cédula ingresada, y	
		también elimina a todos sus juguetes, chequeando que el niño esté registrado
 * */
	public String insertoNino() {
		return "INSERT INTO Ninos VALUES(?,?,?)";
	}
	
	public String existeNino() {
		return "SELECT * FROM Ninos WHERE cedula = ?";
	}
	
	public String insertoJuguete() {
		return "INSERT  INTO Juguete VALUES(?,?,?)";
	}

	public String obtengoNumJuguete() {
		return "SELECT max(numero) FROM Juguete WHERE cedulaNino = ?";
	}
	
	public String listoNino() {
		return "SELECT * FROM Ninos ORDER BY cedula";
	}
	
	public String listoJuguete() {
		return "SELECT * FROM Juguete WHERE cedulaNino = ? ORDER BY numero";
	}
	
	public String descripcionJuguete() {
		return "SELECT descripcion FROM Juguete WHERE cedulaNino = ? AND numero = ?";
	}
	
	public String borrarNino() {
		return "DELETE FROM Ninos WHERE cedula = ?";
	}
	
	public String borrarJuguetes() {
		return "DELETE FROM Juguetes WHERE cedulaNino = ?";
	}
	
	
}
