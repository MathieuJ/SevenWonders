package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.mathieujjava.sevenwonders.enums.Ressource;
import fr.mathieujjava.sevenwonders.enums.TypeCarte;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/conf-context.xml", "/cartes-context.xml", "/merveilles-context.xml"})
public class BotServiceImplTest {
  @Resource(name="listeMerveilles")
  List<Merveille> listeMerveilles;
  
  private Partie partie;


  @Autowired 
  private PartieManager partieManager;
  
  @Autowired 
  private BotService botService;
  
  @Before
  public void setUp() {
    partie = new Partie();
    partieManager.initPartie(partie, 4);
  }

  @Test
  public void testPaiementBasique() {
   
    Merveille m = null;
    for (Merveille merveille : listeMerveilles) {
      if (merveille.getId().equals(1)) m = merveille;
    }
    partie.getJoueur(1).setMerveille(m);
    
    assertEquals(partie.getJoueur(1).getMerveille().getId(), new Integer(1));
    Carte carte = new Carte(TypeCarte.Civil, "caserne", "", new Cout(0, Ressource.Minerai), null, "");
    
    // Un joueur avec le colosse de Rhodes doit pouvoir jouer Bains
    assertNotNull(botService.getCoutTotal(partie, partie.getJoueur(1), carte));
  }
}
