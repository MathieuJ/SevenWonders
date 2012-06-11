package fr.mathieujjava.sevenwonders.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class RessourceTest {

	@Test
	public void test() {
		assertTrue(Ressource.Bois.isMatierePremiere());
		assertTrue(Ressource.Minerai.isMatierePremiere());
		assertTrue(Ressource.Pierre.isMatierePremiere());
		assertTrue(Ressource.Brique.isMatierePremiere());
		assertFalse(Ressource.Papyrus.isMatierePremiere());
		assertFalse(Ressource.Tissu.isMatierePremiere());
		assertFalse(Ressource.Verre.isMatierePremiere());
	}
}
