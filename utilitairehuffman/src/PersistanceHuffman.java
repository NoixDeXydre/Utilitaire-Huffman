/*
 * PersistanceHuffman.java                                          05/24
 * IUT de Rodez, pas de copyright.
 */
package utilitairehuffman.src;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Gestion de la sauvegarde des fichiers compréssés et des arbres de 
 * Huffman.
 * @author TD 2 Groupe 4 : Cylian Poupin
 */
public class PersistanceHuffman {
    
    /**
     * Sauvegarde une chaine de caractères dans un fichier texte.
     * @param donnees Les données à sauvegarder
     * @param destination Chemin ou sera sauvegardé le fichier
     * @throws IOException Si une erreur s'est produite durant 
     *                     l'écriture du fichier
     */
    public static void ecrireDonnees(String donnees, String destination)    // FIXME ne marche pas (boucle infinie)
                       throws IOException {

        FileWriter persistance = new FileWriter(destination);

        persistance.write(donnees);

        persistance.close();
    }
}