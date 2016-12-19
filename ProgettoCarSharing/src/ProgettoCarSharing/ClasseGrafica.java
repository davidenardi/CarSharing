package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ClasseGrafica {
Database db = new Database();
ArrayList<Soci> elencoSoci = new ArrayList<Soci>();
ArrayList<Noleggi> elencoNoleggiWhere = new ArrayList<Noleggi>();
	protected Shell shlCarSharingNardi;

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
				String codFiscaleWhere = sRis.substring(0, 16);
				elencoNoleggiWhere = db.elencoNoleggiWhere(codFiscaleWhere);
				for(int i = 0; i<elencoNoleggiWhere.size();i++){
					listCodice.add(String.valueOf(elencoNoleggiWhere.get(i).codiceNoleggio));
					listAuto.add(elencoNoleggiWhere.get(i).auto);
					listSocio.add(elencoNoleggiWhere.get(i).socio);
					listInizio.add(String.valueOf(elencoNoleggiWhere.get(i).inizio));
					listFine.add(String.valueOf(elencoNoleggiWhere.get(i).fine));
					listRestituita.add(String.valueOf(elencoNoleggiWhere.get(i).autoRestituita));
				}	
			}
		});
		
		combo.setBounds(43, 10, 221, 28);
		
		Label lblNewLabel = new Label(shlCarSharingNardi, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("C:\\Users\\Alessandro\\git\\CarSharing\\ProgettoCarSharing\\File\\immCS.jpg"));
		lblNewLabel.setBounds(650, 13, 196, 155);
		
		
		elencoSoci = db.ElencoSoci();
		for(int i = 0; i<elencoSoci.size();i++){
			combo.add(elencoSoci.get(i).cf + " : " + elencoSoci.get(i).Cognome + " " + elencoSoci.get(i).Nome);
		}
	}
}
