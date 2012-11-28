package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class PartieTest {
  Joueur joueurA, joueurB, joueurC, joueurD;

  Partie partie;

  @Before
  public void setUp() {
    Merveille merveilleA = new Merveille(1, "A", "A", 1, "", null, "", null);
    Merveille merveilleB = new Merveille(2, "B", "B", 1, "", null, "", null);
    Merveille merveilleC = new Merveille(3, "C", "C", 1, "", null, "", null);
    Merveille merveilleD = new Merveille(4, "D", "D", 1, "", null, "", null);
    partie = new Partie();
    partie.addJoueur(joueurA = new Joueur(merveilleA, true));
    partie.addJoueur(joueurB = new Joueur(merveilleB, true));
    partie.addJoueur(joueurC = new Joueur(merveilleC, true));
    partie.addJoueur(joueurD = new Joueur(merveilleD, true));

  }

  @Test
  public void testConstructeurs() {
    assertNotNull(partie.getListeJoueurs());
    assertEquals(0, joueurA.getPlace().intValue());
    assertEquals(1, joueurB.getPlace().intValue());
    assertEquals(2, joueurC.getPlace().intValue());
    assertEquals(3, joueurD.getPlace().intValue());
    assertEquals(1, partie.getTour().intValue());
    assertEquals(1, partie.getAge().intValue());
    partie.setTour(partie.getTour() + 1);
    partie.setAge(2);
    assertEquals(2, partie.getTour().intValue());
    assertEquals(2, partie.getAge().intValue());
  }

  @Test
  public void testVoisins() {
    assertEquals(joueurA, partie.getVoisinGauche(joueurB));
    assertEquals(joueurB, partie.getVoisinGauche(joueurC));
    assertEquals(joueurC, partie.getVoisinGauche(joueurD));
    assertEquals(joueurD, partie.getVoisinGauche(joueurA));
    assertEquals(joueurA, partie.getVoisinDroite(joueurD));
    assertEquals(joueurB, partie.getVoisinDroite(joueurA));
    assertEquals(joueurC, partie.getVoisinDroite(joueurB));
    assertEquals(joueurD, partie.getVoisinDroite(joueurC));
  }

}
