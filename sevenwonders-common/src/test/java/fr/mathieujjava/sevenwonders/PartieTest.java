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
    Merveille merveilleA = new Merveille("A", "A", 1, "", null, new Cout[0]);
    Merveille merveilleB = new Merveille("B", "B", 1, "", null, new Cout[0]);
    Merveille merveilleC = new Merveille("C", "C", 1, "", null, new Cout[0]);
    Merveille merveilleD = new Merveille("D", "D", 1, "", null, new Cout[0]);
    partie = new Partie();
    partie.addJoueur(joueurA = new Joueur(merveilleA));
    partie.addJoueur(joueurB = new Joueur(merveilleB));
    partie.addJoueur(joueurC = new Joueur(merveilleC));
    partie.addJoueur(joueurD = new Joueur(merveilleD));

  }

  @Test
  public void testConstructeurs() {
    assertNotNull(partie.getListeJoueur());
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
