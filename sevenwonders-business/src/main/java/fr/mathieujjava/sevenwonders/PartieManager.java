package fr.mathieujjava.sevenwonders;

import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public interface PartieManager {
  public void effectueAction(Partie partie, Action action) throws Exception;
  
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout) throws Exception;
  
  public int compteNombreCartes(Partie partie, Joueur joueur, TypeCarte typeCarte, boolean gauche, boolean soi, boolean droite);
  
  public int calculePuissanceMilitaire(Partie partie, Joueur joueur);
  
  public int[] comptePoints(Partie partie);

  public void initPartie(Partie partie, Integer nombreJoueurs);
}
