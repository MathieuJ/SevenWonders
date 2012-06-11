package fr.mathieujjava.sevenwonders;

import fr.mathieujjava.sevenwonders.enums.TypeAction;

public class Action {
  Joueur joueur;
  
  TypeAction typeAction;

  Carte carte;

  public Action(Joueur joueur, TypeAction typeAction, Carte carte) {
    super();
    this.joueur = joueur;
    this.typeAction = typeAction;
    this.carte = carte;
  }

  public TypeAction getTypeAction() {
    return typeAction;
  }

  public Carte getCarte() {
    return carte;
  }

  public Joueur getJoueur() {
    return joueur;
  }
  
  
}
