package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class Partie {
  Integer age;

  Integer tour;

  List<Carte> defausse = new ArrayList<Carte>();

  List<Joueur> listeJoueurs = new ArrayList<Joueur>();

  public Partie() {
    age = 1;
    tour = 1;
  }

  public void addJoueur(Joueur joueur) {
    joueur.setPlace(listeJoueurs.size());
    listeJoueurs.add(joueur);
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setTour(Integer tour) {
    this.tour = tour;
  }

  public Integer getAge() {
    return age;
  }

  public Integer getTour() {
    return tour;
  }

  public List<Carte> getDefausse() {
    return defausse;
  }

  public List<Joueur> getListeJoueurs() {
    return listeJoueurs;
  }

  public Joueur getJoueur(int place) {
    return listeJoueurs.get(place);
  }

  public int getNbJoueurs() {
    return getListeJoueurs().size();
  }

  public Joueur getVoisinGauche(Joueur joueur) {
    return getJoueur((joueur.getPlace() + getNbJoueurs() - 1) % getNbJoueurs());
  }

  public Joueur getVoisinDroite(Joueur joueur) {
    return getJoueur((joueur.getPlace() + 1) % getNbJoueurs());
  }

  public int compteNombreCartes(Joueur joueur, TypeCarte typeCarte, boolean gauche, boolean soi, boolean droite) {
    int i = 0;
    if (gauche) {
      i += getVoisinGauche(joueur).compteNombreCartes(typeCarte);
    }
    if (soi) {
      i += joueur.compteNombreCartes(typeCarte);
    }
    if (droite) {
      i += getVoisinDroite(joueur).compteNombreCartes(typeCarte);
    }
    return i;
  }

  public void rotationCartes(boolean sensHoraire) {
    List<Carte> l1 = getJoueur(0).getMain();
    for (int i = 0; i < getNbJoueurs() - 1; i++) {
      getJoueur(i).setMain(getJoueur(i + 1).getMain());
    }
    getJoueur(getNbJoueurs() - 1).setMain(l1);
  }

}
