import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Ej2 {

	public static void main(String[] args) {
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

			//CREO BASE DE DATOS 
			Statement stmt = con.createStatement();
			String query = "CREATE DATABASE IF NOT EXISTS Escuela";
			stmt.executeUpdate(query);
			

			query = "USE Escuela";
			stmt.executeUpdate(query);
			
			//CREO TABLAS
//			Personas (cedula INT, nombre VARCHAR(45), apellido VARCHAR(45))
//			Maestras (cedula INT, grupo VARCHAR(45))
//			Alumnos (cedula INT, cedulaMaestra INT)
			
			query = "CREATE TABLE IF NOT EXISTS Personas (cedula INT Primary Key, nombre VARCHAR(45), apellido VARCHAR(45))";
			stmt.executeUpdate(query);
			
			query = "CREATE TABLE IF NOT EXISTS  Maestras (cedula INT Primary Key, grupo VARCHAR(45), constraint fk_PerMae Foreign key (cedula) references Personas(cedula))";
			stmt.executeUpdate(query);
			
			query = "CREATE TABLE IF NOT EXISTS  Alumnos (cedula INT Primary Key, cedulaMaestra INT, constraint fk_PerAlu Foreign key (cedula) references Personas(cedula), constraint fk_AluMae Foreign key (cedula) references Maestras(cedula))";
			stmt.executeUpdate(query);
			/*EJERCICIO 3 */
			/*System.out.print("Ingrese comando: ");
			Scanner inputScanner = new Scanner (System.in); //Creación de un objeto Scanner
	        String queryTeclado  = inputScanner.nextLine (); //Invocamos un método sobre un objeto Scanner
	        while(!queryTeclado.equals("exit")) {
	        	try {
	        		int cant = stmt.executeUpdate(queryTeclado); // INSERT INTO Personas VALUES (2, "nom 2" ,"ape 2");
	        		System.out.println("Cantidad de filas afectadas: "+cant);

	        	}catch(SQLException e) {
	        		System.out.println("Comando no valido");
	        	}
	        	System.out.print("Ingrese comando: ");
	        	queryTeclado  = inputScanner.nextLine ();

	        }
	        System.out.print("Hasta la proxima");*/
			
			
			/*EJERCICIO 4*/
			//PARTE A
			int maeMax = 0;
			int ciMaeMax = 0;
			query = "SELECT p.Cedula, p.Nombre, p.Apellido, COUNT(*) AS cantidad FROM Personas p, Alumnos a\r\n"
					+ "WHERE p.cedula = a.cedulaMaestra\r\n"
					+ "GROUP BY p.Cedula, p.Nombre, p.Apellido\r\n"
					+ "ORDER BY 4 desc LIMIT 1 ";
			
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Resultado de PARTE A");
			while (rs.next())
			{
				System.out.println("Cedula = " + rs.getInt("cedula")+" "+"Nombre = " + rs.getString("nombre").trim()+" "+"Apellido = " + rs.getString("apellido").trim());
			}
			rs.close();
			
			//PARTE B
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			try {
				query = "SELECT cedula FROM Maestras";
				rs = stmt.executeQuery(query); //Guardo lista de cedula de maestras
				while (rs.next())
				{
					String prepared = "SELECT COUNT(*) FROM Alumnos WHERE cedulaMaestra = ?";
					PreparedStatement pstmt = con.prepareStatement(prepared);
					int ciMaestra = rs.getInt("cedula");
					pstmt.setInt(1, ciMaestra);
					ResultSet rs2 = pstmt.executeQuery();
					while (rs2.next())
					{
						if (maeMax <= rs2.getInt(1)) {
							maeMax = rs2.getInt(1);
							ciMaeMax = ciMaestra;
						}
					}

					pstmt.close();
					rs2.close();
				}
				query = "SELECT nombre, apellido FROM Personas WHERE cedula = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, ciMaeMax);
				rs = pstmt.executeQuery();
				System.out.println("Resultado ejercicio 4 parte b");
				while(rs.next()) {
					System.out.println(rs.getString("nombre").trim()+" "+rs.getString("apellido").trim());
				}
				pstmt.close();
				rs.close();
				stmt.close();
				con.commit();
			}catch(SQLException e) {
				con.rollback();
			}finally {
				/*EJERCICIO 5*/
				String sp = "call BorrarMaestra(?)";
				CallableStatement cstm = con.prepareCall(sp);
				cstm.setInt(1, ciMaeMax);
				boolean isResultSet = cstm.execute();
				if(isResultSet) {
					ResultSet rsSP = cstm.getResultSet();
					while (rsSP.next())
					{
						System.out.print("CI===");
						System.out.println(rsSP.getInt(1));
					}
					rsSP.close();
					isResultSet = cstm.getMoreResults();
					if(isResultSet) {
						rsSP = cstm.getResultSet();
						while (rsSP.next())
						{
							System.out.println(rsSP.getString(1));
						}
					}
					rsSP.close();

					isResultSet = cstm.getMoreResults();
					if(isResultSet) {
						rsSP = cstm.getResultSet();
						while (rsSP.next())
						{
							System.out.println(rsSP.getString(1)+" "+rsSP.getString(2));
						}
					}
					rsSP.close();
				}
				
				query = "SHOW DATABASES";
				Statement stmtDBList = con.createStatement();
				rs = stmtDBList.executeQuery(query);
				System.out.println("Bases de datos del Schema:");
				while(rs.next()) {
					System.out.println(rs.getString(1));
				}
				rs.close();
				stmtDBList.close();
				
				Scanner inputScanner = new Scanner (System.in); //Creación de un objeto Scanner
		        System.out.println("Ingrese nombre de base de datos");
				String queryTeclado  = inputScanner.nextLine (); //Invocamos un método sobre un objeto Scanner
		        query = "USE ?";
		        PreparedStatement pstmtTableList = con.prepareStatement(query);
				pstmtTableList.setString(1, queryTeclado.trim().replace("'",""));
				try {
					System.out.println("1 "+pstmtTableList);
					pstmtTableList.executeQuery();
					System.out.println("2");
					query = "SHOW TABLES";
					stmtDBList = con.createStatement();
					rs = stmtDBList.executeQuery(query);
					System.out.println("Tablas de la BD:");
					while(rs.next()) {
						System.out.println("Tabla - ");
						System.out.print(rs.getString(1));
						query = "SHOW COLUMNS FROM ?";
					    PreparedStatement pstmtColumnList = con.prepareStatement(query);
					    pstmtColumnList.setString(1, rs.getString(1).trim());
					    ResultSet rsColumns = pstmtColumnList.executeQuery();
					    System.out.println("Columnas - ");
					    while(rsColumns.next()) {
					    		 System.out.print(rsColumns.getString(1).trim()+" - "+rsColumns.getString(1).trim()+" - ");
					    }
					}
					rs.close();
					stmtDBList.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					pstmtTableList.close();
				}
				/* 7. por ultimo, cierro la conexion con la base de datos */
				con.close();
			}
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

