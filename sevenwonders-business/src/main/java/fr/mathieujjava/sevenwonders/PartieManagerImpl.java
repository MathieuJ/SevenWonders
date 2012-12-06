package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

@Service("partieManager")
public class PartieManagerImpl implements PartieManager {
  @Resource(name = "listeMerveilles")
  List<Merveille> listeMerveilles;

  @Resource(name = "listeCartesAgeI")
  List<Carte> listeCartesAgeI;

  @Resource(name = "listeCartesAgeII")
  List<Carte> listeCartesAgeII;

  @Resource(name = "listeCartesAgeIII")
  List<Carte> listeCartesAgeIII;

  @Resource(name = "listeCartesGuilde")
  List<Carte> listeCartesGuilde;

  @Autowired
  BotService botService;

  public static void effectueCarte(Partie partie, Joueur joueur, Carte carte) {
    if ("Tavern".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(5);
    } else if ("Vineyard".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur,
          TypeCarte.MatierePremiere, true, true, true));
    } else if ("Bazar".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur,
          TypeCarte.Manufacture, true, true, true));
    } else if ("Chamber of Commerce".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(2 * partie.compteNombreCartes(joueur,
          TypeCarte.Manufacture, false, true, false));
    } else if ("Lighthouse".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur,
          TypeCarte.Commerce, false, true, false));
    } else if ("Haven".equals(carte.getNomEn())) {
      joueur.modifieNombrePieces(partie.compteNombreCartes(joueur,
          TypeCarte.MatierePremiere, false, true, false));
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
      paieCout(partie, joueur, action.getChoixAchatRessources());
      joueur.getMain().remove(action.getCarte());
      joueur.getListeCartes().add(action.getCarte());
      if (action.getCarte().getType() == TypeCarte.Commerce) {
        System.out.println("Ka-CHING");
        effectueCarte(partie, joueur, action.getCarte());
      }
      break;
    case Merveille:
      joueur.getMain().remove(action.getCarte());
      if (joueur.getEtageMerveille() == 3)
        throw new Exception("Le joueur a déjà tout construit");
      paieCout(partie, joueur, action.getChoixAchatRessources());
      joueur.setEtageMerveille(joueur.getEtageMerveille() + 1);
      break;
    case Special:
      break;
    }
  }

  @Override
  public void paieCout(Partie partie, Joueur joueur, ChoixAchatRessources cout)
      throws Exception {
    if (joueur.getNombrePieces() < cout.getTotalPieces()) {
      throw new Exception("Le joueur n'a pas assez de sous");
    }
    joueur.modifieNombrePieces(-cout.getPiecesPourBanque()
        - cout.getPiecesPourDroite() - cout.getPiecesPourGauche());
    partie.getVoisinDroite(joueur).modifieNombrePieces(
        cout.getPiecesPourDroite());
    partie.getVoisinGauche(joueur).modifieNombrePieces(
        cout.getPiecesPourGauche());
  }

  public int compteNombreCartes(Partie partie, Joueur joueur,
      TypeCarte typeCarte, boolean gauche, boolean soi, boolean droite) {
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
    if ("Rhodes Colossus".equals(joueur.getMerveille().getNomEn())
        && joueur.getEtageMerveille() >= 2) {
      puissance += 2;
    }
    return puissance;
  }

  public HashMap<Joueur, List<Integer>> comptePoints(Partie partie) {
    HashMap<Joueur, List<Integer>> resultatX = new HashMap<Joueur, List<Integer>>();
    //int[] resultat = new int[partie.getNbJoueurs() * 8];
    for (Joueur joueur : partie.getListeJoueurs()) {
      List<Integer> listePoints = new ArrayList<Integer>();
      int points;
      int total = 0;
      // Decompte des victoires militaires
      points = 0;
      for (Medaille medaille : joueur.getListeMedailles()) {
        points += medaille.getValeur();
      }
      listePoints.add(points);
      total += points;

      // Contenu du tresor
      listePoints.add(joueur.getNombrePieces() / 3);
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
      listePoints.add(points);
      total += points;

      // Batiments civils
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Civil)) {

        points += new Integer(carte.getTexte().substring(0, 1));
      }
      listePoints.add(points);
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
      if (joueur.getEtageMerveille() >= 2
          && "The Hanging Gardens of Babylon".equals(joueur.getMerveille())) {
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

      listePoints.add(points);
//resultat[joueur.getPlace() * 8 + 4] = points;
      total += points;

      // Batiments commerciaux
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Commerce)) {

        if ("Chamber of Commerce".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur,
              TypeCarte.Manufacture, false, true, false);
        } else if ("Lighthouse".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Commerce,
              false, true, false);
        } else if ("Haven".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur,
              TypeCarte.MatierePremiere, false, true, false);
        } else if ("Arena".equals(carte.getNomEn())) {
          points += 1 * joueur.getEtageMerveille();
        }

      }
      // System.out.println("commerce " + points);
      listePoints.add(points);
//    resultat[joueur.getPlace() * 8 + 5] = points;
      total += points;

      // Guildes :
      points = 0;
      for (Carte carte : joueur.getListeCarte(TypeCarte.Guilde)) {
        if ("Workers Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur,
              TypeCarte.MatierePremiere, true, false, true);
        }
        if ("Craftsmens Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur,
              TypeCarte.Manufacture, true, false, true);
        }
        if ("Traders Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Commerce, true,
              false, true);
        }
        if ("Philosophers Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Science, true,
              false, true);
        }
        if ("Spies Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Militaire,
              true, false, true);
        }
        if ("Stategists Guilde".equals(carte.getNomEn())) {
          // TODO 1 par -1 autour
          // points += partie.compteNombreCartes(joueur,
          // TypeCarte.Manufacture, true, false, true);

        }
        if ("Shiowners Guilde".equals(carte.getNomEn())) {
          points += partie.compteNombreCartes(joueur, TypeCarte.Manufacture,
              false, true, false);
          points += partie.compteNombreCartes(joueur,
              TypeCarte.MatierePremiere, false, true, false);
          points += partie.compteNombreCartes(joueur, TypeCarte.Guilde, false,
              true, false);
        }

        if ("Magistrates Guilde".equals(carte.getNomEn())) {
          points += 2 * partie.compteNombreCartes(joueur, TypeCarte.Civil,
              true, false, true);
        }
        if ("Builders Guilde".equals(carte.getNomEn())) {
          // 1 par etage gauche soi droite
          points += partie.getVoisinDroite(joueur).getEtageMerveille()
              + joueur.getEtageMerveille()
              + partie.getVoisinGauche(joueur).getEtageMerveille();
        }
      }
      listePoints.add(points);
//    resultat[joueur.getPlace() * 8 + 6] = points;
      total += points;

      // Total
      listePoints.add(total);
//    resultat[joueur.getPlace() * 8 + 7] = total;
      // System.out.println("total " + total);
      resultatX.put(joueur, listePoints);
    }
    return resultatX;
  }

  protected List<Carte> getCartes(Integer age, Integer nombreJoueurs) {
    List<Carte> tasCartes = new ArrayList<Carte>();
    List<Carte> cartes = null;
    switch (age) {
    case 1:
      cartes = listeCartesAgeI;
      break;
    case 2:
      cartes = listeCartesAgeII;
      break;
    case 3:
      cartes = listeCartesAgeIII;
      break;
    }
    for (Carte carte : cartes) {
      for (int i = 1; i < nombreJoueurs + 1; i++) {
        if (carte.getNombreJoueurRequis().contains("" + i)) {
          tasCartes.add(carte);
        }
      }
    }
    if (age == 3) {
      // on recopie la liste des cartes de guilde car on ne peut se permettre de la modifier.
      List<Carte> listGuildes = new ArrayList<Carte>();
      for (Carte carte : listeCartesGuilde) {
        listGuildes.add(carte);
      }
      for (int i = 0; i < nombreJoueurs + 2; i++) {
        tasCartes.add(listGuildes.remove((int) (Math.random() * i)));
      }
    }
    return tasCartes;
  }

  protected List<Carte> melangeListe(List<Carte> liste) {
    for (int i = 0; i < 500; i++) {
      Carte carte = liste.remove((int) (Math.random() * liste.size()));
      liste.add(carte);
    }
    return liste;
  }

  public void distribueCartes(Partie partie) {
    List<Carte> tasCartes = getCartes(partie.getAge(), partie.getNbJoueurs());
    tasCartes = melangeListe(tasCartes);
    for (int j = 0; j < tasCartes.size(); j++) {
      partie.getJoueur(j % partie.getNbJoueurs()).getMain()
          .add(tasCartes.get(j));
    }

  }

  @Override
  public void initPartie(Partie partie, Integer nombreJoueurs) {
    List<Merveille> liste = new ArrayList<Merveille>();
    for (Merveille merveille : listeMerveilles) {
      liste.add(merveille);
    }

    // On distribue les merveilles
    for (int i = 0; i < nombreJoueurs; i++) {
      Joueur joueur = new Joueur(liste.remove((int) (Math.random() * liste
          .size())), true);
      partie.addJoueur(joueur);
    }
    partie.getJoueur(1).setBot(false);

    distribueCartes(partie);
  }

  public void effectueActionJoueurEtBots(Partie partie, Action actionJoueur) {
    List<Action> toutesActions = new ArrayList<Action>();
    toutesActions.add(actionJoueur);
    toutesActions.addAll(getActionsBots(partie));
    Joueur joueurMausolee = null;
    for (Action action : toutesActions) {
      Joueur joueur = action.getJoueur();
      switch (action.getTypeAction()) {
      case Defausse:
        System.out.println(joueur.getPlace() + " a " + joueur.getMain()
            + " ; il défausse " + action.getCarte());
        joueur.getMain().remove(action.getCarte());
        joueur.modifieNombrePieces(3);
        partie.getDefausse().add(action.getCarte());
        break;
      case Joue:
        System.out.print(joueur.getPlace() + " a " + joueur.getMain()
            + " ; il joue " + action.getCarte() + " pour ");
        System.out.println(action.getChoixAchatRessources().getTotalPieces());
        if (action.getChoixAchatRessources().getPiecesPourDroite() != 0) {
          System.out.println("Il file "
              + action.getChoixAchatRessources().getPiecesPourDroite()
              + " à droite");
          partie.getVoisinDroite(joueur).modifieNombrePieces(
              action.getChoixAchatRessources().getPiecesPourDroite());
        }
        if (action.getChoixAchatRessources().getPiecesPourGauche() != 0) {
          System.out.println("Il file "
              + action.getChoixAchatRessources().getPiecesPourGauche()
              + " à gauche");
          partie.getVoisinGauche(joueur).modifieNombrePieces(
              action.getChoixAchatRessources().getPiecesPourGauche());
        }
        joueur.modifieNombrePieces(-action.getChoixAchatRessources()
            .getTotalPieces());
        joueur.getMain().remove(action.getCarte());
        joueur.getListeCartes().add(action.getCarte());
        if (action.getCarte().getType() == TypeCarte.Commerce) {
          System.out.println("Ka-CHING");
          effectueCarte(partie, joueur, action.getCarte());
        }
        break;
      case Merveille:
        System.out.println(joueur.getPlace() + " a " + joueur.getMain()
            + " ; il construit un étage avec [" + action.getCarte().getNomFr()
            + "]");
        joueur.getMain().remove(action.getCarte());
        joueur.setEtageMerveille(joueur.getEtageMerveille() + 1);
        if (joueur.getMerveille().getId() == 3L
            && joueur.getEtageMerveille() == 2) {
          joueur.modifieNombrePieces(9);
        }
        if (joueur.getMerveille().getId() == 6L
            && joueur.getEtageMerveille() == 2) {
          joueurMausolee = joueur;
        }
        break;
      }
    }
    if (joueurMausolee != null) {
      Carte carteChoix = null;
      int ponderation = -10;
      for (Carte carte : partie.getDefausse()) {
        if (!joueurMausolee.possede(carte.getNomEn())) {
          int nouvellePonderation = botService.getPonderationCarte(partie,
              joueurMausolee, carte);
          // System.out.println("Ponderation : " + nouvellePonderation
          // + " " + carte);
          if (carteChoix == null || nouvellePonderation > ponderation) {
            carteChoix = carte;
            ponderation = nouvellePonderation;
          }
        }
      }
      if (carteChoix != null) {
        System.out.println("SPECIAL : le joueur Mausolee decide de jouer "
            + carteChoix);
        joueurMausolee.getListeCartes().add(carteChoix);
        partie.getDefausse().remove(carteChoix);
      }
    }
  }

  List<Action> getActionsBots(Partie partie) {
    List<Action> actions = new ArrayList<Action>();
    for (int i = 0; i < partie.getListeJoueurs().size(); i++) {
      if (partie.getJoueur(i).isBot()) {
        actions.add(botService.getBotAction(partie, partie.getJoueur(i)));
      }
    }
    return actions;
  }

  @Override
  public void finitTour(Partie partie) {
    partie.incrementeTour();
    if (partie.getTour() == 7) {
      donneMedaillesMilitaires(partie);
      partie.setAge(partie.getAge() + 1);
      partie.setTour(1);
      for (Joueur joueur : partie.getListeJoueurs()) {
        partie.getDefausse().addAll(joueur.getMain());
        joueur.getMain().clear();
      }
      if (partie.getAge() < 4) {
        distribueCartes(partie);
      }
    }
    if (partie.getAge() == 2) {
      partie.rotationCartes(false);
    } else {
      partie.rotationCartes(true);
    }
  }

  private void donneMedaillesMilitaires(Partie partie) {
    int[] tabPoints = new int[partie.getNbJoueurs()];
    for (int i = 0; i < partie.getNbJoueurs(); i++) {
      Joueur joueur = partie.getJoueur(i);
      tabPoints[i] += calculePuissanceMilitaire(partie, joueur);
      if (joueur.getMerveille().getId() == 1L
          && (joueur.getEtageMerveille() >= 2)) {
        tabPoints[i] += 2;
      }
      System.out.println("Puissance militaire de "
          + partie.getJoueur(i).getNombrePieces() + " : " + tabPoints[i]);
    }
    Medaille medailleVictoire = Medaille.VictoireI;
    if (partie.getAge() == 2)
      medailleVictoire = Medaille.VictoireII;
    else if (partie.getAge() == 3)
      medailleVictoire = Medaille.VictoireIII;

    for (int i = 0; i < partie.getNbJoueurs(); i++) {
      if (tabPoints[i] < tabPoints[(i + 1) % partie.getNbJoueurs()]) {
        partie.getJoueur(i).getListeMedailles().add(Medaille.Defaite);
        partie.getJoueur((i + 1) % partie.getNbJoueurs()).getListeMedailles()
            .add(medailleVictoire);
        System.out.println("Victoire de "
            + partie.getJoueur((i + 1) % partie.getNbJoueurs()).getPlace()
            + " sur " + partie.getJoueur(i).getPlace());
      } else if (tabPoints[i] > tabPoints[(i + 1) % partie.getNbJoueurs()]) {
        partie.getJoueur(i).getListeMedailles().add(medailleVictoire);
        partie.getJoueur((i + 1) % partie.getNbJoueurs()).getListeMedailles()
            .add(Medaille.Defaite);
        System.out.println("Victoire de " + partie.getJoueur(i).getPlace()
            + " sur "
            + partie.getJoueur((i + 1) % partie.getNbJoueurs()).getPlace());
      }
    }

  }
}
