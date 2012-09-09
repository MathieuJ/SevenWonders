package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class JoueurTest {

  @Test
  public void testConstructeurs() {
    Merveille merveille =
        new Merveille(
            "The Colossus of Rhodes",
            "Le Colosse de Rhodes",
            1,
            "CC",
            Ressource.Minerai,
            "BB,QQ,MMMM", "colosse.jpg");

    Joueur joueur = new Joueur(merveille);
    assertEquals(0, joueur.getListeMedailles().size());
    assertEquals(3, joueur.getNombrePieces().intValue());
    assertEquals(0, joueur.getEtageMerveille().intValue());

    joueur.modifieNombrePieces(4);
    assertEquals(7, joueur.getNombrePieces().intValue());
  }
}
