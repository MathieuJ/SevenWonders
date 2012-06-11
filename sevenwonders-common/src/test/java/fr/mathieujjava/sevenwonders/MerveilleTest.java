package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class MerveilleTest {

  @Test
  public void testConstructeurs() {
    Merveille merveille = new Merveille("The Colossus of Rhodes",
        "Le Colosse de Rhodes", 1, "CC", Ressource.Minerai, new Cout(0,
            Ressource.Bois, Ressource.Bois), new Cout(0, Ressource.Brique,
            Ressource.Brique, Ressource.Brique), new Cout(0, Ressource.Minerai,
            Ressource.Minerai, Ressource.Minerai, Ressource.Minerai));
    assertNotNull(merveille);
    assertNotNull(merveille.getCoutAgeIII());
    assertNull(merveille.getCoutAgeIV());

    merveille = new Merveille("The Lighthouse of Alexandria",
        "Le Phare d'Alexandrie", 1, "M/B/Q/P", Ressource.Verre, new Cout(0,
            Ressource.Pierre, Ressource.Pierre), new Cout(0, Ressource.Minerai,
            Ressource.Minerai), new Cout(0, Ressource.Verre, Ressource.Verre));
    assertNotNull(merveille);
    assertNotNull(merveille.getCoutAgeIII());
    assertNull(merveille.getCoutAgeIV());

    merveille = new Merveille("Pyramide B", "Pyramide B", 2, "3V 5V 5V 7V",
        Ressource.Pierre, new Cout(0, Ressource.Bois, Ressource.Bois),
        new Cout(0, Ressource.Pierre, Ressource.Pierre, Ressource.Pierre),
        new Cout(0, Ressource.Brique, Ressource.Brique, Ressource.Brique),
        new Cout(0, Ressource.Pierre, Ressource.Pierre, Ressource.Pierre,
            Ressource.Papyrus, Ressource.Tissu));
    assertNotNull(merveille);
    assertNotNull(merveille.getCoutAgeIII());
    assertNotNull(merveille.getCoutAgeIV());

  }

}
