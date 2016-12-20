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
		shlAggiungiNoleggio.setSize(370, 213);
		shlAggiungiNoleggio.setText("Aggiungi Noleggio");
		
		Label lblDataInizio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataInizio.setBounds(10, 20, 65, 15);
		lblDataInizio.setText("Data Inizio:");
		
		DateTime dateTime = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dateTime.setBounds(81, 11, 80, 24);
		
		Label label = new Label(shlAggiungiNoleggio, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 41, 332, 2);
		
		Label lblDataFine = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblDataFine.setBounds(189, 20, 55, 15);
		lblDataFine.setText("Data Fine:");
		
		DateTime dateTime_1 = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		dateTime_1.setBounds(245, 11, 80, 24);
		
		Label lblAuto = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblAuto.setBounds(10, 64, 37, 15);
		lblAuto.setText("Auto:");
		
		ComboViewer comboViewer = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(55, 61, 91, 23);
		
		Label lblSocio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSocio.setBounds(156, 64, 37, 15);
		lblSocio.setText("Socio:");
		
		ComboViewer comboViewer_1 = new ComboViewer(shlAggiungiNoleggio, SWT.NONE);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setBounds(199, 58, 143, 23);
		
		Button btnAggiungi = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnAggiungi.setBounds(133, 104, 75, 25);
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
