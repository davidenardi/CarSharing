package ProgettoCarSharing;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.ibm.icu.text.SimpleDateFormat;
import org.eclipse.wb.swt.SWTResourceManager;

public class AggiungoNoleggio {

	ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
	ArrayList<Soci> elencoSoci = new ArrayList<Soci>();
	Combo comboAuto;
	Combo comboSocio;
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
		shlAggiungiNoleggio.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		shlAggiungiNoleggio.setImage(SWTResourceManager.getImage(AggiungoNoleggio.class, "/ProgettoCarSharing/autoimg.png"));
		shlAggiungiNoleggio.setSize(567, 307);
		shlAggiungiNoleggio.setText("Aggiungi Noleggio");
		
		Label lblSfondo = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSfondo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblSfondo.setImage(SWTResourceManager.getImage(AggiungoNoleggio.class, "/ProgettoCarSharing/autoimg.png"));
		lblSfondo.setBounds(10, 105, 273, 154);
		
		Label lblControllo = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblControllo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblControllo.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblControllo.setBounds(266, 214, 273, 45);
		
		Label lblSocio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSocio.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblSocio.setBounds(253, 71, 54, 24);
		lblSocio.setText("Socio:");
		
		ComboViewer comboViewer_1 = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		comboSocio = comboViewer_1.getCombo();
		comboSocio.setBounds(313, 68, 211, 33);
		
		
		ComboViewer comboViewer = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		comboAuto = comboViewer.getCombo();
		comboAuto.setBounds(62, 68, 178, 33);
		
		Database db = new Database();
		elencoSoci = db.ElencoSoci();
		elencoAuto = db.ElencoAutoDisponibili("2016-12-22");
		for(int i = 0; i< elencoAuto.size();i++){
			comboAuto.add(elencoAuto.get(i).targa + " " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
		}
		for(int i = 0; i< elencoSoci.size();i++){
			comboSocio.add(elencoSoci.get(i).cf + "(" + elencoSoci.get(i).Cognome + " "+ elencoSoci.get(i).Nome + ")");
		}
		
		
		Label lblDataInizio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataInizio.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblDataInizio.setBounds(10, 20, 92, 24);
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
				aggiornaMacchina(dataInizio);
			}
		});
		dataInizioNuovo.setBounds(108, 20, 147, 24);
		
		Label label = new Label(shlAggiungiNoleggio, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 52, 332, 2);
		
		Label lblDataFine = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataFine.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblDataFine.setBounds(280, 20, 89, 24);
		lblDataFine.setText("Data Fine:");
		
		DateTime dataFineNuovo = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dataFineNuovo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int g =  dataFineNuovo.getDay();
				int m = dataFineNuovo.getMonth() + 1;
				int a = dataFineNuovo.getYear();
				String dataFine = String.valueOf(a).concat("-").concat(String.valueOf(m).concat("-").concat(String.valueOf(g)));
				Database db = new Database();
				elencoAuto = db.ElencoAutoDisponibiliPrimaDellaFine(dataFine);
				for(int i = 0; i<elencoAuto.size();i++){
					//System.out.println(elencoAuto.get(i).targa + " " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
				}
				int comboLenght = comboAuto.getItemCount();
				//aggiornaMacchina(elencoAuto, comboLenght);
			}
		});
		dataFineNuovo.setBounds(376, 20, 147, 24);
		
		Label lblAuto = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblAuto.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblAuto.setBounds(10, 71, 46, 36);
		lblAuto.setText("Auto:");
		
		Button btnAggiungi = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnAggiungi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				SimpleDateFormat di = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dataI = null;
				try {
					dataI= di.parse(dataInizioNuovo.getYear() + "-" + (dataInizioNuovo.getMonth()*1+1 )+ "-" + dataInizioNuovo.getDay());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dataF = null;
				try {
					dataF = df.parse(dataFineNuovo.getYear() + "-" + (dataFineNuovo.getMonth()*1+1) + "-" + dataFineNuovo.getDay());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.now();
				System.out.println(dtf.format(localDate)); //2016/11/16
				java.util.Date dataOdierna = null;
	
				
				
				SimpleDateFormat dO = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dataO = null;
				try {
					dataO = dO.parse(dtf.format(localDate));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//System.out.println("comparazione : " + dataI.compareTo(dataO));
				if(dataI.compareTo(dataO) >= 0){
					if(dataI.compareTo(dataF) <= 0 ){
						System.out.println("date giuste, procedo");
						int gf =  dataFineNuovo.getDay();
						int mf = dataFineNuovo.getMonth()+1;
						
						int af = dataFineNuovo.getYear();
						int gi =  dataInizioNuovo.getDay();
						int mi= dataInizioNuovo.getMonth()+1;
						int ai= dataInizioNuovo.getYear();
						String dataInizioPrenotazione = String.valueOf(ai).concat("-").concat(String.valueOf(mi).concat("-").concat(String.valueOf(gi)));
						String dataFinePrenotazione = String.valueOf(af).concat("-").concat(String.valueOf(mf).concat("-").concat(String.valueOf(gf)));
						String auto = comboAuto.getItem(comboAuto.getSelectionIndex());
						String socio = comboSocio.getItem(comboSocio.getSelectionIndex());
						//socio.substring(0, 16);
						//auto.substring(0, 7);
						System.out.println("pronto ad aggiungere: " + dataInizioPrenotazione + " e " + dataFinePrenotazione);
						db.AggiungiNoleggio(dataInizioPrenotazione, dataFinePrenotazione, socio.substring(0, 16), auto.substring(0, 7));
						lblControllo.setText("dati Inseriti");
				}else{
					System.out.println("date sbagliate, reinserire");
					lblControllo.setText("Le date non possono essere cosi', ricontrolla");
				}
				
				}else{
					System.out.println("date sbagliate, reinserire");
					lblControllo.setText("Le date non possono essere cosi', ricontrolla");
				}
				
			}
		});
		btnAggiungi.setBounds(348, 118, 103, 33);
		btnAggiungi.setText("Aggiungi");
		
		
		

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

	public void aggiornaMacchina(String dataInizio){
		comboAuto.removeAll();
		ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
		Database db = new Database();
		elencoAuto = db.ElencoAutoDisponibili(dataInizio);
		for(int i = 0; i< elencoAuto.size();i++){
			comboAuto.add(elencoAuto.get(i).targa + " " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello);
		}
	}
	
}
