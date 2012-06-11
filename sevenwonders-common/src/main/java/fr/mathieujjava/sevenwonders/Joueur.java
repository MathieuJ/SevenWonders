package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class Joueur {
	Integer etageMerveille;
	
	Merveille merveille;

	List<Carte> listeCartes = new ArrayList<Carte>();

	List<Medaille> listeMedailles = new ArrayList<Medaille>();

	List<Carte> main = new ArrayList<Carte>();

	Integer nombrePieces;

	Integer place;

	public Joueur(Merveille merveille) {
		this.merveille = merveille;
		this.nombrePieces = 3;
		this.etageMerveille = 0;
	}

	public Integer getEtageMerveille() {
		return etageMerveille;
	}

	public void setEtageMerveille(Integer etageMerveille) {
		this.etageMerveille = etageMerveille;
	}

	public Integer getNombrePieces() {
		return nombrePieces;
	}

	public void modifieNombrePieces(Integer nombrePieces) {
		this.nombrePieces += nombrePieces;
	}

	public Merveille getMerveille() {
		return merveille;
	}

	public List<Carte> getListeCartes() {
		return listeCartes;
	}

	public List<Medaille> getListeMedailles() {
		return listeMedailles;
	}

	public List<Carte> getMain() {
		return main;
	}
	
	public Integer getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int compteNombreCartes(TypeCarte typeCarte) {
		int i = 0;
		for (Carte carte : listeCartes) {
			if (carte.getType() == typeCarte) {
				i++;
			}
		}
		return i;
	}

	public void setMain(List<Carte> main) {
		this.main = main;
	}
}