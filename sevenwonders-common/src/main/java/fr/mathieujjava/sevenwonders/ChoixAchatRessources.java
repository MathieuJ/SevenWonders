package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Place;
import fr.mathieujjava.sevenwonders.enums.Ressource;

public class ChoixAchatRessources {
	private Integer piecesPourBanque = 0;
	
	private Integer piecesPourDroite = 0;

  private Integer piecesPourGauche = 0;
  
	private List<Ressource> ressourcesDroite = new ArrayList<Ressource>();
	
	private List<Ressource> ressourcesGauche = new ArrayList<Ressource>();
	
	private List<Ressource> ressourcesSoi = new ArrayList<Ressource>();
	
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
	
	public Integer getTotalPieces() {
	  return piecesPourBanque + piecesPourDroite + piecesPourGauche;
	}
	
	public Integer getPiecesPourBanque() {
    return piecesPourBanque;
  }

  public Integer getPiecesPourDroite() {
    return piecesPourDroite;
  }

  public Integer getPiecesPourGauche() {
    return piecesPourGauche;
  }

  public List<Ressource> getRessourcesDroite() {
    return ressourcesDroite;
  }

  public List<Ressource> getRessourcesGauche() {
    return ressourcesGauche;
  }

  public List<Ressource> getRessourcesSoi() {
    return ressourcesSoi;
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

  public void setRessourcesDroite(List<Ressource> ressourcesDroite) {
    this.ressourcesDroite = ressourcesDroite;
  }

  public void setRessourcesGauche(List<Ressource> ressourcesGauche) {
    this.ressourcesGauche = ressourcesGauche;
  }

  public void setRessourcesSoi(List<Ressource> ressourcesSoi) {
    this.ressourcesSoi = ressourcesSoi;
  }
}