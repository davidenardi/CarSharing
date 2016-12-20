package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class ClasseGrafica {
Database db = new Database();
String codFiscaleWhere;
ArrayList<Soci> elencoSoci = new ArrayList<Soci>();
ArrayList<Noleggi> elencoNoleggiWhere = new ArrayList<Noleggi>();
	protected Shell shlCarSharingNardi;
	private List listCodice;
	private List combo;
	private List listAuto;
	private List listInizio;
	private List listSocio;
	private List listFine;
	private List listRestituita;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClasseGrafica window = new ClasseGrafica();
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
		shlCarSharingNardi.open();
		shlCarSharingNardi.layout();
		while (!shlCarSharingNardi.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCarSharingNardi = new Shell();
		shlCarSharingNardi.setSize(923, 573);
		shlCarSharingNardi.setText("Car Sharing Nardi & Borto");
		
		
		DateTime dataFineFiltro = new DateTime(shlCarSharingNardi, SWT.BORDER);
		dataFineFiltro.setBounds(521, 13, 107, 28);
		
		
		DateTime dataInizioFiltro = new DateTime(shlCarSharingNardi, SWT.BORDER);
		dataInizioFiltro.setBounds(398, 13, 107, 28);
		
		Label lblDataInizio = new Label(shlCarSharingNardi, SWT.NONE);
		lblDataInizio.setBounds(377, 21, 22, 20);
		lblDataInizio.setText("Da:");
		
		Label lblSocio_1 = new Label(shlCarSharingNardi, SWT.NONE);
		lblSocio_1.setBounds(43, 21, 52, 20);
		lblSocio_1.setText("Socio:");
		
		List listCodice = new List(shlCarSharingNardi, SWT.BORDER);
		listCodice.setBounds(43, 73, 46, 314);
		
		Label lblCodice = new Label(shlCarSharingNardi, SWT.NONE);
		lblCodice.setBounds(43, 47, 46, 20);
		lblCodice.setText("Codice");
		
		Label lblAuto = new Label(shlCarSharingNardi, SWT.NONE);
		lblAuto.setBounds(98, 47, 38, 20);
		lblAuto.setText("Auto");
		
		Label lblSocio = new Label(shlCarSharingNardi, SWT.NONE);
		lblSocio.setBounds(197, 47, 38, 20);
		lblSocio.setText("Socio");
		
		Label lblInizio = new Label(shlCarSharingNardi, SWT.NONE);
		lblInizio.setBounds(364, 47, 38, 20);
		lblInizio.setText("Inizio");
		
		Label lblFine = new Label(shlCarSharingNardi, SWT.NONE);
		lblFine.setBounds(466, 47, 38, 20);
		lblFine.setText("Fine");
		
		Label lblRestituita = new Label(shlCarSharingNardi, SWT.NONE);
		lblRestituita.setBounds(568, 47, 70, 20);
		lblRestituita.setText("Restituita");
		
		List listAuto = new List(shlCarSharingNardi, SWT.BORDER);
		listAuto.setBounds(95, 73, 96, 314);
		
		List listSocio = new List(shlCarSharingNardi, SWT.BORDER);
		listSocio.setBounds(197, 73, 161, 314);
		
		List listInizio = new List(shlCarSharingNardi, SWT.BORDER);
		listInizio.setBounds(364, 73, 96, 314);
		
		List listFine = new List(shlCarSharingNardi, SWT.BORDER);
		listFine.setBounds(466, 73, 96, 314);
		
		List listRestituita = new List(shlCarSharingNardi, SWT.BORDER);
		listRestituita.setBounds(568, 73, 70, 314);
		
		ComboViewer comboViewer = new ComboViewer(shlCarSharingNardi, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sRis = combo.getItem(combo.getSelectionIndex());
				codFiscaleWhere = sRis.substring(0, 16);
				elencoNoleggiWhere = db.elencoNoleggiWhereCodice(codFiscaleWhere);
				listCodice.removeAll();
				listAuto.removeAll();				
				listInizio.removeAll();
				listSocio.removeAll();
				listFine.removeAll();
				listRestituita.removeAll();
				for(int i = 0; i<elencoNoleggiWhere.size();i++){
					listCodice.add(String.valueOf(elencoNoleggiWhere.get(i).codiceNoleggio));
					listAuto.add(elencoNoleggiWhere.get(i).auto);
					listSocio.add(elencoNoleggiWhere.get(i).socio);
					listInizio.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).inizio));;
					listFine.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).fine));;	
					if(elencoNoleggiWhere.get(i).autoRestituita == 1){
						listRestituita.add("Si");
						}else{
							listRestituita.add("No");
						}
					}	
			}
		});
		
		combo.setBounds(98, 13, 274, 28);
		
		
		Button btnCerca = new Button(shlCarSharingNardi, SWT.NONE);
		btnCerca.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(combo.getSelectionIndex() >= 0){
					String giornoInizio = String.valueOf(dataInizioFiltro.getDay());
					String meseInizio = String.valueOf(dataInizioFiltro.getMonth());
					String annoInizio = String.valueOf(dataInizioFiltro.getYear());
					String dataFiltroInizio = annoInizio.concat("-").concat(meseInizio).concat("-").concat(giornoInizio);
					
					String giornoFine = String.valueOf(dataFineFiltro.getDay());
					String meseFine = String.valueOf(dataFineFiltro.getMonth());
					String annoFine = String.valueOf(dataFineFiltro.getYear());
					String dataFiltroFine = annoFine.concat("-").concat(meseFine).concat("-").concat(giornoFine);
					//System.out.println(dataFiltro);
					elencoNoleggiWhere = db.elencoNoleggiWhereCodiceData(codFiscaleWhere, dataFiltroInizio, dataFiltroFine);
					
					String sRis  = combo.getItem(combo.getSelectionIndex());
					codFiscaleWhere = sRis.substring(0, 16);
					listCodice.removeAll();
					listAuto.removeAll();				
					listInizio.removeAll();
					listSocio.removeAll();
					listFine.removeAll();
					listRestituita.removeAll();
					for(int i = 0; i<elencoNoleggiWhere.size();i++){
						listCodice.add(String.valueOf(elencoNoleggiWhere.get(i).codiceNoleggio));
						listAuto.add(elencoNoleggiWhere.get(i).auto);
						listSocio.add(elencoNoleggiWhere.get(i).socio);
						listInizio.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).inizio));;
						listFine.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).fine));;	
						if(elencoNoleggiWhere.get(i).autoRestituita == 1){
							listRestituita.add("Si");
							}else{
								listRestituita.add("No");
							}
						}	
				}else{
					String giornoInizio = String.valueOf(dataInizioFiltro.getDay());
					String meseInizio = String.valueOf(dataInizioFiltro.getMonth());
					String annoInizio = String.valueOf(dataInizioFiltro.getYear());
					String dataFiltroInizio = annoInizio.concat("-").concat(meseInizio).concat("-").concat(giornoInizio);
					
					String giornoFine = String.valueOf(dataFineFiltro.getDay());
					String meseFine = String.valueOf(dataFineFiltro.getMonth());
					String annoFine = String.valueOf(dataFineFiltro.getYear());
					String dataFiltroFine = annoFine.concat("-").concat(meseFine).concat("-").concat(giornoFine);
					//System.out.println(dataFiltro);
					elencoNoleggiWhere = db.elencoNoleggiWhereData( dataFiltroInizio, dataFiltroFine);
					listCodice.removeAll();
					listAuto.removeAll();				
					listInizio.removeAll();
					listSocio.removeAll();
					listFine.removeAll();
					listRestituita.removeAll();
					for(int i = 0; i<elencoNoleggiWhere.size();i++){
						listCodice.add(String.valueOf(elencoNoleggiWhere.get(i).codiceNoleggio));
						listAuto.add(elencoNoleggiWhere.get(i).auto);
						listSocio.add(elencoNoleggiWhere.get(i).socio);
						listInizio.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).inizio));;
						listFine.add(ritornaDataOrdinata(elencoNoleggiWhere.get(i).fine));;	
						if(elencoNoleggiWhere.get(i).autoRestituita == 1){
							listRestituita.add("Si");
							}else{
								listRestituita.add("No");
							}
						}	
				}
			}
				
		});
		btnCerca.setBounds(634, 11, 59, 30);
		btnCerca.setText("Cerca");
		
		Label lblA = new Label(shlCarSharingNardi, SWT.NONE);
		lblA.setText("A :");
		lblA.setBounds(511, 21, 22, 15);
		
		
		
		elencoSoci = db.ElencoSoci();
		for(int i = 0; i<elencoSoci.size();i++){
			combo.add(elencoSoci.get(i).cf + " : " + elencoSoci.get(i).Cognome + " " + elencoSoci.get(i).Nome);
		}
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
}
