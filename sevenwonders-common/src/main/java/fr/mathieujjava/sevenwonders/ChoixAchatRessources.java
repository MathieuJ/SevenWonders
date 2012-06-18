package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Place;
import fr.mathieujjava.sevenwonders.enums.Ressource;

public class ChoixAchatRessources {
	Integer piecesPourBanque = 0;
	
	Integer piecesPourDroite = 0;

  Integer piecesPourGauche = 0;
  
	List<Ressource> ressourcesDroite = new ArrayList<Ressource>();
	
	List<Ressource> ressourcesGauche = new ArrayList<Ressource>();
	
	List<Ressource> ressourcesSoi = new ArrayList<Ressource>();
	
	public ChoixAchatRessources addAchat(Place place,  Ressource ressource){
		if (place == Place.Gauche) {
			ressourcesGauche.add(ressource);
		} else if (place == Place.Droite) {
			ressourcesDroite.add(ressource);
		} else {
			ressourcesSoi.add(ressource);
		}
		return this;
	}
	
	public ChoixAchatRessources clone(){
		ChoixAchatRessources res = new ChoixAchatRessources();
		for (Ressource ressource : ressourcesGauche){
			res.ressourcesGauche.add(ressource);
		}
		for (Ressource ressource : ressourcesDroite){
			res.ressourcesDroite.add(ressource);
		}
		for (Ressource ressource : ressourcesSoi){
			res.ressourcesSoi.add(ressource);
		}
		return res;
	}
	
	
	public void setPiecesPourBanque(Integer piecesPourBanque) {
    this.piecesPourBanque = piecesPourBanque;
  }

  public void setPiecesPourDroite(Integer piecesPourDroite) {
    this.piecesPourDroite = piecesPourDroite;
  }

  public void setPiecesPourGauche(Integer piecesPourGauche) {
    this.piecesPourGauche = piecesPourGauche;
  }
}