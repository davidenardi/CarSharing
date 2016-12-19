package ProgettoCarSharing;

import java.sql.Date;

public class Noleggi {
	int codiceNoleggio;
	String auto;
	String socio;
	Date inizio;
	Date fine;
	int autoRestituita;
	
	public Noleggi(int codiceNoleggio, String auto,String socio,Date inizio,Date fine,int autoRestituita){
		this.auto = auto;
		this.codiceNoleggio = codiceNoleggio;
		this.socio = socio;
		this.inizio = inizio;
		this.fine = fine;
		this.autoRestituita = autoRestituita;
	}

	@Override
	public String toString() {
		return "Nolleggi [codiceNoleggio=" + codiceNoleggio + ", auto=" + auto + ", socio=" + socio + ", inizio="
				+ inizio + ", fine=" + fine + ", autoRestituita=" + autoRestituita + "]";
	}

	public int getCodiceNoleggio() {
		return codiceNoleggio;
	}

	public void setCodiceNoleggio(int codiceNoleggio) {
		this.codiceNoleggio = codiceNoleggio;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getSocio() {
		return socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public int getAutoRestituita() {
		return autoRestituita;
	}

	public void setAutoRestituita(int autoRestituita) {
		this.autoRestituita = autoRestituita;
	}
	
}
