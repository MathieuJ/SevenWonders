package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class Cout {
	List<Ressource> listeRessources = new ArrayList<Ressource>();
	
	Integer prix = 0;
	
	public Cout(Integer prix, Ressource ... ressources) {
		this.prix = prix;
		for (Ressource ressource : ressources) {
			listeRessources.add(ressource);
		}
	}
	
	public Integer getPrix() {
		return prix;
	}

	public List<Ressource> getListeRessources() {
		return listeRessources;
	}

	public void retire(int i) throws Exception {
		if (i > prix) {
			throw new Exception("Pas assez de sous !");
		}
		prix -= i;
	}

	public void retire(Ressource ... ressources) {
		for (Ressource ressource : ressources) {
			listeRessources.remove(ressource);
		}
	}
}
