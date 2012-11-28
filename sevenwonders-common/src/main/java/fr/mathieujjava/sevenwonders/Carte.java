package fr.mathieujjava.sevenwonders;

import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class Carte {
  public Carte(TypeCarte type, String nomEn, String nomFr, Cout cout, Carte parent, String texte) {
    super();
    this.parent = parent;
    this.cout = cout;
    this.nomEn = nomEn;
    this.nomFr = nomFr;
    this.texte = texte;
    this.type = type;
  }

  public Carte(TypeCarte type, String nomEn, String nomFr, String nombreJoueurRequis, Cout cout, String parent, String texte) {
    super();
    //this.parent = parent;
    this.cout = cout;
    this.nomEn = nomEn;
    this.nomFr = nomFr;
    this.nombreJoueurRequis = nombreJoueurRequis;
    this.texte = texte;
    this.type = type;
  }

  private Carte parent;
  
  private String nombreJoueurRequis;
  
  private Cout cout;

  private String nomEn;

  private String nomFr;

  private String texte;

  private TypeCarte type;

  public Carte getParent() {
    return parent;
  }

  public Cout getCout() {
    return cout;
  }

  public String getNomEn() {
    return nomEn;
  }

  public String getNomFr() {
    return nomFr;
  }

  public String getTexte() {
    return texte;
  }

  public TypeCarte getType() {
    return type;
  }

  public String getNombreJoueurRequis() {
    return nombreJoueurRequis;
  }

  @Override
  public String toString() {
    return "Carte [" + nomFr + "-" + texte + "]";
  }

}
