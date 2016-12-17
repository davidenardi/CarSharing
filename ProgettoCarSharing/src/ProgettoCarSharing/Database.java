package ProgettoCarSharing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
		public static ArrayList<Soci> ElencoSoci(){
			ArrayList<Soci> elencoSoci = new ArrayList<Soci>();
			
			//connessione al database
			Connection cn;
			Statement st;
			ResultSet rs;
			String sql;
			// ________________________________connessione
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException: ");
				System.err.println(e.getMessage());
			} // fine try-catch

			try {
				// Creo la connessione al database
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

				sql = "SELECT cf,Cognome,Nome,Indirizzo,Telefono FROM soci;";
				// ________________________________query

				st = cn.createStatement(); // creo sempre uno statement sulla
											// connessione
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					Soci s = new Soci(rs.getString("cf"), rs.getString("Cognome"), rs.getString("Nome"), rs.getString("Indirizzo"),rs.getString("Telefono"));
					elencoSoci.add(s);
				}

				cn.close(); // chiusura connessione
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
				e.printStackTrace();
			} // fine try-catch

			return elencoSoci;
		}
}