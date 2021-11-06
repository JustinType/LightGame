import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Principale {


	@SuppressWarnings("deprecation")
	/*
	 * Programme principal
	 */
	public static void main(String[] args) {
		/*
		 * Initialisation des variables du modele des vues et du controleur
		 */
		Lumiere lum  = new Lumiere();
		VueGraphique vg = new VueGraphique();
		lum.addObserver(vg);
		ControleurGraphique cg = new ControleurGraphique(lum);
		
		/*
		 * Creation des composants
		 */
		JPanel jeu = new JPanel();
		jeu.setLayout(new BorderLayout());
		
		JButton Configurer = new JButton("Configurer");
		JButton Aleatoire = new JButton("Aleatoire");
		JButton Jouer = new JButton("Jouer");
		Jouer.setEnabled(false);
		JButton Quitter = new JButton("Quitter");
		Quitter.setEnabled(false);
		JLabel Deplacement = new JLabel("Nb Deplacements : 0");
		
		JPanel gauche = new JPanel();
		JPanel droite = new JPanel();
		
		/*
		 * Organisation des composants
		 */
		gauche.setLayout(new GridLayout(5,1));
		gauche.add(Configurer);
		gauche.add(Aleatoire);
		gauche.add(Jouer);
		gauche.add(Deplacement);
		gauche.add(Quitter);
		
		droite.add(vg);

		
		jeu.add(gauche, BorderLayout.CENTER);
		jeu.add(droite, BorderLayout.EAST);
		jeu.setPreferredSize(new Dimension(500, 300));
		
		/*
		 * Creation des mouse listeners
		 */
		MouseListener mljouer = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}


			@Override
			public void mousePressed(MouseEvent e) {
				// Pas tres elegant mais je vois pas comment faire autrement
				int x, y;
				
				// Pour x
				if (e.getX() < 50) {
					x = 0;
				} else if (e.getX() >= 50 && e.getX() < 100) {
					x = 1;
				} else if (e.getX() >= 100 && e.getX() < 150) {
					x = 2;
				} else if (e.getX() >= 150 && e.getX() < 200) {
					x = 3;
				} else {
					x = 4;
				}
				
				// Pour y
				if (e.getY() < 50) {
					y = 0;
				} else if (e.getY() >= 50 && e.getY() < 100) {
					y = 1;
				} else if (e.getY() >= 100 && e.getY() < 150) {
					y = 2;
				} else if (e.getY() >= 150 && e.getY() < 200) {
					y = 3;
				} else {
					y = 4;
				}
				lum.jouer(x, y);
				cg.clic();
				Deplacement.setText("Nb Deplacements : " + cg.getNb());
			}


			@Override
			public void mouseReleased(MouseEvent e) {}


			@Override
			public void mouseEntered(MouseEvent e) {}


			@Override
			public void mouseExited(MouseEvent e) {}
			
			
		};
		
		
		MouseListener mlconfig = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}


			@Override
			public void mousePressed(MouseEvent e) {
				// Pas tres elegant mais je vois pas comment faire autrement
				int x, y;
				
				// Pour x
				if (e.getX() < 50) {
					x = 0;
				} else if (e.getX() >= 50 && e.getX() < 100) {
					x = 1;
				} else if (e.getX() >= 100 && e.getX() < 150) {
					x = 2;
				} else if (e.getX() >= 150 && e.getX() < 200) {
					x = 3;
				} else {
					x = 4;
				}
				
				// Pour y
				if (e.getY() < 50) {
					y = 0;
				} else if (e.getY() >= 50 && e.getY() < 100) {
					y = 1;
				} else if (e.getY() >= 100 && e.getY() < 150) {
					y = 2;
				} else if (e.getY() >= 150 && e.getY() < 200) {
					y = 3;
				} else {
					y = 4;
				}
				lum.switchAllume(x, y);
			}


			@Override
			public void mouseReleased(MouseEvent e) {}


			@Override
			public void mouseEntered(MouseEvent e) {}


			@Override
			public void mouseExited(MouseEvent e) {}
			
			
		};
		
		
		/*
		 * Configuration des boutons
		 */
		Configurer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aleatoire.setEnabled(false);
				Jouer.setEnabled(true);
				Quitter.setEnabled(true);
				vg.addMouseListener(mlconfig);
			}
		});
		
		Jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vg.removeMouseListener(mlconfig);
				vg.addMouseListener(mljouer);
				Configurer.setEnabled(false);
				Aleatoire.setEnabled(false);
				Jouer.setEnabled(false);
				Quitter.setEnabled(true);
				
			}
		});
		
		
		Aleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lum.aleatoire();
				Configurer.setEnabled(false);
				Jouer.setEnabled(true);
				Quitter.setEnabled(true);
				vg.removeMouseListener(mljouer);
				vg.removeMouseListener(mlconfig);
			}
		});
		
		Quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cg.reset();
				lum.clear();
				lum.jouer(-1, -1);
				Aleatoire.setEnabled(true);
				Configurer.setEnabled(true);
				Jouer.setEnabled(false);
				Quitter.setEnabled(false);
				Deplacement.setText("Nb Deplacements : " + cg.getNb());
				vg.removeMouseListener(mljouer);
				vg.removeMouseListener(mlconfig);
			}
		});
		

		/*
		 * Creation de la fenetre et ajout des composants
		 */
		JFrame f = new JFrame();
		vg.setPreferredSize(new Dimension(250, 250));
		f.setContentPane(jeu);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		
		// Si je ne mets pas cette ligne la grille ne s'affiche pas
		// Il faut jouer une fois dans le vide pour qu'elle s'affiche
		lum.jouer(-1, -1);
		
		/*
		 * Detection de fin du jeu
		 */
		boolean fini = false;
		while (!fini) {
			System.out.println("Toutes les lumieres eteintes : "+lum.gagner());
			if (cg.getNb() != 0 && lum.gagner()) {
				JLabel gg = new JLabel("Bravo !");
				JLabel dep = new JLabel("Vous avez gagne en "+cg.getNb()+" deplacements");
				JPanel j = new JPanel();
				
				j.add(gg);
				j.add(dep);

				JFrame fr = new JFrame();
				fr.setPreferredSize(new Dimension(400, 500));
				fr.setContentPane(j);
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.pack();
				fr.setVisible(true);
				fini = true;
			}
		}
	}
	
}
