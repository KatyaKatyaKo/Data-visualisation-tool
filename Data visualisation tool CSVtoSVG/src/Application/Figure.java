package Application;

public class Figure {
	/**
	 * @param name
	 * @param annee
	 * @param result
	 */
	String name; // exemple: bananes, oranges
	int annee;
	int result;

//Costructeur	
	public Figure(String name, int annee, int result) {
		this.name = name;
		this.annee = annee; //Utile pour affichage
		this.result = result; //Egale hauteur de la colonne
	}

//Getters & Setters
	public int getAnnee() {
		return annee;
	}

	public int getResult() {
		return result;
	}
	
	public String getName() {
		return name;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public void setName(String name) {
		this.name = name;
	}

//toStirng
	public String toString() {
		return this.name + "/" + this.annee + "/" + this.result;
	}


}
