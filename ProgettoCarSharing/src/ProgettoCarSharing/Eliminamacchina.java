package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Eliminamacchina {
	Database db = new Database();
	ArrayList<Auto> elencoAuto = new ArrayList<Auto>();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Eliminamacchina window = new Eliminamacchina();
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
		Shell shell = new Shell();
		shell.setSize(377, 258);
		shell.setText("Elimina una macchina");
		
		shell.open();
		shell.layout();

		ComboViewer comboViewer = new ComboViewer(shell, SWT.NONE);
		Combo comboAuto = comboViewer.getCombo();
		comboAuto.setBounds(37, 22, 255, 51);
		
		Button btnCancella = new Button(shell, SWT.NONE);
		btnCancella.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String auto  = comboAuto.getItem(comboAuto.getSelectionIndex());
				db.EliminaAuto(auto.substring(0, 7));
			}
		});
		btnCancella.setBounds(112, 68, 111, 39);
		btnCancella.setText("Cancella");

		
		elencoAuto = db.ElencoAutoDisponibili();
		for(int i = 0; i< elencoAuto.size();i++){
			comboAuto.add(elencoAuto.get(i).targa + " : " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello );
		}
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		
	}
}
