package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Place;
import fr.mathieujjava.sevenwonders.enums.Ressource;
import fr.mathieujjava.sevenwonders.enums.TypeAction;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class PartieManagerImplTest {

  private Partie partie;

  private Joueur joueurA, joueurB, joueurC, joueurD;
  
  

  private Carte carteScierie, carteUniversite;

  private PartieManagerImpl partieManager;

  @Before
  public void setUp() {
    partieManager = new PartieManagerImpl();
    Merveille merveilleA = new Merveille("A", "A", 1, "", null, new Cout[0]);
    partie = new Partie();
    partie.addJoueur(joueurA = new Joueur(merveilleA));
    partie.addJoueur(joueurB = new Joueur(merveilleA));
    partie.addJoueur(joueurC = new Joueur(merveilleA));
    partie.addJoueur(joueurD = new Joueur(merveilleA));
    joueurA.getMain().add(carteScierie = new Carte(TypeCarte.MatierePremiere, "Sawmill", "Scierie", new Cout(1), null, "Fournit 2 bois"));
    joueurA.getMain().add(new Carte(TypeCarte.Science, "Library", "Bibliothèque", new Cout(0, Ressource.Pierre, Ressource.Pierre, Ressource.Tissu), null, "Ecriture"));
    carteUniversite = new Carte(TypeCarte.Science, "University", "Université", new Cout(0, Ressource.Bois, Ressource.Bois, Ressource.Papyrus, Ressource.Verre), null, "Ecriture");
  }

  @Test
  public void testDefausseReussie() throws Exception {
    Action action = new Action(joueurA, TypeAction.Defausse, carteScierie);
    
    // on vérifie la configuration de départ
    assertEquals(0, partie.getDefausse().size());
    assertEquals(3, joueurA.getNombrePieces().intValue());
    assertEquals(2, joueurA.getMain().size());
    partieManager.effectueAction(partie, action);
    // le joueur gagne 3 pièces, et met une carte en défausse
    assertEquals(6, joueurA.getNombrePieces().intValue());
    assertEquals(1, partie.getDefausse().size());
    assertEquals(carteScierie, partie.getDefausse().get(0));
    assertEquals(1, joueurA.getMain().size());
  }
  
  @Test(expected=Exception.class)
  public void testDefausseImpossible() throws Exception {
    Action action = new Action(joueurA, TypeAction.Defausse, carteUniversite);
    partieManager.effectueAction(partie, action);
  }
  
  @Test
  public void testPaieCout() {
    ChoixAchatRessources choix = new ChoixAchatRessources();
    choix.addAchat(Place.Gauche, Ressource.Bois);
    choix.addAchat(Place.Gauche, Ressource.Pierre);
    
  }
}
