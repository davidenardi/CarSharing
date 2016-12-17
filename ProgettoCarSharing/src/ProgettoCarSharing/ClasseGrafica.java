package ProgettoCarSharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.util.ArrayList;

import org.eclipse.swt.SWT;

public class ClasseGrafica {
Database db = new Database();
ArrayList<Soci> elencoSoci = new ArrayList<Soci>();
	protected Shell shell;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 10, 121, 206);
		elencoSoci = db.ElencoSoci();
		System.out.println(elencoSoci.get(1).Cognome);
		for(int i = 0; i<elencoSoci.size();i++){
			list.add(elencoSoci.get(i).Cognome + " " + elencoSoci.get(i).Nome);
		}
	}
}
