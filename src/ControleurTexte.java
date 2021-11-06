import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;

public class ControleurTexte {

	@SuppressWarnings("deprecation")
	/*
	 * Un petit programme permettant de verifier que le vue texte et la vue graphique marchent correctement
	 */
	public static void main ( String [] args ) {
		Lumiere l = new Lumiere();
		VueTexte vt = new VueTexte();

		VueGraphique vg = new VueGraphique();
		vg.setPreferredSize(new Dimension(250, 250));
		JFrame f = new JFrame();
		f.setContentPane(vg);
		f.pack();
		f.setVisible(true);
		
		l.addObserver(vt);
		l.addObserver(vg);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Coordonnees x : ");
		int x = sc.nextInt();
		System.out.println("Coordonnees y : ");
		int y = sc.nextInt();
		
		
		while (true) {
			l.jouer(x, y);
			System.out.println("Lumiere selectionnee : "+vt.getLabel(x, y)+"\n\n");
			System.out.println("Coordonnees x : ");
			x = sc.nextInt();
			System.out.println("Coordonnees y : ");
			y = sc.nextInt();
		}

		
		
	}
	
}
