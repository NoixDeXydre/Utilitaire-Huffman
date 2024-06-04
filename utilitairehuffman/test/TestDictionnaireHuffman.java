/*
 * TestDictionnaireHuffman.java         26/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import static utilitairehuffman.src.DictionnaireHuffman.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe DictionnaireHuffman.
 * @author TD 2 Groupe 4 : Noa M'tima Lesniak
 */
public class TestDictionnaireHuffman {

    final static String[] CHEMINS_ARBRES = {
        "utilitairehuffman/test/textes/arbres/1maam112.txt",
        "utilitairehuffman/test/textes/arbres/saut.txt"
    }; 
    
    final static String[] CHEMINS_FICHIERS = {
        "utilitairehuffman/test/textes/cajouj.txt", // 8 lettres
        "utilitairehuffman/test/textes/le_vide.txt", // 0 lettres
        "utilitairehuffman/test/textes/mystere.txt", // 9 lettres
    };
    
    /**
     * Tests de getDictCompression.
     * @throws FileNotFoundException 
     *
     * @throws IOException @see java.lang.IOException
     */
    @Test
    public void testGetDictCompression() throws FileNotFoundException {
        
        final String[] DICT_VALIDES = {
            "{1=00, a=10, m=01, 2=11}",
            "{}",
            "{\n=1, é=0}"
        };
        
        assertEquals(DICT_VALIDES[0],
                     getDictCompression(CHEMINS_ARBRES[0]).toString());
        
        // Arbre vide
        assertEquals(DICT_VALIDES[1],
                getDictCompression(CHEMINS_FICHIERS[1]).toString());
        
        // Cas particulier avec un saut de ligne et un accent
        assertEquals(DICT_VALIDES[2],
                getDictCompression(CHEMINS_ARBRES[1]).toString());
    }
    
    /**
     * Tests de getDictCompression.
     * @throws FileNotFoundException 
     *
     * @throws IOException @see java.lang.IOException
     */
    @Test
    public void testGetDictDecompression() throws FileNotFoundException {
        
        final String[] DICT_VALIDES = {
            "{00=1, 10=a, 01=m, 11=2}",
            "{}",
            "{1=\n, 0=é}"
        };
        
        assertEquals(DICT_VALIDES[0],
                getDictDecompression(CHEMINS_ARBRES[0]).toString());
   
       // Arbre vide
       assertEquals(DICT_VALIDES[1],
               getDictDecompression(CHEMINS_FICHIERS[1]).toString());
       
       // Cas particulier avec un saut de ligne et un accent
       assertEquals(DICT_VALIDES[2],
               getDictDecompression(CHEMINS_ARBRES[1]).toString());
    }

    /**
     * Tests de getDictLettresFrequences.
     *
     * @throws IOException @see java.lang.IOException
     */
    @Test
    public void testGetDictLettresFrequences() throws IOException {

        // Données mises dans l'ordre croissant

        final double[][] FREQUENCES_VALIDES = {
            {.125, .125, .25, .25, .25},
            {},
            {1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0,
             1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 2.0 / 9.0},
        };

        final char[][] LETTRES_VALIDES = {
            {'b', 'u', 'a', 'j', 'o'},
            {},
            {'u', 'p', 'e', 'c', 't', ' ', '.', 's'},
        };

        final LinkedHashMap<Character, Double> DICTIONNAIRE1
        = getDictLettresFrequences(CHEMINS_FICHIERS[0]);

        final LinkedHashMap<Character, Double> DICTIONNAIRE2
        = getDictLettresFrequences(CHEMINS_FICHIERS[1]);

        final LinkedHashMap<Character, Double> DICTIONNAIRE3
        = getDictLettresFrequences(CHEMINS_FICHIERS[2]);

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

        // Dictionnaire vide
        assertEquals(0, DICTIONNAIRE2.size());

        i = 0;
        for (char k : DICTIONNAIRE3.keySet()) {
            assertEquals(DICTIONNAIRE3.get(k), FREQUENCES_VALIDES[2][i]);
            assertEquals(k, LETTRES_VALIDES[2][i]);
            i++;
        }
    }
}
