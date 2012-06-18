package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/conf-context.xml", "/merveilles-beans.xml"})
public class MerveillesBeansTest {
  @Autowired
  Merveille merveille1;
  //List<Merveille> merveillesListe;
  
  @Test
  public void nombreMerveilleTest() {
    assertNotNull(merveille1);
    //assertEquals(1, merveillesListe.size());
  }
}
