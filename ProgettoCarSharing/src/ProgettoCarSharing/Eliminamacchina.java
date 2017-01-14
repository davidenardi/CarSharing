package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

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
		shell.setImage(SWTResourceManager.getImage(Eliminamacchina.class, "/ProgettoCarSharing/autoimg.png"));
		shell.setSize(377, 258);
		shell.setText("Elimina una macchina");
		
		shell.open();
		shell.layout();


		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(10, 146, 339, 28);
		
		ComboViewer comboViewer = new ComboViewer(shell, SWT.NONE);
		Combo comboAuto = comboViewer.getCombo();
		comboAuto.setBounds(40, 23, 255, 51);
		
		Button btnCancella = new Button(shell, SWT.NONE);
		btnCancella.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String auto  = comboAuto.getItem(comboAuto.getSelectionIndex());
				db.EliminaAuto(auto.substring(0, 7));
				db.EliminaNoleggi(auto.substring(0, 7));
				comboAuto.removeAll();
				//riga suggerita da nardi
				elencoAuto = db.ElencoAutoDisponibili();
				for(int i = 0; i< elencoAuto.size();i++){
					comboAuto.add(elencoAuto.get(i).targa + " : " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello );
				}
				
				lblNewLabel.setText("macchina (" + auto.substring(0, 7) + ") e i relativi noleggi eliminati" );
			}
		});
		btnCancella.setBounds(112, 68, 111, 39);
		btnCancella.setText("Cancella");
		
		Button btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		btnRefresh.setText("refresh");
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboAuto.removeAll();
				//riga suggerita da nardi
				elencoAuto = db.ElencoAutoDisponibili();
				for(int i = 0; i< elencoAuto.size();i++){
					comboAuto.add(elencoAuto.get(i).targa + " : " + elencoAuto.get(i).marca + " " + elencoAuto.get(i).modello );
				}
				
			}
		});
		btnRefresh.setBounds(301, 24, 48, 27);
		

		
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
