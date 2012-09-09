package fr.mathieujjava.sevenwonders;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/conf-context.xml", "/cartes-context.xml", "/merveilles-context.xml" })
public class ControllerTest {
  @Resource(name = "listeMerveilles")
  List<Merveille> listeMerveilles;

  @Test
  public void nombreMerveilleTest() {
    assertEquals(7, listeMerveilles.size());
  }
}
