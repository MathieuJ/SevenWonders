package fr.mathieujjava.sevenwonders;

public class PartieManagerImpl implements PartieManager {

  @Override
  public void effectueAction(Partie partie, Action action) throws Exception {
    Joueur joueur = action.getJoueur();
    Carte carte = action.getCarte();

    if (joueur.getMain().contains(carte)) {
      joueur.getMain().remove(carte);
      partie.getDefausse().add(carte);
      joueur.modifieNombrePieces(3);
    } else {
      throw new Exception("Le joueur ne possede pas la carte " + carte);
    }
  }

  @Override
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout) {
    
  }

}
