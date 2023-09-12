package accesoBD;

public class Consultas {

	public String listarExamenes() {
		return "SELECT * FROM Examenes";
	}
	
	public String insertarResultado (){
		return "INSERT INTO Resultados VALUES(?, ?, ?)";
	}
}
