package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

@Service("partieManager")
public class PartieManagerImpl implements PartieManager {
  @Resource(name = "listeMerveilles")
  List<Merveille> listeMerveilles;

  public static void effectueCarte(Partie partie, Joueur joueur, Carte carte) {
    if ("Tavern".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(5);
    } else if ("Vineyard".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur, TypeCarte.MatierePremiere, true, true, true));
    } else if ("Bazar".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur, TypeCarte.Manufacture, true, true, true));
    } else if ("Chamber of Commerce".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(2 * partie.compteNombreCartes(joueur, TypeCarte.Manufacture, false, true, false));
    } else if ("Lighthouse".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur, TypeCarte.Commerce, false, true, false));
    } else if ("Haven".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur, TypeCarte.MatierePremiere, false, true, false));
    }
  }

  @Override
  public void effectueAction(Partie partie, Action action) throws Exception {
    Joueur joueur = action.getJoueur();
    Carte carte = action.getCarte();

    switch (action.getTypeAction()) {
    case Defausse:
      if (joueur.getMain().contains(carte)) {
        joueur.getMain().remove(carte);
        partie.getDefausse().add(carte);
        joueur.modifieNombrePieces(3);
      } else {
        throw new Exception("Le joueur ne possede pas la carte " + carte);
      }
      break;

    case Joue:
      paieCout(partie, joueur, action.choixAchatRessources);
      joueur.getMain().remove(action.getCarte());
      joueur.getListeCartes().add(action.getCarte());
      if (action.getCarte().getType() == TypeCarte.Commerce) {
        System.out.println("Ka-CHING");
        effectueCarte(partie, joueur, action.getCarte());
      }
      break;
    case Merveille:
      joueur.getMain().remove(action.getCarte());
      if (joueur.getEtageMerveille() == 3) throw new Exception("Le joueur a déjà tout construit");
      paieCout(partie, joueur, action.choixAchatRessources);
      joueur.setEtageMerveille(joueur.getEtageMerveille() + 1);
      break;
    case Special:
      break;
    }
  }

  @Override
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout) throws Exception {
    if (joueur.getNombrePieces() < cout.piecesPourBanque + cout.piecesPourDroite + cout.piecesPourGauche) {
      throw new Exception("Le joueur n'a pas assez de sous");
    }
    joueur.modifieNombrePieces(-cout.piecesPourBanque - cout.piecesPourDroite - cout.piecesPourGauche);
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
      case Defaite:
        puissance -= 1;
        break;
      case VictoireI:
        puissance += 1;
        break;
      case VictoireII:
        puissance += 3;
        break;
      case VictoireIII:
        puissance += 5;
        break;
      }
    }
    if ("Rhodes Colossus".equals(joueur.getMerveille().getNomEn()) && joueur.getEtageMerveille() >= 2) {
      puissance += 2;
    }
    return puissance;
  }

  public int[] comptePoints(Partie partie) {
    int[] resultat = new int[partie.getNbJoueurs() * 8];
    for (Joueur joueur : partie.getListeJoueurs()) {
      int points;
      int total = 0;
      // Decompte des victoires militaires
      points = 0;
      for (Medaille medaille : joueur.getListeMedailles()) {
        points += medaille.getValeur();
      }
      resultat[joueur.getPlace() * 8] = points;
      total += points;

      // Contenu du tresor
      resultat[joueur.getPlace() * 8 + 1] = joueur.getNombrePieces() / 3;
      total += joueur.getNombrePieces() / 3;

      // Merveille
      points = 0;
      if (joueur.getEtageMerveille() >= 1) {
        points += 3;
      }
      if (joueur.getEtageMerveille() >= 2) {
        // GIZAH
        if ("The Pyramids of Giza".equals(joueur.getMerveille().getNomEn())) {
          points += 5;
        }
      }
      if (joueur.getEtageMerveille() == 3) {
        points += 7;
      }
      resultat[joueur.getPlace() * 8 + 2] = points;
      total += points;

      // Batiments civils
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Civil)) {

        points += new Integer(carte.getTexte().substring(0, 1));
      }
      resultat[joueur.getPlace() * 8 + 3] = points;
      total += points;

      // Decompte des victoires science
      points = 0;
      int nbInge = 0, nbMath = 0, nbEcriture = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Science)) {
        if (carte.getTexte().startsWith("M")) {
          nbMath++;
        } else if (carte.getTexte().startsWith("I")) {
          nbInge++;
        } else if (carte.getTexte().startsWith("E")) {
          nbEcriture++;
        }
      }

      // Si guilde, on rajoute la science la plus basse.
      if (joueur.possede("Scientists Guilde")) {
        if (nbMath > nbInge) {
          if (nbInge > nbEcriture) {
            nbEcriture++;
          } else
            nbInge++;
        } else {
          if (nbMath > nbEcriture) {
            nbEcriture++;
          } else
            nbMath++;
        }
      }

      // Si 2eme étage Babylone, on rajoute la science la plus basse.
      if (joueur.getEtageMerveille() >= 2 && "The Hanging Gardens of Babylon".equals(joueur.getMerveille())) {
        if (nbMath > nbInge) {
          if (nbInge > nbEcriture) {
            nbEcriture++;
          } else
            nbInge++;
        } else {
          if (nbMath > nbEcriture) {
            nbEcriture++;
          } else
            nbMath++;
        }
      }

      int nbLigne = nbInge;
      if (nbMath < nbLigne)
        nbLigne = nbMath;
      if (nbEcriture < nbLigne)
        nbLigne = nbEcriture;

      points += nbInge * nbInge;
      points += nbMath * nbMath;
      points += nbEcriture * nbEcriture;

      points += 7 * nbLigne;

      resultat[joueur.getPlace() * 8 + 4] = points;
      total += points;

      // Batiments commerciaux
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Commerce)) {

        if ("Chamber of Commerce".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Manufacture, false, true, false);
        } else if ("Lighthouse".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Commerce, false, true, false);
        } else if ("Haven".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.MatierePremiere, false, true, false);
        } else if ("Arena".equals(carte.getNomEn())) {
          points += 1 * joueur.getEtageMerveille();
        }

      }
      // System.out.println("commerce " + points);
      resultat[joueur.getPlace() * 8 + 5] = points;
      total += points;

      // Guildes :
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Guilde)) {
        if ("Workers Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.MatierePremiere, true, false, true);
        }
        if ("Craftsmens Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Manufacture, true, false, true);
        }
        if ("Traders Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Commerce, true, false, true);
        }
        if ("Philosophers Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Science, true, false, true);
        }
        if ("Spies Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Militaire, true, false, true);
        }
        if ("Stategists Guilde".equals(carte.getNomEn())) {
          // TODO 1 par -1 autour
          // points += partie.compteNombreCartes(joueur,
          // TypeCarte.Manufacture, true, false, true);

        }
        if ("Shiowners Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Manufacture, false, true, false);
          points += partie.compteNombreCartes(joueur, TypeCarte.MatierePremiere, false, true, false);
          points += partie.compteNombreCartes(joueur, TypeCarte.Guilde, false, true, false);
        }

        if ("Magistrates Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Civil, true, false, true);
        }
        if ("Builders Guilde".equals(carte.getNomEn())) {
          // 1 par etage gauche soi droite
          points += partie.getVoisinDroite(joueur).getEtageMerveille() + joueur.getEtageMerveille() + partie.getVoisinGauche(joueur).getEtageMerveille();
        }
      }
      resultat[joueur.getPlace() * 8 + 6] = points;
      total += points;

      // Total
      resultat[joueur.getPlace() * 8 + 7] = total;
      // System.out.println("total " + total);

    }
    return resultat;
  }

  @Override
  public void initPartie(Partie partie, Integer nombreJoueurs) {
    List<Merveille> liste = new ArrayList<Merveille>();
    for (Merveille merveille : listeMerveilles) {
      liste.add(merveille);
    }

    // On distribue les merveilles
    for (int i = 0; i < nombreJoueurs; i++) {
      Joueur joueur = new Joueur(liste.remove((int) (Math.random() * liste.size())));
      partie.addJoueur(joueur);
    }
  }
}
