package fr.mathieujjava.sevenwonders.bean;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import fr.mathieujjava.sevenwonders.Partie;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class PartieBean {
  private Partie partie;

  public Partie getPartie() {
    return partie;
  }

  public void setPartie(Partie partie) {
    this.partie = partie;
  }
  
  @PostConstruct
  public void init() {
    partie = new Partie();
    System.out.println("_________________________________");
  }
  
  
  
}
