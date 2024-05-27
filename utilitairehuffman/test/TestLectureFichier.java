/**
 * LectureFichier.java         26/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.test;

import static utilitairehuffman.src.LectureFichier.getLiseurChar;
import static utilitairehuffman.src.LectureFichier.getLongueurTexte;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe LectureFichier.
 * @author TD 2 Groupe 4 : Noa M'Tima Lesniak
 */
public class TestLectureFichier {
	
	@Test
	public void testGetLiseurChar() {

		// Cas d'erreur
		assertThrows(IOException.class, 
					() -> getLiseurChar("a"));
	}
	
	@Test
	public void testGetLongueurTexte() throws IOException {
		
		final String[] cheminsFichiers = {
			"utilitairehuffman/test/textes/cajouj.txt",
			"utilitairehuffman/test/textes/java.txt",
			"utilitairehuffman/test/textes/mystere.txt",
			"utilitairehuffman/test/textes/oeufman.txt"
		};

		assertEquals(getLongueurTexte(cheminsFichiers[0]), 8);
		assertEquals(getLongueurTexte(cheminsFichiers[1]), 47);
		assertEquals(getLongueurTexte(cheminsFichiers[2]), 9);
		assertEquals(getLongueurTexte(cheminsFichiers[3]), 45);
		
	}

}
