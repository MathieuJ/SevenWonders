package fr.mathieujjava.sevenwonders;

import java.util.Arrays;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class Merveille {
  Cout[] coutsAge;

  public Cout[] getCoutsAge() {
    return coutsAge;
  }

  Integer face;
  String nomEn;
  String nomFr;
  Ressource ressource;
  String texte;
  String image;

  public Merveille() {

  }

  public Merveille(String nomEn, String nomFr, Integer face, String texte, Ressource ressource, String couts, String image) {
    this.nomEn = nomEn;
    this.nomFr = nomFr;
    this.face = face;
    this.texte = texte;
    this.ressource = ressource;
    String[] sCouts = couts.split(",");
    coutsAge = new Cout[sCouts.length];
    int i = 0;
    for (String sCout : sCouts) {
      Cout cout = new Cout(sCout);

      System.out.println(" " + i + " " + cout);
      coutsAge[i] = cout;
      i++;
    }
    this.image = image;
  }

  /*
   * public Merveille(String nomEn, String nomFr, Integer face, String texte,
   * Ressource ressource, Cout... cout) { this.nomEn = nomEn; this.nomFr =
   * nomFr; this.face = face; this.texte = texte;
   * 
   * this.ressource = ressource; coutsAge = cout; }
   */

  /*
   * public Merveille(String nomEn, String nomFr, Integer face, String texte,
   * Ressource ressource, List<Cout> couts) { this.nomEn = nomEn; this.nomFr =
   * nomFr; this.face = face; this.texte = texte;
   * 
   * this.ressource = ressource; coutsAge = (Cout[]) couts.toArray(); }
   */

  public Cout getCoutAge(Integer age) throws Exception {
    if (age > coutsAge.length) {
      throw new Exception("Pas d'age " + age + " pour cette merveille");
    }
    return coutsAge[age - 1];
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

  public String getImage() {
    return image;
  }

}
