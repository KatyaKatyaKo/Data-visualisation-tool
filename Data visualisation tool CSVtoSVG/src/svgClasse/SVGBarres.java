package svgClasse;

import Application.Figure;
import mesInterfaces.interfaceSVG;

public class SVGBarres extends SVG implements interfaceSVG{
	
	//Constructeur
	public SVGBarres(Figure [][] Donnees)  {
		super(height,couleurFond, couleurBarres,largueurbarres, espacementBarres, Donnees);
	}
	
	//Méthodes de l'interface
	public String body() { 
		int couleurPosY = 20;
		String donAnnee = null;
		String donStr = null;
		String donCoul = null;
		int countPosX = 20;
		
		for(int i=1; i<Donnees.length; i++) {
			int couleur = 0;
			for(int j=1; j<Donnees[i].length; j++) {
				donStr = donStr+ "\n<rect width=\"20\" height=\"" + Donnees[i][j].getResult() + "\" x=\"" + countPosX + "\" y=\"" + (400-Donnees[i][j].getResult())+ "\" fill=\""+getCouleurBarres()[couleur]+"\"/>"/*+
						"\n<text x=\""+ (countPosX+((largueurbarres)/2)) +"\" y=\""+430+"\" font-size=\"10\" style=\"text-anchor:middle;\">"+Donnees[i][j].getAnnee()+"</text>"*/;
				countPosX= countPosX+30;
				couleur++;
			}
			donAnnee=donAnnee+"\n<text x=\""+ ((countPosX-(30*(Donnees[i].length-1))+((Donnees[i].length*largueurbarres)/2))) +"\" y=\""+430+"\" font-size=\"10\" style=\"text-anchor:middle;\">"+Donnees[i][0].getAnnee()+"</text>";
		}
		
		//Dessiner les declaration couleurs + name donneees
		for(int k=0; k<=(Donnees[0].length-2); k++) {
			donCoul = donCoul+ "\n<rect width=\"10\" height=\"10\" x=\"" + (((sizeDonnees())*30)+50) + "\" y=\"" + (couleurPosY+14)+ "\" fill=\""+getCouleurBarres()[k]+"\"/>"+
					"\n<text x=\""+ ((sizeDonnees()*30)+80) +"\" y=\""+(couleurPosY+22.5)+"\" font-size=\"10\" style=\"text-anchor:middle;\">"+Donnees[1][k+1].getName()+"</text>";
			couleurPosY+=20;
		}
		return donStr+donCoul+donAnnee;
	}
	
	//Ecriture de SVG
	public String toString() {
		return super.entete()+body()+super.pied();
	}	
}
