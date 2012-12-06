package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class Joueur {
  private Integer etageMerveille;

  private Merveille merveille;

  private List<Carte> listeCartes = new ArrayList<Carte>();

  private List<Medaille> listeMedailles = new ArrayList<Medaille>();

  private List<Carte> main = new ArrayList<Carte>();

  private Integer nombrePieces;

  private Integer place;
  
  private Boolean bot;

  public Joueur(Merveille merveille, Boolean isBot) {
    this.merveille = merveille;
    this.nombrePieces = 3;
    this.etageMerveille = 0;
    this.bot = isBot;
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

  public List<Carte> getListeCarte(TypeCarte type) {
    List<Carte> l = new ArrayList<Carte>();
    for (Carte carte : listeCartes) {
      if (carte.getType() == type) {
        l.add(carte);
      }
    }
    return l;
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

  public void ajouteMedaille(Medaille medaille) {
    listeMedailles.add(medaille);
  }

  public boolean possede(String nomParent) {
    if (nomParent == null)
      return false;
    for (Carte carte : listeCartes) {
      if (nomParent.equals(carte.getNomEn())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return "\nJoueur [etageMerveille=" + etageMerveille + ", merveille="
        + merveille + ", nombrePieces=" + nombrePieces
        + ", place=" + place + ",\n   listeCartes=" + listeCartes + ", listeMedailles="
        + listeMedailles + ",\n   main=" + main + "]";
  }

  public Boolean isBot() {
    return bot;
  }

  public void setBot(Boolean bot) {
    this.bot = bot;
  }

  public void setMerveille(Merveille merveille) {
    this.merveille = merveille;
  }
  
}