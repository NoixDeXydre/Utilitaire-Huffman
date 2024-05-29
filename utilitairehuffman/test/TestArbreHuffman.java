/*
 * TestArbreHuffman.java         10/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import utilitairehuffman.src.ArbreHuffman;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe ArbreHuffman.
 * 
 * TODO changer les auteurs
 * @author TD 2 Groupe 4 : Noa M'tima Lesniak
 */
public class TestArbreHuffman {
	
	final String[] cheminsFichiers = {
		"utilitairehuffman/test/textes/cajouj.txt",
		"utilitairehuffman/test/textes/java.txt",
		"utilitairehuffman/test/textes/mystere.txt",
		"utilitairehuffman/test/textes/oeufman.txt"
	};
	
	@Test
	public void testArbreHuffman() throws IOException {
		ArbreHuffman test = new ArbreHuffman(cheminsFichiers[0]);
		System.out.print(test);
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetDictHuffman() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
}
