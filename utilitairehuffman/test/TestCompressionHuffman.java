/*
 * TestCompressionHuffman.java                           4 juin 2024
 * IUT de Rodez Info1 TP32 2023-2024, pas de copyright 
 */
package utilitairehuffman.test;

import static utilitairehuffman.src.CompressionHuffman.compresserFichier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe Compressionhuffman.
 * @author TD 2 Groupe 4 : Noa M'tima Lesniak
 */
class TestCompressionHuffman {
    
    final static String[] CHEMINS_ARBRES = {
        "utilitairehuffman/test/textes/arbres/1maam112.txt",
        "utilitairehuffman/test/textes/arbres/java.txt"
    }; 
    
    final static String[] CHEMINS_FICHIERS = {
        "utilitairehuffman/test/textes/fichier_compresser1.txt"
    }; 
    
    final static String[] CHEMINS_FICHIERS_SORTIE = {
        "utilitairehuffman/test/textes/sortie/fichier_compresser1"
    }; 
    
    @Test
    void testCompresserFichier() throws IOException {
        assertDoesNotThrow(() -> compresserFichier(CHEMINS_FICHIERS[0],
                                                   CHEMINS_ARBRES[0],
                                                   CHEMINS_FICHIERS_SORTIE[0]));
    }

}
