
public class ControleurGraphique {
	Lumiere modele;
	int nb;

	/*
	 * Constructeur permettant de lier la vue graphique au modele
	 * 
	 * @param l modele
	 */
	public ControleurGraphique(Lumiere l) {
		modele = l;	
		nb = 0;
	}
	
	/*
	 * Incremente le nombre de clic
	 */
	public void clic() {
		nb += 1;
	}
	
	/*
	 * Remets le nombre de clic a 0
	 */
	public void reset() {
		nb = 0;
	}
	
	/*
	 * Permet de connaitre le nombre de clic
	 * 
	 * @return nb nombre de clics
	 */
	public int getNb() {
		return nb;
	}
	
	
}
