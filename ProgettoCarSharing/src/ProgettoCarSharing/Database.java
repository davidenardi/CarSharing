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
		
			public ArrayList<Noleggi> elencoNoleggi(){
				ArrayList<Noleggi> elencoNoleggi = new ArrayList<Noleggi>();
				
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

					sql = "SELECT codice_noleggio,auto,socio,inzio,fine,auto_restituita FROM noleggi  ";
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						Noleggi n = new Noleggi(rs.getInt("codice_noleggio"), rs.getString("auto"), rs.getString("socio"), rs.getDate("inzio"),rs.getDate("fine"), rs.getInt("auto_restituita"));
						elencoNoleggi.add(n);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
					e.printStackTrace();
				} // fine try-catch

				return elencoNoleggi;
			}
		
		
			
			//ritorna un certo arraylist di noleggio, col where per  codice fiscale 
			public ArrayList<Noleggi> elencoNoleggiWhereCodice(String Where){
				ArrayList<Noleggi> elencoNoleggi = new ArrayList<Noleggi>();
				
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


					sql = "SELECT codice_noleggio,auto,socio,inzio,fine,auto_restituita FROM noleggi WHERE socio = '" + Where + "';" ;
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						Noleggi n = new Noleggi(rs.getInt("codice_noleggio"), rs.getString("auto"), rs.getString("socio"), rs.getDate("inzio"),rs.getDate("fine"), rs.getInt("auto_restituita"));
						elencoNoleggi.add(n);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
					e.printStackTrace();
				} // fine try-catch

				return elencoNoleggi;
			}
			
			//ritorna n base anche alla data
			

			//ritorna un certo arraylist di noleggio, col where per  codice fiscale 
			public ArrayList<Noleggi> elencoNoleggiWhereCodiceData(String codiceFiscale, String dataInizio, String dataFine){
				ArrayList<Noleggi> elencoNoleggi = new ArrayList<Noleggi>();
				
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


					sql = "SELECT codice_noleggio,auto,socio,inzio,fine,auto_restituita FROM noleggi WHERE socio = '" + codiceFiscale + "' and inzio >= '"+dataInizio+"' AND inzio <= '" + dataFine + "';" ;
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						Noleggi n = new Noleggi(rs.getInt("codice_noleggio"), rs.getString("auto"), rs.getString("socio"), rs.getDate("inzio"),rs.getDate("fine"), rs.getInt("auto_restituita"));
						elencoNoleggi.add(n);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
					e.printStackTrace();
				} // fine try-catch

				return elencoNoleggi;
			}
			
			
			
			//ritorna un certo arraylist di noleggio, col where per  data inizio e data fine
			public ArrayList<Noleggi> elencoNoleggiWhereData(String dataInizio, String dataFine){
				ArrayList<Noleggi> elencoNoleggi = new ArrayList<Noleggi>();
				
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


					sql = "SELECT codice_noleggio,auto,socio,inzio,fine,auto_restituita FROM noleggi WHERE inzio >= '"+dataInizio+"' AND inzio <= '" + dataFine + "';" ;
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						Noleggi n = new Noleggi(rs.getInt("codice_noleggio"), rs.getString("auto"), rs.getString("socio"), rs.getDate("inzio"),rs.getDate("fine"), rs.getInt("auto_restituita"));
						elencoNoleggi.add(n);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
					e.printStackTrace();
				} // fine try-catch

				return elencoNoleggi;
			}
}