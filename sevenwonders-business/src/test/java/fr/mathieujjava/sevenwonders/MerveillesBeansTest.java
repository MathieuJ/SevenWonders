package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mathieujjava.sevenwonders.enums.Ressource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/merveilles-context.xml" })
public class MerveillesBeansTest {
  @Resource(name = "listeMerveilles")
  List<Merveille> listeMerveilles;

  @Test
  public void nombreMerveilleTest() {
    assertEquals(7, listeMerveilles.size());
    Merveille m = listeMerveilles.get(0);
    assertEquals(3, m.getCoutsAge().length);

  }

  private Object[][] merveillesProvider() {
    Object[][] testMerveille = new Object[7][3];
    return new Object[][] {
        {listeMerveilles.get(0), 1, "The Colossus of Rhodes", new Cout(0, Ressource.Brique, Ressource.Brique), new Cout(0, Ressource.Minerai), new Cout(0, Ressource.Pierre)},
        {listeMerveilles.get(1), 2, "The Lighthouse of Alexandria", new Cout(0, Ressource.Brique, Ressource.Brique), new Cout(0, Ressource.Minerai), new Cout(0, Ressource.Pierre)}
    };   
  }
}
