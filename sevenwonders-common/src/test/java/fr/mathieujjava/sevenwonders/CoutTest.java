package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.mathieujjava.sevenwonders.enums.Ressource;

public class CoutTest {

  @Test
  public void testConstructeurs() {
    Cout cout = new Cout(1, Ressource.Bois);
    assertNotNull(cout.getListeRessources());

    cout = new Cout(0, Ressource.Brique, Ressource.Papyrus, Ressource.Brique);
    assertNotNull(cout);
    assertEquals(3, cout.getListeRessources().size());
    assertEquals(0, cout.getPrix().intValue());

    cout = new Cout(8);
    assertNotNull(cout);
    assertEquals(0, cout.getListeRessources().size());
    assertEquals(8, cout.getPrix().intValue());
  }

  @Test
  public void testOperationRetire() throws Exception {
	  Cout cout = new Cout(5, Ressource.Bois, Ressource.Bois, Ressource.Pierre, Ressource.Pierre, Ressource.Pierre, Ressource.Papyrus, Ressource.Tissu);
	  cout.retire(2);
	  assertEquals(3, cout.getPrix().intValue());
	  cout.retire(Ressource.Bois);
	  assertEquals(6, cout.getListeRessources().size());
	  cout.retire(Ressource.Bois, Ressource.Bois, Ressource.Papyrus, Ressource.Papyrus);
	  assertEquals(4, cout.getListeRessources().size());
  }
  
  @Test(expected=Exception.class)
  public void testRetirePrixTropGrand() throws Exception {
	  Cout cout = new Cout(5);
	  cout.retire(8);
  }
}
