package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Ressource;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;

public class CarteTest {

  @Test
  public void testConstructeurs() {
    Carte carte1 = new Carte(TypeCarte.MatierePremiere, "Sawmill", "Scierie", new Cout(1), null, "Fournit 2 bois");
    assertEquals(0, carte1.getCout().getListeRessources().size());
    assertTrue("Scierie".equals(carte1.getNomFr()));

    Carte carte2 = new Carte(TypeCarte.Science, "Library", "Bibliothèque", new Cout(0, Ressource.Pierre, Ressource.Pierre, Ressource.Tissu), null, "Ecriture");
    assertEquals(3, carte2.getCout().getListeRessources().size());
    assertTrue("Bibliothèque".equals(carte2.getNomFr()));

    Carte carte3 =
        new Carte(
            TypeCarte.Science,
            "University",
            "Université",
            new Cout(0, Ressource.Bois, Ressource.Bois, Ressource.Papyrus, Ressource.Verre),
            carte2,
            "Ecriture");
    assertEquals(4, carte3.getCout().getListeRessources().size());
    assertTrue("University".equals(carte3.getNomEn()));
    assertEquals(carte2, carte3.getParent());
  }
}
