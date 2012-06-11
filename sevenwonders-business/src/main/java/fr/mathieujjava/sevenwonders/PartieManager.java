package fr.mathieujjava.sevenwonders;

public interface PartieManager {
  public void effectueAction(Partie partie, Action action) throws Exception;
  
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout);
}
