package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/merveilles-context.xml"})
public class MerveillesBeansTest {
  @Resource(name="listeMerveilles")
  List<Merveille> listeMerveilles;
  
  @Test
  public void nombreMerveilleTest() {
    assertEquals(7, listeMerveilles.size());
    Merveille m = listeMerveilles.get(0);
    assertEquals(3, m.getCoutsAge().length);
       
  }
}
