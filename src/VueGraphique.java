import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

@SuppressWarnings({ "deprecation", "serial" })
public class VueGraphique extends JPanel implements Observer {
	Lumiere modele;
	
	/*
	 * Permet de lier la vue au modele
	 * 
	 * @param l modele
	 * @param arg1 Object
	 */
	public void update ( Observable l , Object arg1 ) {
		modele = ( Lumiere ) l;
		repaint();
	}
	
	/*
	 * Permet de dessiner la grille avec les lumieres
	 * 
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color vertClair = new Color(0,120,0);
		if (modele != null) {
			// On dessine les lumieres
			for (int i = 0 ; i < 5 ; i++) {
				for (int j = 0 ; j < 5 ; j++) {
					if (modele.getAllume(i, j)) {
						g.setColor(Color.green);
					} else {
						g.setColor(vertClair);
					}
					g.fillRect(i*50, j*50, (i*50)+50, (j*50)+50);
				}
			}
			
			// On dessine la grille
			g.setColor(Color.black);
			for (int i = 0 ; i < 250 ; i += 50) {
				g.drawLine(i, 0, i, i*50);
			}
			for (int i = 0 ; i < 250 ; i += 50) {
				g.drawLine(0, i, i*50, i);
			}
			
		}
	}

}
