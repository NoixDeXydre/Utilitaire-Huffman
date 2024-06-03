/**
 * LectureFichier.java         26/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utilitairehuffman.src.LectureFichier.getLiseurChar;
import static utilitairehuffman.src.LectureFichier.getLongueurTexte;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe LectureFichier.
 * @author TD 2 Groupe 4 : Noa M'Tima Lesniak
 */
public class TestLectureFichier {

    /**
     * Tests de getLiseurChar
     */
    @Test
    public void testGetLiseurChar() { // static auto d'Eclipse

        // Cas d'erreur
        assertThrows(IOException.class,
                    () -> getLiseurChar("a"));
    }

    /**
     * Tests de getLongeurTexte
     *
     * @throws IOException @see java.lang.IOException
     */
    @Test
    public void testGetLongueurTexte() throws IOException { // static auto d'Eclipse

        final String[] cheminsFichiers = {
            "utilitairehuffman/test/textes/cajouj.txt",
            "utilitairehuffman/test/textes/java.txt",
            "utilitairehuffman/test/textes/mystere.txt",
            "utilitairehuffman/test/textes/oeufman.txt",
            "utilitairehuffman/test/textes/le_vide.txt"
        };

        assertEquals(getLongueurTexte(cheminsFichiers[0]), 8);
        assertEquals(getLongueurTexte(cheminsFichiers[1]), 50);
        assertEquals(getLongueurTexte(cheminsFichiers[2]), 9);
        assertEquals(getLongueurTexte(cheminsFichiers[3]), 45);
        assertEquals(getLongueurTexte(cheminsFichiers[4]), 0);

    }

}
