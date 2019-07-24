package svgClasse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import Application.Figure;

public  class SVG {
	static protected Figure [][] Donnees; 
	
	/**
	 * @param couleurbarres
	 * @param couleurbackground
	 * @param height
	 * @param largueurbarres
	 * @param espacementbarres
	 */
	static String[] couleurBarres = {"blue","yellow","violet","gold","black"};
	static protected double height = 600;
	static protected int largueurbarres = 20;
	static protected int espacementBarres= 10;
	static protected String couleurFond = "grey";
	static private int countPosX=20;

	public SVG(double height, String couleurFond, String [] couleurBarres, int largueurbarres, int espacementbarres, Figure [][] Donnees) {
		SVG.height = height;
		SVG.couleurFond = couleurFond;
		SVG.couleurBarres = couleurBarres;
		SVG.largueurbarres = largueurbarres;
		SVG.espacementBarres = espacementbarres;
		SVG.Donnees=Donnees;
	}
	
	//Setters
	public void setHeight(int height) {SVG.height = height;}
	public void setCouleurBarres(String [] couleurBarres) {SVG.couleurBarres = couleurBarres;}
	public void setEspacementBarres(int espacementBarres) {SVG.espacementBarres = espacementBarres;}
	public void setCouleurFond(String couleurFond) {SVG.couleurFond = couleurFond;}
	public void setLargueurBarres(int largueurbarres) {SVG.largueurbarres = largueurbarres;}
	
	//Getters
	public double getHeight() {return SVG.height;}
	public String [] getCouleurBarres() {return SVG.couleurBarres;}
	public int getLargueurBarres() {return largueurbarres;}
	public String getCouleurFond() {return couleurFond;}
	public int getEspacementBarres() {return espacementBarres;}
	public Figure[][] getDonnees() {return SVG.Donnees;}
	
	protected int sizeDonnees() {
		int size = 0;
		for(int i=1; i<Donnees.length; i++) {
			for(int j=1; j<Donnees[i].length; j++) {
				size++;
			}
		}
		return size;
	}
	
	private String grillage() {
		String grillage="";
		for(int i=1; i<=(int)412/100; i++) {
			grillage=grillage+"\n<line x1=\"" +(countPosX-10)  + "\" y1=\""+ (410-(i*100)) +"\" x2=\"" + (((sizeDonnees())*30)+32) + "\" y2=\"" + (410-(i*100)) + "\" stroke=\"darkgrey\" stroke-width=\"1\"/>"
							 +"\n<text x=\""+(((sizeDonnees())*30)+21)+"\" y=\""+ (422-(i*100))+ "\" font-size=\"10\" style=\"text-anchor:middle;\" fill=\"darkgrey\">"+(i*100)+"</text>" ;	
		}
		return grillage;
	}
	
	protected String entete() {
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
			+"<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\""+(((sizeDonnees())*30)+120) +"\" height=\""+getHeight()+"\">\n"
			+"<rect width=\""+(((sizeDonnees())*30)+120)+"\" height=\""+getHeight()+"\" x=\"0\" y=\"0\" fill=\""+getCouleurFond()+"\"/>"
			+"\n<line x1=\"" +(countPosX-10)  + "\" y1=\""+ 410 +"\" x2=\"" + (((sizeDonnees())*30)+22) + "\" y2=\"" + 410 + "\" stroke=\"black\" stroke-width=\"4\"/>"
			+"\n<line x1=\"" +(countPosX-12)  + "\" y1=\""+ 412 /*400+ width barre*/ +"\" x2=\"" + (countPosX-12) + "\" y2=\"" + 5 + "\" stroke=\"black\" stroke-width=\"4\"/>"
			+grillage();
	}
	
	protected String pied() {
		return "\n</svg>";
	}
	
/////////////////////////Méthodes FICHIER//////////////
	
	public static void ecrireSVG(String file, Object Donnees, SVG graphique) throws FileNotFoundException  {
		boolean testFichier = false;
		do {
			try {
				PrintWriter pw = new PrintWriter(file);
				pw.println(graphique.toString());
				pw.close(); 
				testFichier=true;
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("Le chemin est incorrect. Resaisissez, svp:");
				testFichier = false;
			}
		} while (testFichier != true);
	}
	
	public static String saisieCheminExtension(String extension) {  //Teste le format de saisie du path
		System.out.println("Indiquez le chemin vers le fichier " + extension+":");
		String chemin;
		do {
			Scanner sc = new Scanner (System.in);
			chemin = sc.nextLine();
		if(chemin.equals("")){  //si chemin vide
			System.out.println("Le champs ne doit pas être vide"); 
		}
		else if(chemin.length()<5){
			System.out.println("Le chemin est trop court"); 
		}
		else if	(!(chemin.substring((chemin.length()-4),(chemin.length())).matches(extension))) { 
			System.out.println("Le fichier doit avoir une extension "+extension);
		}
		else {
			return chemin;	
		}
		}
		while (chemin.equals("")||(!chemin.matches("[A-Za-z\\/\\d-_]+" + extension)));
		//sc.close();  //QUAND EST-CE QU'ON CLOSE LE SCANNER?
		return null;	
	}

	public static boolean fichierConforme(String chemin) throws FileNotFoundException {
	boolean fichierConforme=false;
	File fichier = new File(chemin);
	Scanner sc = new Scanner(fichier);
	
	String [] lineEntete=sc.nextLine().split(","); //lit la première ligne du fichier et la prend comme critère de comparaison
	int numLigne=2;  //car ligne 1 = entête
	do {
		String [] line=sc.nextLine().split(",");
		if(lineEntete.length!=line.length) {
		System.out.println("Fichier n'est pas conforme. Problème sur la ligne "+ numLigne+". Veillez corriger le fichier.\n");
		fichierConforme=false;
		break;
	}
	else {
		fichierConforme=true;
		numLigne++;
	}
	}
	while(sc.hasNextLine());
	sc.close();
	return fichierConforme;
	}

	public static int taille(File fichier) throws FileNotFoundException { //Retourne la longueur du fichier
		int nmbrLignes=0;
		Scanner sc = new Scanner (fichier);
		do { //parcourt fichier
			sc.nextLine();
			nmbrLignes++;
		}
		while(sc.hasNextLine());
		sc.close();
		return nmbrLignes;
	}

	public static int nbColonnes (File fichier) throws FileNotFoundException {
		Scanner sc = new Scanner (fichier);
		String [] ligne = sc.nextLine().split(",");
		return ligne.length;	
	}
	
	public static Figure [][] lireDonnees() throws FileNotFoundException {
		boolean testFichier=false;
		Scanner sc = null;
		File fichier1 = null;
		String chemin = null;
		Figure [][] Donnees = null;
		do {
			try {
				chemin = saisieCheminExtension(".csv");
				fichier1 = new File(chemin);
				sc = new Scanner(fichier1); // Lecture du fichier //FAUT PAS OUBLIER DE LE FERMER
				if(fichier1.length()==0) { //Si le fichier est vide
					System.out.println("Le fichier est vide. Veillez choisir un autre fichier.");
					testFichier=false;
				}
				else if(fichierConforme(chemin)==false){ //si le fichier n'est pas conforme
					testFichier=false;
				}
				else { //Lecture fichier
				testFichier=true;
				//Stocker le resultat dans un tableau
				Scanner scanDonnee=null;
				Donnees = new Figure [taille(fichier1)] [nbColonnes(fichier1)]; //nombre de ligne, car pas besoin d'entête
				//Entete: annee, citron, orange 
				//Titre
				scanDonnee = new Scanner(fichier1);
				String line= scanDonnee.nextLine();
				String [] names = line.split(",");
				String [] lineSplit  = line.split(",");
				do {
					for(int i=1; i<Donnees.length; i++) { //à partir de 2e ligne
						line= scanDonnee.nextLine(); //premiere ligne
						for (int j=0; j<Donnees[i].length; j++) {
							lineSplit  = line.split(",");
							String annee= lineSplit[0]; //denomination
							String result= lineSplit[j]; //chiffres
							
							int anneeConverted = Integer.parseInt(annee);
							int resultConverted = Integer.parseInt(result);
							
							String name = names[j];
						
							Donnees[i][j] = new Figure(name,anneeConverted , resultConverted);
						}
					}
				}
				while(scanDonnee.hasNextLine());
				scanDonnee.close();	
				}
			} catch (FileNotFoundException fnfe) {
				System.out.println("Le fichier n'existe pas. Resaisissez, svp:");
				testFichier = false;
			}
		} while (testFichier != true);
		sc.close();
		return Donnees;
	}
}
