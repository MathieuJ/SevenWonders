package fr.mathieujjava.sevenwonders;

import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public interface PartieManager {
  public void effectueAction(Partie partie, Action action) throws Exception;
  
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout);
  
  public int compteNombreCartes(Partie partie, Joueur joueur, TypeCarte typeCarte, boolean gauche, boolean soi, boolean droite);
  
  public int calculePuissanceMilitaire(Partie partie, Joueur joueur);
}
