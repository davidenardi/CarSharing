package ProgettoCarSharing;

public class Auto {
	String targa;
	String marca;
	String modello;
	float costoGiornaliero;
	
	public Auto(String targa, String marca,String modello,float costoGiornaliero){
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.costoGiornaliero = costoGiornaliero;
	}
	public void cancellaDati(){
	
		}
	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public float getCostoGiornaliero() {
		return costoGiornaliero;
	}

	public void setCostoGiornaliero(float costoGiornaliero) {
		this.costoGiornaliero = costoGiornaliero;
	}

	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", marca=" + marca + ", modello=" + modello + ", costoGiornaliero="
				+ costoGiornaliero + "]";
	}
	
}
