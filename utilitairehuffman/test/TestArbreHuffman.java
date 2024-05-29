/*
 * TestArbreHuffman.java         10/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import utilitairehuffman.src.ArbreHuffman;
import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

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
		"utilitairehuffman/test/textes/gros_fichier.txt",
		"utilitairehuffman/test/textes/java.txt",
		"utilitairehuffman/test/textes/le_vide.txt",
		"utilitairehuffman/test/textes/mystere.txt",
		"utilitairehuffman/test/textes/oeufman.txt",
		"utilitairehuffman/test/textes/texte_binaire.txt"
	};
	
	@Test
	public void testArbreHuffman() {
		
		// Ne devrait pas envoyer "erreur d'encodage"
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
												 (cheminsFichiers[0])));
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
												 (cheminsFichiers[3])));
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
												 (cheminsFichiers[6])));
		
		// Erreur de dépassement ?
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
				 								 (cheminsFichiers[1])));
	}
	
	@Test
	public void testToString() throws IOException {
		
		// Cas où l'arbre de Huffman est vide (dictionnaire vide.)
		assertEquals("", new ArbreHuffman(getDictLettresFrequences
										 (cheminsFichiers[3])).toString());
	}
}
