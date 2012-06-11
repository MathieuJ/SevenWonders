package fr.mathieujjava.sevenwonders.enums;

public enum Ressource {
	Brique, Pierre, Minerai, Verre, Tissu, Papyrus, Bois;

	public boolean isMatierePremiere() {
		return this == Bois || this == Brique || this == Minerai || this == Pierre;
	}
}
