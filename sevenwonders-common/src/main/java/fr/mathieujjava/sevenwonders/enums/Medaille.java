package fr.mathieujjava.sevenwonders.enums;

public enum Medaille {
	Defaite, VictoireI, VictoireII, VictoireIII;

	public Integer getValeur() {
		switch (this) {
		case Defaite:
			return -1;
		case VictoireI:
			return 1;
		case VictoireII:
			return 3;
		case VictoireIII:
			return 5;
		}
		return 0;
	}
}