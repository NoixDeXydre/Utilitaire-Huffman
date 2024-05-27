/*
 * TestDictionnaireHuffman.java         26/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

import java.io.IOException;
import java.util.LinkedHashMap;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe DictionnaireHuffman.
 * @author TD 2 Groupe 4 : Noa M'Tima Lesniak
 */
public class TestDictionnaireHuffman {

	@Test
	public void testGetDictLettresFrequences() throws IOException {
		
		final String[] CHEMINS_FICHIERS = {
			"utilitairehuffman/test/textes/cajouj.txt", // 8 lettres
			"utilitairehuffman/test/textes/mystere.txt", // 9 lettres
		};
		
		// Données mises dans l'ordre croissant
		
		final double[][] FREQUENCES_VALIDES = {
			{.125, .125, .25, .25, .25},
			{1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0,
			 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 2.0 / 9.0}
		};
		
		final char[][] LETTRES_VALIDES = {
			{'b', 'u', 'a', 'j', 'o'},
			{'u', 'p', 'e', 'c', 't', ' ', '.', 's'}
		};
		
		final LinkedHashMap<Character, Double> DICTIONNAIRE1
		= getDictLettresFrequences(CHEMINS_FICHIERS[0]);
		
		final LinkedHashMap<Character, Double> DICTIONNAIRE2
		= getDictLettresFrequences(CHEMINS_FICHIERS[1]);
		
		int i = 0;
		
		/*
		 *  Vérifie si les fréquences sont bien triées,
		 *  puis vérifie ensuite l'ordre des clés
		 */
		
		i = 0;
		for (char k : DICTIONNAIRE1.keySet()) {
			assertEquals(DICTIONNAIRE1.get(k), FREQUENCES_VALIDES[0][i]);
			assertEquals(k, LETTRES_VALIDES[0][i]);
			i++;
		}
		
		i = 0;
		for (char k : DICTIONNAIRE2.keySet()) {
			assertEquals(DICTIONNAIRE2.get(k), FREQUENCES_VALIDES[1][i]);
			assertEquals(k, LETTRES_VALIDES[1][i]);
			i++;
		}
		
	}
}
