package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class MerveilleTest {

  @Test
  public void testConstructeurs() throws Exception {
    List<Cout> l = new ArrayList();
    l.add(new Cout(0, Ressource.Bois, Ressource.Bois));
    l.add(new Cout(0, Ressource.Brique, Ressource.Brique, Ressource.Brique));
    l.add(new Cout(0, Ressource.Minerai, Ressource.Minerai, Ressource.Minerai, Ressource.Minerai));
    Merveille merveille = new Merveille(1, "The Colossus of Rhodes",
        "Le Colosse de Rhodes", 1, "CC", Ressource.Minerai, "1,1,2", "colosse.jpg");
    assertNotNull(merveille);
    assertNotNull(merveille.getCoutAge(3));
  }
}
