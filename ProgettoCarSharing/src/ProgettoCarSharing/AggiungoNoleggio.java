package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;

import java.sql.Date;
import java.util.Calendar;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class AggiungoNoleggio {

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
		
		Label lblDataInizio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataInizio.setBounds(10, 20, 80, 24);
		lblDataInizio.setText("Data Inizio:");
		
		DateTime dataInizioNuovo = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dataInizioNuovo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//aggiorno la lista delle macchine disponibili per questa data
			}
		});
		dataInizioNuovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println(String.valueOf(dataInizioNuovo));
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
				//aggiorno la lista delle macchine disponibili per questa data
				
			}
		});
		dataFineNuovo.setBounds(294, 20, 111, 24);
		
		Label lblAuto = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblAuto.setBounds(10, 71, 46, 36);
		lblAuto.setText("Auto:");
		
		ComboViewer comboViewer = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(62, 68, 126, 28);
		
		Label lblSocio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSocio.setBounds(194, 71, 46, 24);
		lblSocio.setText("Socio:");
		
		ComboViewer comboViewer_1 = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setBounds(246, 68, 155, 28);
		
		Button btnAggiungi = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnAggiungi.setBounds(165, 121, 75, 25);
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
}
