package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mathieujjava.sevenwonders.enums.Medaille;
import fr.mathieujjava.sevenwonders.enums.Ressource;
import fr.mathieujjava.sevenwonders.enums.TypeAction;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/conf-context.xml", "/cartes-context.xml", "/merveilles-context.xml"})
public class BotServiceImplTest {
  @Resource(name="listeMerveilles")
  List<Merveille> listeMerveilles;
  
  private Partie partie;


  @Autowired 
  private PartieManager partieManager;
  
  
  @Before
  public void setUp() {
    partie = new Partie();
    partieManager.initPartie(partie, 4);
  }

  @Test
  public void test1() {
    System.out.println(partie);
  }
}
