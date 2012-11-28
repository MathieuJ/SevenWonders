package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class Cout {
  private List<Ressource> listeRessources = new ArrayList<Ressource>();

  private Integer prix = 0;

  public Cout(String sCout) {
    System.out.println("ON FAIT : " + sCout);
    for (char c : sCout.toCharArray()) {
      switch (c) {
      case '1':
        prix = 1;
        break;
      case '2':
        prix = 2;
        break;
      case '3':
        prix = 3;
        break;
      case '4':
        prix = 4;
        break;
      case '5':
        prix = 5;
        break;
      case '6':
        prix = 6;
        break;
      case '7':
        prix = 7;
        break;
      case '8':
        prix = 8;
        break;
      case '9':
        prix = 9;
        break;
      case 'B':
        ajoute(Ressource.Bois);
        break;
      case 'P':
        ajoute(Ressource.Pierre);
        break;
      case 'Q':
        ajoute(Ressource.Brique);
        break;
      case 'M':
        ajoute(Ressource.Minerai);
        break;
      case 'T':
        ajoute(Ressource.Tissu);
        break;
      case 'V':
        ajoute(Ressource.Verre);
        break;
      case 'Y':
        ajoute(Ressource.Papyrus);
        break;
      }
    }
  }

  public Cout(Integer prix, Ressource... ressources) {
    this.prix = prix;
    for (Ressource ressource : ressources) {
      listeRessources.add(ressource);
    }
  }

  public Integer getPrix() {
    return prix;
  }

  public String toString() {
    String res = "[";
    if (prix != 0) {
      res = "" + prix;
    }
    for (Ressource ressource : listeRessources) {
      res += ressource.toString();
    }
    return res + ']';
  }

  public List<Ressource> getListeRessources() {
    return listeRessources;
  }

  public void retire(int i) throws Exception {
    if (i > prix) {
      throw new Exception("Pas assez de sous !");
    }
    prix -= i;
  }

  public void retire(Ressource... ressources) {
    for (Ressource ressource : ressources) {
      listeRessources.remove(ressource);
    }
  }

  public boolean contient(Ressource ressource) {
    return listeRessources.contains(ressource);
  }
  
  public void ajoute(Ressource ressource) {
    listeRessources.add(ressource);
  }
  
  public Cout clone() {
    Cout cout = new Cout(getPrix());
    for (Ressource ressource : getListeRessources()) {
      cout.ajoute(ressource);
    }
    return cout;
  }

}
