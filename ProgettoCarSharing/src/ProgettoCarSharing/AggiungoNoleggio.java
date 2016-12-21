package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ibm.icu.text.SimpleDateFormat;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class AggiungoNoleggio {

ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
ComboViewer comboViewer;
	protected Shell shlAggiungiNoleggio;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AggiungoNoleggio window = new AggiungoNoleggio();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAggiungiNoleggio.open();
		shlAggiungiNoleggio.layout();
		while (!shlAggiungiNoleggio.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shlAggiungiNoleggio = new Shell();
		shlAggiungiNoleggio.setSize(475, 290);
		shlAggiungiNoleggio.setText("Aggiungi Noleggio");
		
		comboViewer = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo comboAuto = comboViewer.getCombo();
		comboAuto.setBounds(48, 67, 155, 28);
		
		Database  db = new Database();
		elencoAuto = db.ElencoAutoDisponibili("2016-12-29");
		for(int i = 0; i<elencoAuto.size();i++){
			System.out.println(elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
		}
		aggiornaMacchina(elencoAuto, 0);
		
		Label lblDataInizio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataInizio.setBounds(10, 20, 80, 24);
		lblDataInizio.setText("Data Inizio:");
		
		DateTime dataInizioNuovo = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dataInizioNuovo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//aggiorno la lista delle macchine disponibili per questa data
				int g =  dataInizioNuovo.getDay();
				int m = dataInizioNuovo.getMonth();
				int a = dataInizioNuovo.getYear();
				String dataInizio = String.valueOf(a).concat("-").concat(String.valueOf(m).concat("-").concat(String.valueOf(g)));
				Database db = new Database();
				elencoAuto = db.ElencoAutoDisponibili(dataInizio);
				for(int i = 0; i<elencoAuto.size();i++){
					System.out.println(elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
				}
				int comboLenght = comboAuto.getItemCount();
				aggiornaMacchina(elencoAuto, comboLenght);
			}
		});
		dataInizioNuovo.setBounds(96, 20, 111, 24);
		
		Label label = new Label(shlAggiungiNoleggio, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 52, 332, 2);
		
		Label lblDataFine = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataFine.setBounds(213, 20, 75, 24);
		lblDataFine.setText("Data Fine:");
		
		DateTime dataFineNuovo = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dataFineNuovo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int g =  dataFineNuovo.getDay();
				int m = dataFineNuovo.getMonth();
				int a = dataFineNuovo.getYear();
				String dataFine = String.valueOf(a).concat("-").concat(String.valueOf(m).concat("-").concat(String.valueOf(g)));
				Database db = new Database();
				elencoAuto = db.ElencoAutoDisponibiliPrimaDellaFine(dataFine);
				for(int i = 0; i<elencoAuto.size();i++){
					System.out.println(elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
				}
				int comboLenght = comboAuto.getItemCount();
				aggiornaMacchina(elencoAuto, comboLenght);
			}
		});
		dataFineNuovo.setBounds(294, 20, 111, 24);
		
		Label lblAuto = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblAuto.setBounds(10, 71, 46, 36);
		lblAuto.setText("Auto:");
		
		Label lblSocio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSocio.setBounds(221, 71, 46, 24);
		lblSocio.setText("Socio:");
		
		ComboViewer comboViewer_1 = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo comboSocio = comboViewer_1.getCombo();
		comboSocio.setBounds(273, 68, 155, 28);
		
		Button btnAggiungi = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnAggiungi.setBounds(165, 121, 75, 25);
		btnAggiungi.setText("Aggiungi");
		
		

	}
	
	public ComboViewer getComboViewer() {
		return comboViewer;
	}
	
	public void setComboViewer(ComboViewer comboViewer) {
		this.comboViewer = comboViewer;
	}

	public String ritornaDataOrdinata(Date d){
		Calendar data = Calendar.getInstance();
		data.setTime(d);
		String giornoInizio = String.valueOf(Calendar.DAY_OF_MONTH);
		String meseInizio = String.valueOf(Calendar.MONTH);
		String annoInizio = String.valueOf(data.get(Calendar.YEAR));
		String dataOrdinata = giornoInizio.concat("-").concat(meseInizio).concat("-").concat(annoInizio);
		return dataOrdinata;
	}
	public ArrayList<Auto> getElencoAuto() {
		return elencoAuto;
	}

	public void setElencoAuto(ArrayList<Auto> elencoAuto) {
		this.elencoAuto = elencoAuto;
	}

	public void aggiornaMacchina(ArrayList macchine, int comboLenght){
		ComboViewer combo = getComboViewer();
		for(int y = 0; y < comboLenght; y++){
			combo.remove(y);
		}
		ArrayList<Auto> elencoAuto2= getElencoAuto();
		for(int i =0; i<macchine.size();i++){
			combo.add(elencoAuto2.get(i).marca + " " + elencoAuto2.get(i).modello);
		}
		setComboViewer(combo);
	}
}
