package fr.mathieujjava.sevenwonders;

public interface BotService {
  Action getBotAction(Partie partie, Joueur joueur);
  
  public int getPonderationCarte(Partie partie, Joueur joueur,
      Carte carte);
  
  public ChoixAchatRessources getCoutTotal(Partie partie, Joueur joueur,
      Carte carteAJouer);
}
