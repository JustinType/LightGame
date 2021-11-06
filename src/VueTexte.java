import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

@SuppressWarnings({ "deprecation", "serial" })
public class VueTexte extends JPanel implements Observer {
	private JLabel[][] labelAllume;
	
	/*
	 * Constructeur qui attache un JLabel par defaut aux lumieres (ici eteintes)
	 */
	public VueTexte() {
		this.labelAllume = new JLabel[5][5];
		for (int i = 0 ; i < 5 ; i++) {
			for (int j = 0 ; j < 5 ; j++) {
				labelAllume[i][j] = new JLabel("eteinte");
			}
		}
	}

	
	/*
	 * Permet de changer le JLabel en fonction de l'etat de la lumiere
	 * 
	 * @param l Lumiere
	 * @param arg1 Object
	 * @Override
	 */
	public void update(Observable l, Object arg1) {
		boolean[][] allume = ((Lumiere)l).getTabAllume();
		for (int i = 0 ; i < 5 ; i++) {
			for (int j = 0 ; j < 5 ; j++) {
				if (!allume[i][j]) {
					labelAllume[i][j].setText("eteinte");
				} else {
					labelAllume[i][j].setText("allumee");
				}
			}
		}
		
	}
	
	/*
	 * Permet d'avoir le label correspondant a une lumiere precise
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 * @return String label
	 */
	public String getLabel(int x, int y) {
		if (x < 0 || x >= 5 || y < 0 || y >= 5) {
			return "Cette lumiere n'existe pas";
		} else {
			return labelAllume[x][y].getText();
		}
	}

}

