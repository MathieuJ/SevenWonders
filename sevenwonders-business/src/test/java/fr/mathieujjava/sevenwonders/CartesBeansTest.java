package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/conf-context.xml", "/cartes-context.xml", "/merveilles-context.xml"})
public class CartesBeansTest {
  @Resource(name="listeCartesAgeI")
  List<Carte> listeCartesAgeI;
  
  @Resource(name="listeCartesAgeII")
  List<Carte> listeCartesAgeII;
  
  @Resource(name="listeCartesAgeIII")
  List<Carte> listeCartesAgeIII;
  
  @Resource(name="listeCartesGuilde")
  List<Carte> listeCartesGuilde;
  
  @Test
  public void nombreMerveilleTest() {
    assertEquals(28, listeCartesAgeI.size());
    assertEquals(22, listeCartesAgeII.size());
    assertEquals(18, listeCartesAgeIII.size());
    assertEquals(10, listeCartesGuilde.size());
  }
}
