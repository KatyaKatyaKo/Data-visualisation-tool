package svgClasse;

import Application.Figure;
import mesInterfaces.interfaceSVG;

public class SVGLignes extends SVG implements interfaceSVG {
	
	//Constructeur
	public SVGLignes(Figure [][] Donnees) {
		super(height,couleurFond, couleurBarres,largueurbarres, espacementBarres, Donnees);
	}

	//Méthodes de l'interface
	public String body() {
		int largueur = (Donnees[0].length-1)*largueurbarres;
		int countPosX1=50;
		int countPosX=countPosX1+largueur;
		int couleurPosY = 20;
		String donAnnee = null;
		String donStr = null;
		String donCoul = null;
		
		for(int i=1; i<(Donnees.length-1); i++) { //annees
			int couleur = 0;
			for(int j=1; j<=Donnees[i].length-1; j++) { //lignes par annee et par couleur
				donStr = donStr+"\n<line x1=\"" +countPosX1  + "\" y1=\""+(400-Donnees[i][j].getResult())  +"\" x2=\"" + countPosX + "\" y2=\"" + (400-Donnees[i+1][j].getResult())+ "\" stroke=\""+getCouleurBarres()[couleur]+"\" stroke-width=\""+1+"\"/>";
				couleur++;
				donAnnee=donAnnee+"\n<text x=\""+ countPosX1 +"\" y=\""+430+"\" font-size=\"9\">"+Donnees[i][1].getAnnee()+"</text>";
			}	
			countPosX= countPosX+largueur;
			countPosX1=countPosX1+largueur;	
		}
		donAnnee=donAnnee+"\n<text x=\""+ countPosX1 +"\" y=\""+430+"\" font-size=\"9\">"+Donnees[Donnees.length-1][1].getAnnee()+"</text>";
		
		//Dessiner les declaration couleurs + name donneees
				for(int k=0; k<=(Donnees[0].length-2); k++) {
					donCoul = donCoul+ "\n<rect width=\"10\" height=\"10\" x=\"" + (((sizeDonnees())*30)+40) + "\" y=\"" + (couleurPosY+10)+ "\" fill=\""+getCouleurBarres()[k]+"\"/>"+
							"\n<text x=\""+ ((sizeDonnees()*30)+70) +"\" y=\""+(couleurPosY+17.5)+"\" font-size=\"10\" style=\"text-anchor:middle;\">"+Donnees[1][k+1].getName()+"</text>";
					couleurPosY+=20;
				}
				return donStr+donCoul+donAnnee;
	}
		
	public String toString() {
		return super.entete()+body()+super.pied();
	}
}
