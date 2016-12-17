package ProgettoCarSharing;

public class Soci {
	String cf;
	String Nome;
	String Cognome;
	String Indirizzo;
	String Telefono;
	
	public Soci(String cf, String Cognome,String Nome , String Indirizzo, String Telefono){
		this.cf = cf;
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Indirizzo = Indirizzo;
		this.Telefono = Telefono;
	}

	public String getcf() {
		return cf;
	}

	public void setIdSocio(String cf) {
		this.cf = cf;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getIndirizzo() {
		return Indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	@Override
	public String toString() {
		return "Soci [cf=" + cf + ", Nome=" + Nome + ", Cognome=" + Cognome + ", Indirizzo=" + Indirizzo + ", Telefono="
				+ Telefono + "]";
	}
	
}
