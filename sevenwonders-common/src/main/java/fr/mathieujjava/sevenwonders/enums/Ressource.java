package fr.mathieujjava.sevenwonders.enums;

public enum Ressource {
  Brique, Pierre, Minerai, Verre, Tissu, Papyrus, Bois;

  public boolean isMatierePremiere() {
    return this == Bois || this == Brique || this == Minerai || this == Pierre;
  }

  public String getSymbole() {
    switch (this) {
    case Bois:
      return "B";
    case Brique:
      return "Q";
    case Minerai:
      return "M";
    case Papyrus:
      return "Y";
    case Pierre:
      return "P";
    case Tissu:
      return "T";
    case Verre:
      return "V";
    }
    return "-";
  }

}
