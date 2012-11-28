package fr.mathieujjava.sevenwonders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.mathieujjava.sevenwonders.enums.Place;
import fr.mathieujjava.sevenwonders.enums.Ressource;
import fr.mathieujjava.sevenwonders.enums.TypeAction;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

@Service
public class BotServiceImpl implements BotService {
  @Autowired
  private PartieManager partieManager;

  @Override
  public Action getBotAction(Partie partie, Joueur joueur) {
    try {
      return choisitAction(partie, joueur);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  // pondère une liste de cartes
  public int getPonderationCarte(Partie partie, Joueur joueur,
      Carte carte) {
    int ponderation = 0;
    /*
     * if (hasCarteParent(joueur, carte)) {
     * System.out.println("_________IA carte gratos : " + carte); ponderation +=
     * 3; }
     */
    if (carte.getType() == TypeCarte.Civil) {
      ponderation += (int) (Math.random() * 2);
    } else if (carte.getType() == TypeCarte.Science) {
      ponderation += 2 + partie.compteNombreCartes(joueur, TypeCarte.Science,
          false, true, false) * 2;
      if (ponderation > 0)
        System.out.println("_________IA carte science : " + carte);
    } else if (carte.getType() == TypeCarte.MatierePremiere
        || carte.getType() == TypeCarte.Manufacture) {
      ponderation += 2;
      if ((partie.compteNombreCartes(joueur, TypeCarte.Manufacture, false,
          true, false)
          + partie.compteNombreCartes(joueur, TypeCarte.Manufacture, false,
              true, false) < 3)) {
        ponderation += 2;
      }
      switch (joueur.getMerveille().getId()) {
      // Colosse RHODES
      case 1:
        switch (partie.getAge()) {
        case 1:
          if (isCarteDans(carte, new String[] { "Mine", "Stone Pit",
              "Ore Vein", "Loom", "East Trading Post", "West Trading Post",
              "Marketplace", "Tavern", "Workshop", "Barracks" })) {
            ponderation += 5;
          }
          // Tout sauf minerai
          // Militaire, mais pas plus de 2
          // 1er étage de merveille
          break;
        case 2:
          // 2eme étage de merveille au moins
          if (isCarteDans(carte, new String[] { "Foundry", "Forum",
              "Caravansary", "Stables", "Courthouse", "Training Ground" })) {
            ponderation += 5;
          }
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Town Hall", "Senate",
              "Craftsmens Guild", "Arena", "Haven", "Philosophers Guild",
              "Shipowners Guild", "Strategists Guild", "Arsenal" })) {
            ponderation += 5;
          }
          break;
        }
        break;

      // LIBRAIRIE ALEXANDRIE
      case 2:
        switch (partie.getAge()) {
        case 1:
          if (isCarteDans(carte, new String[] { "Mine", "Stone Pit",
              "Ore Vein", "Loom", "East Trading Post", "West Trading Post",
              "Marketplace", "Tavern", "Workshop", "Barracks" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Foundry", "Quarry",
              "Caravansary", "Forum", "Library", "Dispensary", "Laboratory",
              "Training Ground", "Walls" })) {
            ponderation += 5;
          }
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Town Hall", "Senate",
              "Craftsmens Guild", "Arena", "Lighthouse", "Circus",
              "Fortifications" })) {
            ponderation += 5;
          }
          break;
        }
        break;
      // TEMPLE ARTEMISE
      case 3:
        switch (partie.getAge()) {
        case 1:
          if (isCarteDans(carte, new String[] { "Mine", "Stone Pit",
              "Ore Vein", "Loom", "East Trading Post", "West Trading Post",
              "Marketplace", "Tavern", "Workshop", "Barracks" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Foundry", "Quarry",
              "Caravansary", "Forum", "Library", "Dispensary", "Laboratory",
              "Training Ground", "Walls" })) {
            ponderation += 5;
          }
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Town Hall", "Senate",
              "Craftsmens Guild", "Arena", "Lighthouse", "Circus",
              "Fortifications" })) {
            ponderation += 5;
          }
          break;
        }
        break;
      // JARDINS DE BABYLONE
      case 4:
        switch (partie.getAge()) {
        case 1:
          if (isCarteDans(carte, new String[] { "Tree Farm", "Press", "Loom",
              "Glassworks", "Lumber Yard", "Clay Pool" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Brickyard", "Sawmill",
              "Forum", "Caravansery", "Quarry", "Laboratory" })) {
            ponderation += 5;
          }
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Philosophers Guild",
              "Traders Guild", "Spies Guild", "Shipowners Guild",
              "Chamber of Commerce", "Siege Workshop", "Lodge" })) {
            ponderation += 5;
          }
          break;
        }
        break;
      // Olympia Zeus
      case 5:
        switch (partie.getAge()) {
        case 1:
          if (isCarteDans(carte, new String[] { "East Trading Post",
              "West Trading Post", "Marketplace", "Loom", "Timber Yard",
              "Tavern", "Glassworks", "Stockade" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Caravansary", "Vineyard",
              "Forum", "Bazaar", "Loom", "Glassworks", "Archery Range",
              "Training Ground" })) {
            ponderation += 5;
          }
          // Manufacture qu'on a pas
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Craftmens Guild", "Arena",
              "Arsenal", "Circus", "Lighthouse", "Magistrates Guild",
              "Strategists Guild" })) {
            ponderation += 5;
          }
          break;
        }
        break;
      // Mausolee
      case 6:
        switch (partie.getAge()) {
        case 1:
          // Raw, manufacture, militaire gratos
          if (isCarteDans(carte, new String[] { "Clay Pit ", "Stone Pit",
              "Ore Vein", "Loom", "Apothecary", "Barracks" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Caravansary", "School",
              "Forum", "Foundry", "Courtyard", "Statue", "Dispensary",
              "Stables", "Brickyard", "Training Ground" })) {
            ponderation += 5;
          }
          // Manufacture qu'on a pas
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Haven", "Arena", "Palace",
              "Siege Workshop", "Observatory", "Traders Guild",
              "Strategists Guild", "Chamber of Commerce", "Pantheon" })) {
            ponderation += 5;
          }
          break;
        }
        break;
      // Pyramides :
      case 7:
        switch (partie.getAge()) {
        case 1:
          // Raw Materials – prioritize wood and stone,
          // leave with one ore
          // Manufactured Resources
          // Baths
          if (isCarteDans(carte, new String[] { "Baths", "Loom", "Glassworks",
              "Press" })) {
            ponderation += 5;
          }
          break;
        case 2:
          if (isCarteDans(carte, new String[] { "Foundry", "Quarry",
              "Caravansary", "Forum", "Library", "Dispensary", "Laboratory",
              "Training Ground", "Walls" })) {
            ponderation += 5;
          }
          break;
        case 3:
          if (isCarteDans(carte, new String[] { "Town Hall", "Senate",
              "Craftsmens Guild", "Arena", "Lighthouse", "Circus",
              "Fortifications" })) {
            ponderation += 5;
          }
          break;
        }
        break;

      }
    } else if (carte.getType() == TypeCarte.Militaire) {
      int puissanceCarte = carte.getTexte().length();
      int puissanceSoi = partieManager.calculePuissanceMilitaire(partie, joueur);
      int puissanceGauche = partieManager.calculePuissanceMilitaire(partie, partie.getVoisinGauche(joueur));
      int puissanceDroite = partieManager.calculePuissanceMilitaire(partie, partie.getVoisinDroite(joueur));
      System.out.print(puissanceCarte + " " + puissanceSoi + " "
          + puissanceGauche + " " + puissanceDroite);
      if ((puissanceSoi > puissanceDroite) && (puissanceSoi > puissanceGauche)) {
        System.out.println("____________ pas besoin d'armée chuis trop fort");
        ponderation += 1;
      } else if ((puissanceSoi + puissanceCarte < puissanceDroite)
          && (puissanceSoi + puissanceCarte < puissanceGauche)) {
        System.out
            .println("____________ pas besoin de me booster ils sont trop forts");
        ponderation += 1;
      } else if ((puissanceSoi < puissanceDroite && puissanceSoi
          + puissanceCarte > puissanceDroite)
          || (puissanceSoi < puissanceGauche && puissanceSoi + puissanceCarte > puissanceGauche)) {
        System.out.println("____________ ça peut me bosster et les dépasser");
        ponderation += 4;
      } else {
        System.out.println("____________ bof");
        ponderation += 2;
      }
    }

    // ponderation += carte.getCout().getListeRessources().size();
    // System.out.println("Carte " + carte.getNomFr() + " ponderation : " +
    // ponderation);
    return ponderation;
  }

  private Action choisitAction(Partie partie, Joueur joueur) throws Exception {
    // List<Carte> listeCartesJouables = new ArrayList<Carte>();
    HashMap<Carte, ChoixAchatRessources> hashChoixCarte = new HashMap<Carte, ChoixAchatRessources>();
    System.out.println("Joueur : " + joueur.getPlace());
    for (Carte carte : joueur.getMain()) {
      if (!joueur.possede(carte.getNomEn())) {
        ChoixAchatRessources car = getCoutTotal(partie, joueur, carte);
        if (car != null && car.getTotalPieces() <= joueur.getNombrePieces()) {
          hashChoixCarte.put(carte, car);
        }
      }
    }

    int ponderation = -10;
    Carte carteChoix = null;
    for (Carte carte : hashChoixCarte.keySet()) {
      int nouvellePonderation = getPonderationCarte(partie, joueur, carte);
      System.out.println("Ponderation : " + nouvellePonderation + " " + carte);
      if (carteChoix == null || nouvellePonderation > ponderation) {
        carteChoix = carte;
        ponderation = nouvellePonderation;
        // System.out.println(carteChoix);
      }
    }
    ChoixAchatRessources car = getCoutConstructionEtage(partie, joueur);
    if (car != null && joueur.getEtageMerveille() < 3
        && car.getTotalPieces() <= joueur.getNombrePieces() && ponderation < 5) {
      return new Action(joueur, TypeAction.Merveille, joueur.getMain().get(0), car);
    }
    if (carteChoix != null) {
      return new Action(joueur, TypeAction.Joue, carteChoix,
          hashChoixCarte.get(carteChoix));
    }

    return new Action(joueur, TypeAction.Defausse, joueur.getMain().get(0));
  }

  private static boolean isCarteDans(Carte carte, String[] listeNoms) {
    for (String nom : listeNoms) {
      if (nom.equals(carte.getNomEn())) {
        return true;
      }
    }
    return false;
  }

  private ChoixAchatRessources getCoutConstructionEtage(Partie partie,
      Joueur joueur) throws Exception {
    ChoixAchatRessources choix;
    switch (joueur.getEtageMerveille()) {

    case 0:
      choix = fonctionDepart(partie, joueur, joueur.getMerveille()
          .getCoutAge(1));
      break;
    case 1:
      choix = fonctionDepart(partie, joueur, joueur.getMerveille()
          .getCoutAge(2));
      break;
    case 2:
      choix = fonctionDepart(partie, joueur, joueur.getMerveille()
          .getCoutAge(3));
      break;
    default:
      return null;
    }
    return choix;

  }

  private static int peutPayer(Partie partie, Joueur joueur, Cout coutAPayer) {
    int cout = 0;
    Cout coutATester = coutAPayer.clone();
    cout += coutAPayer.getPrix();
    coutATester.retire(joueur.getMerveille().getRessource());
    retireRessourcesPayables(partie, joueur, coutATester);
    if (coutATester.getListeRessources().isEmpty()) {
      return cout;
    }

    return -1;
  }

  private ChoixAchatRessources fonctionDepart(Partie partie,
      Joueur joueur, Cout coutATester) {
    List<RecRessourceCarte> listeRecRessCarte = new ArrayList<RecRessourceCarte>();
    if (joueur.getMerveille().getId() == 2L && joueur.getEtageMerveille() >= 2) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, new Carte(
          TypeCarte.MatierePremiere, "Caravansery", "Phare Alexandrier", "0",
          null, null, "Phare d'alexandrie")));
    }
    switch (joueur.getMerveille().getId().intValue()) {
    case 1:
      listeRecRessCarte
          .add(new RecRessourceCarte(Place.Soi, new Carte(TypeCarte.MatierePremiere,
              "Ore Vein", "Filon", "0", null, null, "")));
      break;
    case 2:
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, 
          new Carte( TypeCarte.MatierePremiere, "Glassworks", "Filon", "0",
              null, null, "")));
      break;
    case 3:
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, new Carte(
          TypeCarte.MatierePremiere, "Press", "Filon", "0", null, null, "")));
      break;
    case 4:
      listeRecRessCarte
          .add(new RecRessourceCarte(Place.Soi, new Carte(TypeCarte.MatierePremiere,
              "Clay Pool", "Filon", "0", null, null, "")));
      break;
    case 5:
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, new Carte(
          TypeCarte.MatierePremiere, "Lumber Yard", "Filon", "0", null, null,
          "")));
      break;
    case 6:
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, new Carte(
          TypeCarte.MatierePremiere, "Loom", "Filon", "0", null, null, "")));
      break;
    case 7:
      listeRecRessCarte
          .add(new RecRessourceCarte(Place.Soi, new Carte(TypeCarte.MatierePremiere,
              "Stone Pit", "Filon", "0", null, null, "")));
      break;
    }

    for (Carte carte : joueur.getListeCarte(TypeCarte.Manufacture)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, carte));
    }
    for (Carte carte : joueur.getListeCarte(TypeCarte.MatierePremiere)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Soi, carte));
    }
    for (Carte carte : partie.getVoisinGauche(joueur)
        .getListeCarte(TypeCarte.Manufacture)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Gauche, carte));
    }
    for (Carte carte : partie.getVoisinGauche(joueur)
        .getListeCarte(TypeCarte.MatierePremiere)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Gauche, carte));
    }
    for (Carte carte : partie.getVoisinDroite(joueur)
        .getListeCarte(TypeCarte.Manufacture)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Droite, carte));
    }
    for (Carte carte : partie.getVoisinDroite(joueur)
        .getListeCarte(TypeCarte.MatierePremiere)) {
      listeRecRessCarte.add(new RecRessourceCarte(Place.Droite, carte));
    }

    // System.out.println("\nRecursive 0" + " " +
    // carteATester.getCout().toString() + " " + listeRecRessCarte.size());
    List<ChoixAchatRessources> listeResultat = recursivePaiement(
        coutATester.toString(), listeRecRessCarte, 0,
        new ChoixAchatRessources());

    ChoixAchatRessources resultatFinal = null;
    for (ChoixAchatRessources resultat : listeResultat) {
      for (Ressource r : resultat.getRessourcesGauche()) {
        if (r.isMatierePremiere() && joueur.possede("West Trading Post")) {
          resultat.setPiecesPourGauche(resultat.getPiecesPourGauche() + 1);
        } else if (!r.isMatierePremiere() && joueur.possede("Marketplace")) {
          resultat.setPiecesPourGauche(resultat.getPiecesPourGauche() + 1);
        } else {
          resultat.setPiecesPourGauche(resultat.getPiecesPourGauche() + 2);
        }
      }
      for (Ressource r : resultat.getRessourcesDroite()) {
        if (r.isMatierePremiere() && joueur.possede("East Trading Post")) {
          resultat.setPiecesPourDroite(resultat.getPiecesPourDroite() + 1);
        } else if (!r.isMatierePremiere() && joueur.possede("Marketplace")) {
          resultat.setPiecesPourDroite(resultat.getPiecesPourDroite() + 1);
        } else {
          resultat.setPiecesPourDroite(resultat.getPiecesPourDroite() + 2);
        }
      }
      if (resultatFinal == null
          || resultat.getTotalPieces() < resultatFinal.getTotalPieces()) {
        resultatFinal = resultat;
      }
    }
    // System.out.println("\n\n");
    return resultatFinal;
  }

  private int getCout(Partie partie, Joueur joueur, ChoixAchatRessources resultat) {

    return 0;
  }

  private List<ChoixAchatRessources> recursivePaiement(String coutCourt,
      List<RecRessourceCarte> listeRessourcesCarteDispo, int indiceRessCarte,
      ChoixAchatRessources resultatEnCours) {
    List<ChoixAchatRessources> lRes = new ArrayList<ChoixAchatRessources>();
    if (coutCourt.isEmpty()) {
      // System.out.println("yay");
      lRes.add(resultatEnCours);
      return lRes;
    }
    if (indiceRessCarte == listeRessourcesCarteDispo.size()) {
      // System.out.println("liste parcourue");
      return new ArrayList<ChoixAchatRessources>();
    }
    RecRessourceCarte r = listeRessourcesCarteDispo.get(indiceRessCarte);
    // System.out.println("Recursive " + indiceRessCarte + " " + coutCourt +
    // " " + r.carte.getNomEn() + " " + resultatEnCours);

    // les 2 choix :
    ChoixAchatRessources res2 = resultatEnCours.clone();

    if ("Sawmill".equals(r.carte.getNomEn())) {
      // System.out.println(indiceRessCarte + "Sawmill !");
      if (coutCourt.contains("BB")) {
        lRes.addAll(recursivePaiement(
            coutCourt.replaceFirst("BB", ""),
            listeRessourcesCarteDispo,
            indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Bois).addAchat(
                r.endroit, Ressource.Bois)));
      }
      if (coutCourt.contains("B")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Bois)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Quarry".equals(r.carte.getNomEn())) {
      // System.out.println(indiceRessCarte + "quarry !");
      if (coutCourt.contains("PP")) {
        lRes.addAll(recursivePaiement(
            coutCourt.replaceFirst("PP", ""),
            listeRessourcesCarteDispo,
            indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Pierre).addAchat(
                r.endroit, Ressource.Pierre)));
      }
      if (coutCourt.contains("P")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Pierre)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Brickyard".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("QQ")) {
        // System.out.println(indiceRessCarte + "Bricky !");
        lRes.addAll(recursivePaiement(
            coutCourt.replaceFirst("QQ", ""),
            listeRessourcesCarteDispo,
            indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Brique).addAchat(
                r.endroit, Ressource.Brique)));
      }
      if (coutCourt.contains("Q")) {
        // System.out.println("Bricky");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Foundary".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("MM")) {
        // System.out.println(indiceRessCarte + "Foundry !");
        lRes.addAll(recursivePaiement(
            coutCourt.replaceFirst("MM", ""),
            listeRessourcesCarteDispo,
            indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Minerai).addAchat(
                r.endroit, Ressource.Minerai)));
      }
      if (coutCourt.contains("M")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Minerai)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Lumber Yard".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("B")) {
        // System.out.println(indiceRessCarte + "Lumber !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Bois)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Stone Pit".equals(r.carte.getNomEn())) {
      // System.out.println("stonepite !");
      if (coutCourt.contains("P")) {
        // System.out.println(indiceRessCarte + "Stonepite !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Pierre)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Clay Pool".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("Q")) {
        // System.out.println(indiceRessCarte + "Clay !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Ore Vein".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("M")) {
        // System.out.println(indiceRessCarte + "Ore !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Minerai)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Loom".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("T")) {
        // System.out.println(indiceRessCarte + "T et Loom !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("T", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Tissu)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Glassworks".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("V")) {
        // System.out.println(indiceRessCarte + "V et Glass !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("V", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Verre)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Press".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("Y")) {
        // System.out.println(indiceRessCarte + "Y et Presse !");
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Y", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Papyrus)));
      } else {
        lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
            indiceRessCarte + 1, res2));
      }
    } else if ("Tree Farm".equals(r.carte.getNomEn())) {
      // System.out.println(indiceRessCarte + "Tree farm !");
      if (coutCourt.contains("B")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Bois)));
      }
      if (coutCourt.contains("Q")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Excavation".equals(r.carte.getNomEn())) {
      // System.out.println(indiceRessCarte + "Excavation !");
      if (coutCourt.contains("P")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Pierre)));
      }
      if (coutCourt.contains("Q")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Clay Pit".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("M")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Minerai)));
      }
      if (coutCourt.contains("Q")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Timber Yard".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("B")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Bois)));
      }
      if (coutCourt.contains("P")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Pierre)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Forest Cave".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("B")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Bois)));
      }
      if (coutCourt.contains("M")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Minerai)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Mine".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("P")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Pierre)));
      }
      if (coutCourt.contains("M")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Minerai)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Caravansery".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("B")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("B", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Bois)));
      }
      if (coutCourt.contains("Q")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Q", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Brique)));
      }
      if (coutCourt.contains("M")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("M", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Minerai)));
      }
      if (coutCourt.contains("P")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("P", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Pierre)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else if ("Forum".equals(r.carte.getNomEn())) {
      if (coutCourt.contains("T")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("T", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1,
            resultatEnCours.addAchat(r.endroit, Ressource.Tissu)));
      }
      if (coutCourt.contains("V")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("V", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Verre)));
      }
      if (coutCourt.contains("Y")) {
        lRes.addAll(recursivePaiement(coutCourt.replaceFirst("Y", ""),
            listeRessourcesCarteDispo, indiceRessCarte + 1, res2.clone()
                .addAchat(r.endroit, Ressource.Papyrus)));
      }
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2));
    } else {
      lRes.addAll(recursivePaiement(coutCourt, listeRessourcesCarteDispo,
          indiceRessCarte + 1, res2.clone()));
    }
    return lRes;
  }

  // Si un joueur peut payer les ressources d'un cout
  public static Cout retireRessourcesPayables(Partie partie, Joueur joueur,
      Cout coutATester) {
    if (joueur.possede("Sawmill")) {
      coutATester.retire(Ressource.Bois);
      coutATester.retire(Ressource.Bois);
    }
    if (joueur.possede("Quarry")) {
      coutATester.retire(Ressource.Pierre);
      coutATester.retire(Ressource.Pierre);
    }
    if (joueur.possede("Brickyard")) {
      coutATester.retire(Ressource.Brique);
      coutATester.retire(Ressource.Brique);
    }
    if (joueur.possede("Foundary")) {
      coutATester.retire(Ressource.Minerai);
      coutATester.retire(Ressource.Minerai);
    }
    if (joueur.possede("Lumber Yard")) {
      coutATester.retire(Ressource.Bois);
    }
    if (joueur.possede("Stone Pit")) {
      coutATester.retire(Ressource.Pierre);
    }
    if (joueur.possede("Clay Pool")) {
      coutATester.retire(Ressource.Brique);
    }
    if (joueur.possede("Ore Vein")) {
      coutATester.retire(Ressource.Minerai);
    }
    if (joueur.possede("Loom")) {
      coutATester.retire(Ressource.Tissu);
    }
    if (joueur.possede("Glassworks")) {
      coutATester.retire(Ressource.Verre);
    }
    if (joueur.possede("Press")) {
      coutATester.retire(Ressource.Papyrus);
    }
    if (joueur.possede("Tree Farm")) {
      if (coutATester.contient(Ressource.Bois)) {
        coutATester.retire(Ressource.Bois);
      } else if (coutATester.contient(Ressource.Brique)) {
        coutATester.retire(Ressource.Brique);
      }
    }
    if (joueur.possede("Excavation")) {
      if (coutATester.contient(Ressource.Pierre)) {
        coutATester.retire(Ressource.Pierre);
      } else if (coutATester.contient(Ressource.Brique)) {
        coutATester.retire(Ressource.Brique);
      }
    }
    if (joueur.possede("Clay Pit")) {
      if (coutATester.contient(Ressource.Minerai)) {
        coutATester.retire(Ressource.Minerai);
      } else if (coutATester.contient(Ressource.Brique)) {
        coutATester.retire(Ressource.Brique);
      }
    }
    if (joueur.possede("Timber Yard")) {
      if (coutATester.contient(Ressource.Bois)) {
        coutATester.retire(Ressource.Bois);
      } else if (coutATester.contient(Ressource.Pierre)) {
        coutATester.retire(Ressource.Pierre);
      }
    }
    if (joueur.possede("Forest Cave")) {
      if (coutATester.contient(Ressource.Bois)) {
        coutATester.retire(Ressource.Bois);
      } else if (coutATester.contient(Ressource.Minerai)) {
        coutATester.retire(Ressource.Minerai);
      }
    }
    if (joueur.possede("Mine")) {
      if (coutATester.contient(Ressource.Pierre)) {
        coutATester.retire(Ressource.Pierre);
      } else if (coutATester.contient(Ressource.Minerai)) {
        coutATester.retire(Ressource.Minerai);
      }
    }
    if (joueur.possede("Caravansery")) {
      if (coutATester.contient(Ressource.Bois)) {
        coutATester.retire(Ressource.Bois);
      } else if (coutATester.contient(Ressource.Brique)) {
        coutATester.retire(Ressource.Brique);
      } else if (coutATester.contient(Ressource.Minerai)) {
        coutATester.retire(Ressource.Minerai);
      } else if (coutATester.contient(Ressource.Pierre)) {
        coutATester.retire(Ressource.Pierre);
      }
    }
    if (joueur.possede("Forum")) {
      if (coutATester.contient(Ressource.Tissu)) {
        coutATester.retire(Ressource.Tissu);
      } else if (coutATester.contient(Ressource.Verre)) {
        coutATester.retire(Ressource.Verre);
      } else if (coutATester.contient(Ressource.Papyrus)) {
        coutATester.retire(Ressource.Papyrus);
      }
    }
    return coutATester;
  }

  public ChoixAchatRessources getCoutTotal(Partie partie, Joueur joueur,
      Carte carteAJouer) {
    ChoixAchatRessources choixFinal = null;
    if (hasCarteParent(joueur, carteAJouer)) {
      System.out.println(carteAJouer + " " + "Carte gratos ! "
          + carteAJouer.getParent());
      return new ChoixAchatRessources();
    }
    if (carteAJouer.getCout().getListeRessources().size() == 0) {
      choixFinal = new ChoixAchatRessources();
      choixFinal.setPiecesPourBanque(carteAJouer.getCout().getPrix());
    } else {
      choixFinal = fonctionDepart(partie, joueur, carteAJouer.getCout());
    }
    // System.out.println("_______________________________" + choixFinal);
    return choixFinal;
  }

  private static boolean hasCarteParent(Joueur joueur, Carte carteAJouer) {
    if (carteAJouer.getParent() != null
        && joueur.possede(carteAJouer.getParent().getNomEn())) {
      return true;
    }
    return false;
  }

  public class RecRessourceCarte {
    public Place endroit;
    public Carte carte;

    public RecRessourceCarte(Place endroit, Carte carte) {
      super();
      this.endroit = endroit;
      this.carte = carte;
    }
  }
}
