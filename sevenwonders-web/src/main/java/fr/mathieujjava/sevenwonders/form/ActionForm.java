package fr.mathieujjava.sevenwonders.form;

import fr.mathieujjava.sevenwonders.enums.TypeAction;

public class ActionForm {
  private Integer numJoueur;
  
  private TypeAction typeAction;

  private Integer numCarte;
  
  public Integer getNumJoueur() {
    return numJoueur;
  }

  public void setNumJoueur(Integer numJoueur) {
    this.numJoueur = numJoueur;
  }

  public TypeAction getTypeAction() {
    return typeAction;
  }

  public void setTypeAction(TypeAction typeAction) {
    this.typeAction = typeAction;
  }

  public Integer getNumCarte() {
    return numCarte;
  }

  public void setNumCarte(Integer numCarte) {
    this.numCarte = numCarte;
  }
  
  
}
