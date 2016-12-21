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
			public static ArrayList<Auto> ElencoAutoDisponibili(String dataInizio){
				ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
				elencoAuto.removeAll(elencoAuto);
				
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

					sql = "SELECT auto.Targa,auto.Marca,auto.Modello,costo_giornaliero FROM auto,noleggi WHERE auto.Targa = noleggi.auto and noleggi.fine < '"+ dataInizio + "'";
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					
					while (rs.next() == true) {
						Auto a = new Auto(rs.getString("Targa"), rs.getString("Marca"), rs.getString("Modello"), rs.getFloat("costo_giornaliero"));
						elencoAuto.add(a);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
					e.printStackTrace();
				} // fine try-catcheeee

				
				return elencoAuto;
			}
			
			public static ArrayList<Auto> ElencoAutoDisponibiliPrimaDellaFine(String dataFine){
				ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
				elencoAuto.removeAll(elencoAuto);
				
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

					sql = "SELECT auto.Targa,auto.Marca,auto.Modello,costo_giornaliero FROM auto,noleggi WHERE auto.Targa = noleggi.auto and noleggi.inzio > '"+ dataFine + "'";
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeQuery(sql); // faccio la query su uno statement
					
					while (rs.next() == true) {
						Auto a = new Auto(rs.getString("Targa"), rs.getString("Marca"), rs.getString("Modello"), rs.getFloat("costo_giornaliero"));
						elencoAuto.add(a);
					}

					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore :" + e.getMessage());
					e.printStackTrace();
				} // fi

				
				return elencoAuto;
			}
			
			public static void AggiungiNoleggio(String dataInizio,String dataFine, String socio, String auto){
				//connessione al database
				Connection cn;
				Statement st;
				int rs;
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
					//INSERT INTO `noleggi` (`codice_noleggio`, `auto`, `socio`, `inzio`, `fine`, `auto_restituita`) VALUES (NULL, '', '', '', '', '')
					sql ="INSERT INTO `noleggi` (`codice_noleggio`, `auto`, `socio`, `inzio`, `fine`, `auto_restituita`) "
							+ "VALUES (NULL, '"  + auto +  "','" + socio + "','" + dataInizio + "', '"+ dataFine + "', '0')";
					// ________________________________query

					st = cn.createStatement(); // creo sempre uno statement sulla
												// connessione
					rs = st.executeUpdate(sql); // faccio la query su uno statement
					System.out.println("dati inseriti");
					cn.close(); // chiusura connessione
				} catch (SQLException e) {
					System.out.println("errore :" + e.getMessage());
					e.printStackTrace();
				} 
			}
}