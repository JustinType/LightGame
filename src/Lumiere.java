
import java.util.Observable;

import javax.swing.JPanel;

@SuppressWarnings("deprecation")
public class Lumiere extends Observable {
	private boolean[][] allume;
	private JPanel[][] tab;

	/**
	 * Constructeur permettant de creer une grille de lumieres
	 */
	public Lumiere() {
		// J'initialise toutes les lumieres eteintes
		this.tab = new JPanel[5][5];
        this.allume = new boolean[5][5];
        
		for (int i = 0 ; i < 5 ; i++) {
			for (int j = 0 ; j < 5 ; j++) {
				allume[i][j] = false;
				tab[i][j] = new JPanel();
			}
		}
		
		// On indique qu'il y a modification
		setChanged();
						
		// On notifie l'observer
		
	}
	
	/*
	 * Permet de savoir si une case existe bien aux coordoonees precises
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 * @return true ou false
	 */
	public boolean existeCase(int x, int y) {
		// Si les coordoonees sont invalides on renvoit false sinon true
		if (x < 0 || x >= 5 || y < 0 || y >= 5) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Permet de changer l'etat d'une lumiere precise
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 */
	public void switchAllume(int x, int y) {
		// On change la lumiere
		if (!allume[x][y]) {
			allume[x][y] = true;
		} else {
			allume[x][y] = false;	
		}

		// On indique qu'il y a modification
		setChanged();
		
		// On notifie l'observer
		notifyObservers();
	}
	
	
	/*
	 * Permet de changer l'etat d'une lumiere et celle adjacentes
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 */
	public void jouer(int x, int y) {
		// On switch la case selectionnee si elle existe
		if (existeCase(x,y)) {
			switchAllume(x,y);
			
			// On switch la case a gauche
			if (existeCase(x-1,y)) {
				switchAllume(x-1,y);
			}
			
			// On switch la case a droite
			if (existeCase(x+1,y)) {
				switchAllume(x+1,y);
			}
			
			// On switch la case au dessus
			if (existeCase(x,y-1)) {
				switchAllume(x,y-1);
			}
		
			// On switch la case en dessous
			if (existeCase(x,y+1)) {
				switchAllume(x,y+1);
			}
		}
		
		
		// On indique qu'il y a modification
		setChanged();
				
		// On notifie l'observer
		notifyObservers();
	}
	
	/*
	 * Permet de changer automatiquement l'etat d'un nombre aleatoire de lumieres
	 */
	public void aleatoire() {
		int nb = (int)(Math.random()*25);
		for (int i = 0 ; i < nb ; i++) {
			int randomX = (int)(Math.floor(Math.random()*5));
			int randomY = (int)(Math.floor(Math.random()*5));
			switchAllume(randomX, randomY);
		}
	}
	
	/*
	 * Permet de remettre toutes les lumieres eteintes
	 */
	public void clear() {      
		for (int i = 0 ; i < 5 ; i++) {
			for (int j = 0 ; j < 5 ; j++) {
				allume[i][j] = false;
				tab[i][j] = new JPanel();
			}
		}
				
		// On indique qu'il y a modification
		setChanged();
								
		// On notifie l'observer
		notifyObservers();
	}
	
	/*
	 * Permet de savoir si toutes les lumieres sont eteintes
	 * 
	 * @return true ou false
	 */
	public boolean gagner() {
		boolean gg = true;
		for (int i = 0 ; i < 5 ; i++) {
			for (int j = 0 ; j < 5 ; j++) {
				if (allume[i][j]) {
					gg = false;
				}
			}
		}
		return gg;
	}
	
	
	/*
	 * Renvoie l'etat d'une lumiere precise
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 * @return allume[x][y]
	 */
	public boolean getAllume(int x, int y) {
		return allume[x][y];
	}
	
	/*
	 * Renvoie une case precise
	 * 
	 * @param x coordoonees x
	 * @param y coordoonees y
	 * @return tab[x][y];
	 */
	public JPanel getCase(int x, int y) {
		return tab[x][y];
	}
	
	/*
	 * Renvoie la grille des lumieres avec leur etats
	 *
	 * @return allume
	 */
	public boolean[][] getTabAllume() {
		return allume;
	}

}

