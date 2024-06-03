/*
 * TestArbreHuffman.java         10/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import utilitairehuffman.src.ArbreHuffman;
import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		"utilitairehuffman/test/textes/gros_fichier.txt",
		"utilitairehuffman/test/textes/le_vide.txt",
		"utilitairehuffman/test/textes/testAB1.txt",
		"utilitairehuffman/test/textes/texte_binaire.txt"
	};
	
        /**
         * Tests de arbreHuffman
         */
	@Test
	public void testArbreHuffman() {
		
		// Ne devrait pas envoyer "erreur d'encodage"
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
												 (cheminsFichiers[1])));
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
												 (cheminsFichiers[3])));
		
		// Erreur de dépassement ?
		assertDoesNotThrow(() -> new ArbreHuffman(getDictLettresFrequences
				 								 (cheminsFichiers[1])));
	
        /**
         * Tests de toString
         * 
         * @throws IOException @see java.lang.IOException
         */
	@Test
	public void testToString() throws IOException {
		
		final String fichier1 = 
		"""
		codehuffman = 00 ; encode = 00110001 ; symbole = 1
		codehuffman = 10 ; encode = 01100001 ; symbole = a
		codehuffman = 01 ; encode = 01101101 ; symbole = m
		codehuffman = 11 ; encode = 00110010 ; symbole = 2
		""";
		
		// Cas où l'arbre de Huffman est vide (dictionnaire vide.)
		assertEquals("", new ArbreHuffman(getDictLettresFrequences
										 (cheminsFichiers[1])).toString());
		
		// Texte binaire
		//assertEquals("", new ArbreHuffman(getDictLettresFrequences
				 //(cheminsFichiers[3])).toString());
		
		// Exemple : 1maam112
		assertEquals(fichier1.replaceAll("\\s+", ""),
					new ArbreHuffman
					(getDictLettresFrequences
					(cheminsFichiers[2])).toString().replaceAll("\\s+", ""));
	}
}
