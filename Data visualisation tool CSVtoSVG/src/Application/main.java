package Application;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import GUI.opener;
import svgClasse.SVG;
import svgClasse.SVGBarres;
import svgClasse.SVGLignes;

public class main {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)  throws Exception, NullPointerException {
		
//*************************************OPENER*******************************
		     new opener(); //JFrame

//*************************************OUVERTURE DU FICHIER CSV*******************************
		System.out.println("BIENVENUE DANS NOTRE APPLICATION\n");
		
		int choix = -1;
		Figure [] [] Donnees=null;
		do {
			try {
				System.out.println("1 - Lire les données"); 
				System.out.println("2 - Choisir le type de graphique"); 
				System.out.println("0 - Quitter l'application"); 
				System.out.print("\tVotre choix:");
				choix = sc.nextInt();
				switch (choix) {
				case 1: //Lire les données
					Donnees = SVG.lireDonnees();
					System.out.println("Les données ont été lu avec succès\n"); //NB: pas de break, il continue vers le type de graphique directement, car sans ça on ne peut pas dessiner un graphique
				case 2:
					SVG graphique =menuTypeGraphique(Donnees); //polymorphisme 
					menuDessinerGraphique(Donnees, graphique); //paramètres par default déjà renseignés
					break;
				default:
					System.out.println("Le choix doit être compris entre 0 et 2. Merci de resaisir:");
					break;
				case 0:
					break;
				}
			} catch (InputMismatchException ime) {
				System.out.println("La sasie doit être numérique. Merci de resaisir:");
				sc.nextLine();
			}
		} while (choix != 0);
		System.out.println("Merci d'avoir utilisé notre application. Au revoir.");
	}

	
	/////////////////////////////////METHODES MENU///////////////////////////////
	public static SVG menuTypeGraphique(Figure [][] Donnees) throws FileNotFoundException {
		int choix = -1;
		SVG graphique = null;
			try {
				System.out.println("Choisissez votre graphique:");
				System.out.println("1 - Graphique à colonnes");
				System.out.println("2 - Graphique en courbes");
				System.out.println("0 - Retourner au menu précédent");
				System.out.print("\tVotre choix: ");
				
				choix = sc.nextInt();
				switch (choix) {
				case 1: 
					graphique = new SVGBarres((Figure [][]) Donnees);
					break;
				case 2:
					graphique = new SVGLignes((Figure [][]) Donnees);
					break;
				default:
					System.out.println("Le choix doit être compris entre 0 et 2. Merci de resaisir:\n");
					break;
				case 0:
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextInt();
				System.out.println("Veillez saisir une valeur numérique:");
			}
		return graphique;
	}

	public static int menuDessinerGraphique(Figure [][] Donnees, SVG graphique) throws FileNotFoundException {
		int choix = -1;
		System.out.println("\nNB! Vous venez de choisir les données et le graphique mais vous pouvez changer un/tous ces paramètres à partir du ménu ci-dessous:");
		do {
			try {
				System.out.println("\nPour écrire le graphique veillez choisir:");
				System.out.println("1 - Lire les données");
				System.out.println("2 - Choisir le type de graphique");
				System.out.println("3 - Modifier les paramètres");
				System.out.println("4 - Créer le fichier");
				System.out.println("0 - Retourner au menu précédent");
				System.out.print("\tVotre choix: ");
				choix = sc.nextInt();
				switch (choix) {
				case 1: 
					Donnees = SVG.lireDonnees();
					graphique =menuTypeGraphique(Donnees);
					break;
				case 2:
					graphique=menuTypeGraphique(Donnees);
					break;
				case 3:
					modifierParametres(graphique, Donnees); 
					break;
				case 4:
					String file = SVG.saisieCheminExtension(".svg");
					SVG.ecrireSVG(file, Donnees, graphique);
					System.out.println("Le fichier vient d'être écrit\n");
					break;
				default:
					System.out.println("Le choix doit être compris entre 0 et 4. Merci de resaisir:");
					break;
				case 0:
					System.out.println("Retourner au menu précédent");
					break; 
				}
			} catch (InputMismatchException e) {
				System.out.println("Veillez saisir une valeur numérique:");
				sc.nextLine();
			}
		} while (choix != 0);
		return choix;
	}

	public static SVG modifierParametres(SVG graphique, Figure [][]Donnees) { //ici on a les paramètres par default, mais on peut les changer
		int choix = -1;
		do {
			try {
				System.out.println("\nVeillez choisir un paramètre du graphique: ");
				System.out.println("1 - Hauteur du graphique");
				System.out.println("2 - Couleur du fond");
				System.out.println("3 - Couleur des barres");
				System.out.println("4 - Largueur de barres");
				System.out.println("5 - Largueur d'espacement");
				System.out.println("0 - Retourner au menu précédent");
				System.out.print("\tVotre choix: ");
				choix = sc.nextInt();
				switch (choix) {
				case 1:
					System.out.println("Choisissez la hauteur de votre graphique (valeur par default "+ (int)graphique.getHeight()+"px) :");
					sc.nextLine();  //vide le retour charriot après le précédent
					graphique.setHeight(sc.nextInt());
					System.out.println("La hauteur a été fixée à "+ graphique.getHeight()+ " px.");
					break;
				case 2:
					System.out.println("Choisissez la couleur du fond (valeur par default "+ graphique.getCouleurFond()+") :");
					sc.nextLine();  //vide le retour chariot après le précédent
					graphique.setCouleurFond(sc.nextLine());
					System.out.println("La couleur du fond a été fixée à "+ graphique.getCouleurFond()+ ".");
					break;
				case 3:
					sc.nextLine();  //vide le retour chariot après le précédent
					String [] couleurs = new String [Donnees[0].length-1]; 
					System.out.println("Il vous faut choisir "+ couleurs.length + " couleurs.");
					for (int i=0; i<couleurs.length; i++) {
						System.out.println("Choisissez la couleur de la barre N:"+(i+1)); 
						couleurs[i] = sc.nextLine(); //vide le retour chariot après le précédent
					}
					graphique.setCouleurBarres(couleurs);
					System.out.println("Vous avez choisi:");
					for (int i=0;i<couleurs.length; i++) {
						System.out.println(couleurs[i]);
					}
					break;
				case 4:
					System.out.println("Choisissez la largeur des barres (valeur par default "+ graphique.getLargueurBarres()+") :");
					graphique.setLargueurBarres(sc.nextInt());
					System.out.println("La largeur des barres a été fixée à "+ graphique.getLargueurBarres()+ ".");
					break;
				case 5:
					System.out.println("Choisissez la largeur espacement (valeur par default "+ graphique.getEspacementBarres()+") :");
					graphique.setEspacementBarres(sc.nextInt());
					System.out.println("La largeur espacement a été fixée à "+ graphique.getLargueurBarres()+ ".");
					break;
				default:
					System.out.println("Le choix doit être compris entre 0 et 4. Merci de resaisir:");
					break;
				case 0:
					System.out.println("Revenir au menu princinaple");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Veillez saisir une valeur numérique:");
				sc.nextLine();
			}
		} while (choix != 0);
		return graphique;
	}	
}