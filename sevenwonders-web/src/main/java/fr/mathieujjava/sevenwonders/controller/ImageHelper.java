package fr.mathieujjava.sevenwonders.controller;

public class ImageHelper {
  public static String getNomImageFromMerveilleNomEn(String nomEn) {
    if ("The Colossus of Rhodes".equals(nomEn)){
      return "colosse";
    } else if ("The Lighthouse of Alexandria".equals(nomEn)){
      return "alexandrie";
    } else if ("The Temple of Artemis in Ephesus".equals(nomEn)){
      return "artemis";
    } else if ("The Hanging Gardens of Babylon".equals(nomEn)){
      return "babylone";
    } else if ("The Statue of Zeus in Olympia".equals(nomEn)){
      return "olympe";
    } else if ("The Mausoleum of Halicarnassus".equals(nomEn)){
      return "halicarnassus";
    } else if ("The Pyramids of Giza".equals(nomEn)){
      return "gizeh";
    }
    return "";
  }
}
