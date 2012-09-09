package fr.mathieujjava.sevenwonders.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.mathieujjava.sevenwonders.Joueur;
import fr.mathieujjava.sevenwonders.Partie;
import fr.mathieujjava.sevenwonders.PartieManager;
import fr.mathieujjava.sevenwonders.form.PartieForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
  
  @Autowired
  PartieManager partieManager;
  
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  
  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! the client dddd locale is "+ locale.toString());
    
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    
    String formattedDate = dateFormat.format(date);
    
    model.addAttribute("serverTime", formattedDate + "coincoin" );
    
    return "home";
    
  }
  
  @RequestMapping(value = "nouvellePartie")
  public ModelAndView nouvellePartie(@ModelAttribute("partieForm") PartieForm partieForm, Model model) {
    Partie partie = new Partie();
    partieManager.initPartie(partie, 4);
    model.addAttribute("partie", partie);
    for (Joueur joueur : partie.getListeJoueurs()) {
      System.out.println("joueur " + joueur.getMerveille().getNomEn());
    }
    return new ModelAndView("partie", "partie", partie);
  }
  
}
