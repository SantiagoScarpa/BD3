import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class PruebaAccesoBD
{
	public static String getNomRespaldo()throws Exception{
		Properties prop = new Properties();
		String nomArch = "config/config.properties";
		String ruta = null; 
		try {
			prop.load(new FileInputStream(nomArch));
			ruta = prop.getProperty("backupFile");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!(ruta==null))
			return ruta;
		else
			throw new Exception ("ERROR_AL_OBTENER_RUTA");
	}
	
	public static void main (String[] args)
	{
		try
		{
			/* primer programa de prueba para ejemplo de acceso a MySQL desde Java */
			/* accede a una base de datos de MySQL llamada Prueba que contiene una tabla llamada Personas */
			/* dentro de dicha tabla hay una columna llamada nombre */
			Properties prop = new Properties();
			String nomArch = "config/config.properties.txt";
			String driver = null;
			String url = null;
			String usuario = null;
			String pass = null;
			try {
				prop.load(new FileInputStream(nomArch));
				driver 	= prop.getProperty("driver");
				url 	= prop.getProperty("url");
				usuario = prop.getProperty("usuario");
				pass 	= prop.getProperty("password");
			} catch (IOException e) {
				System.out.println("Error al leer archivo de conexion, contacte al administrador");
			}
			if (driver == null || url == null || usuario == null || pass == null)
				System.out.println("Error al leer archivo de conexion, contacte al administrador");

			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);

			/* 2. una vez cargado el driver, me conecto con la base de datos */
			Connection con = DriverManager.getConnection(url,usuario, pass);

			/* 3. creo un PreparedStatement para insertar una persona en base de datos */
			String insert = "INSERT INTO Personas VALUES (?)";
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setString(1, "Julia");

			/* 4. ejecuto la sentencia de insercion y cierro el PreparedStatement */
			int cant = pstmt.executeUpdate();
			pstmt.close();
			System.out.print("Resultado de " + insert + ": ");
			System.out.println(cant + " filas afectadas");

			/* 5. creo un Statement para listar todas las personas de la base de datos */
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM Personas";

			/* 6. ejecuto la consulta, listo las personas y cierro el ResultSet y el Statement */
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Resultado de " + query);
			while (rs.next())
			{
				System.out.println("Nombre = " + rs.getString("nombre").trim());
			}
			rs.close();
			stmt.close();

			/* 7. por ultimo, cierro la conexion con la base de datos */
			con.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}
}
