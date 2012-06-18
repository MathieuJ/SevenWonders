package fr.mathieujjava.sevenwonders;

import org.springframework.stereotype.Service;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

@Service("partieManager")
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
    joueur.modifieNombrePieces(- cout.piecesPourBanque - cout.piecesPourDroite - cout.piecesPourGauche);
    partie.getVoisinDroite(joueur).modifieNombrePieces(cout.piecesPourDroite);
    partie.getVoisinGauche(joueur).modifieNombrePieces(cout.piecesPourGauche);
  }

  public int compteNombreCartes(Partie partie, Joueur joueur, TypeCarte typeCarte, boolean gauche, boolean soi, boolean droite) {
    int i = 0;
    if (gauche) {
      i += partie.getVoisinGauche(joueur).compteNombreCartes(typeCarte);
    }
    if (soi) {
      i += joueur.compteNombreCartes(typeCarte);
    }
    if (droite) {
      i += partie.getVoisinDroite(joueur).compteNombreCartes(typeCarte);
    }
    return i;
  }

  @Override
  public int calculePuissanceMilitaire(Partie partie, Joueur joueur) {
    int puissance = 0;
    for (Carte carte : joueur.getListeCartes()) {
      if (carte.getType() == TypeCarte.Militaire) {
        puissance += carte.getTexte().length();
      }
    }
    for (Medaille medaille : joueur.getListeMedailles()) {
      switch (medaille) {
      case Defaite : puissance -= 1; break;
      case VictoireI : puissance += 1; break;
      case VictoireII : puissance += 3; break;
      case VictoireIII : puissance += 5; break;
      }
    }
    if ("Rhodes Colossus".equals(joueur.getMerveille().getNomEn()) && joueur.getEtageMerveille() >= 2) {
      puissance += 2;
    }
    return puissance;
  }
}
