package persistencia.consultas;

public class Consultas {
	public String insertoNino() {
		return "INSERT INTO Ninos VALUES(?,?,?)";
	}
	
	public String existeNino() {
		return "SELECT * FROM Ninos WHERE cedula = ?";
	}
	
	public String insertoJuguete() {
		return "INSERT  INTO Juguetes VALUES(?,?,?)";
	}

	public String obtengoNumJuguete() {
		return "SELECT max(numero) FROM Juguetes WHERE cedulaNino = ?";
	}
	
	public String listoNino() {
		return "SELECT * FROM Ninos ORDER BY cedula";
	}
	
	public String listoJuguete() {
		return "SELECT * FROM Juguetes WHERE cedulaNino = ? ORDER BY numero";
	}
	
	public String descripcionJuguete() {
		return "SELECT descripcion FROM Juguetes WHERE cedulaNino = ? AND numero = ?";
	}
	
	public String borrarNino() {
		return "DELETE FROM Ninos WHERE cedula = ?";
	}
	
	public String borrarJuguetes() {
		return "DELETE FROM Juguetes WHERE cedulaNino = ?";
	}
}
