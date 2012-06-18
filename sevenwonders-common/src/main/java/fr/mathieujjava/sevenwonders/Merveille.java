package fr.mathieujjava.sevenwonders;

import fr.mathieujjava.sevenwonders.enums.Ressource;


public class Merveille {
  Cout coutAgeI, coutAgeII, coutAgeIII, coutAgeIV;

  Integer face;
  String nomEn;
  String nomFr;
  Ressource ressource;
  String texte;

  public Merveille(){
    
  }
  
  public Merveille(String nomEn, String nomFr, Integer face, String texte, Ressource ressource) {
    this.nomEn = nomEn;
    this.nomFr = nomFr;
    this.face = face;
    this.texte = texte;
    this.ressource = ressource;
  }

  
  
  public Merveille(String nomEn, String nomFr, Integer face, String texte, Ressource ressource, Cout... cout) {
    this.nomEn = nomEn;
    this.nomFr = nomFr;
    this.face = face;
    this.texte = texte;

    this.ressource = ressource;
    if (cout.length > 0) {
      coutAgeI = cout[0];
    }
    if (cout.length > 1) {
      coutAgeII = cout[1];
    }
    if (cout.length > 2) {
      coutAgeIII = cout[2];
    }
    if (cout.length > 3) {
      coutAgeIV = cout[3];
    }
  }

  public Cout getCoutAgeI() {
    return coutAgeI;
  }

  public Cout getCoutAgeII() {
    return coutAgeII;
  }

  public Cout getCoutAgeIII() {
    return coutAgeIII;
  }

  public Cout getCoutAgeIV() {
    return coutAgeIV;
  }

  public Integer getFace() {
    return face;
  }

  public String getNomEn() {
    return nomEn;
  }

  public String getNomFr() {
    return nomFr;
  }

  public Ressource getRessource() {
    return ressource;
  }

  public String getTexte() {
    return texte;
  }

}
