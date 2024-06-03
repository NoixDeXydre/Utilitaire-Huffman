/*
 * TestArbreHuffman.java         10/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import utilitairehuffman.src.ArbreHuffman;

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
		"utilitairehuffman/test/textes/le_vide.txt",
		"utilitairehuffman/test/textes/mystere.txt",
		"utilitairehuffman/test/textes/oeufman.txt",
		"utilitairehuffman/test/textes/texte_binaire.txt"
	};
	
        /**
         * Tests de arbreHuffman
         */
	@Test
	public void testArbreHuffman() {
		
		// Ne devrait pas envoyer "erreur d'encodage"
		assertDoesNotThrow(() -> new ArbreHuffman(cheminsFichiers[1]));
		assertDoesNotThrow(() -> new ArbreHuffman(cheminsFichiers[5]));
	}
	
        /**
         * Tests de getDictHuffman
         */
	@Test
	public void testGetDictHuffman() {
		fail("Not yet implemented");
	}
	
        /**
         * Tests de toString
         * 
         * @throws IOException @see java.lang.IOException
         */
	@Test
	public void testToString() throws IOException {
		
		// Cas où l'arbre de Huffman est vide (dictionnaire vide.)
		assertEquals("", new ArbreHuffman(cheminsFichiers[2]).toString());
	}
}
