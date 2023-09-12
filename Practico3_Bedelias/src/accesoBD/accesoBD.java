package accesoBD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class accesoBD {
//	
	public List<Examen> listarExamenes (Connection con){
		List<Examen> examenes = new ArrayList<Examen>();
		Consultas cons = new Consultas();
		String query = cons.listarExamenes();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
		
		return examenes;
	}
	/* devuelve un listado de todos los exámenes registrados en la BD */
	/* de cada examen se tiene código, asignatura y período */
	
	
	public void ingresarResultado (Connection con, Resultado resu) {}
	/* ingresa el resultado de un examen en la BD, los datos del */
	/* resultado vienen almacenados en el objeto resu */

}
