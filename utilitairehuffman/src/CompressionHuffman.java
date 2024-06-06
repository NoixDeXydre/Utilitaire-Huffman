/**
 * CompressionHuffman.java       28/05/2024
 * IUT de Rodez, pas de copyright
 */
package utilitairehuffman.src;

import static utilitairehuffman.src.LectureFichier.getLiseurChar;
//import static utilitairehuffman.src.PersistanceHuffman.ecrireDonnees;
import static utilitairehuffman.src.DictionnaireHuffman.getDictCompression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Compresse un fichier selon un arbre de huffman donné puis créé un
 * nouveau fichier dans lequel on écrit le texte en version compressé
 * puis on supprime le fichier original non compressé pour gagner de
 * la place
 * 
 * @author TD2 groupe 4 Adrien Vigué, Cylian Poupin, Noa M'tima Lesniak
 */
public class CompressionHuffman {
    
    private static final String ERREUR_ARBRE 
    = "L'arbre de Huffman est invalide car une lettre n'existe pas";
    
    /**
     * Compresse un fichier selon un 
     * {@link utilitairehuffman.src.ArbreHuffman}
     * 
     * @param cheminFichierACompresser le chemin vers le fichier à
     *                                 compresser
     * @param cheminArbreHuffman le chemin vers l'arbre de Huffman
     *                           qui servira pour le codage
     * @param cheminFichierCompresse le chemin vers l'endroit où 
     *                               sera placé le fichier compressé
     * @throws IOException @see java.lang.IOException
     * @throws FileNotFoundException @see java.lang.FileNotFoundException
     */
    public static void compresserFichier(String cheminFichierACompresser,
                                         String cheminArbreHuffman,
                                         String cheminFichierCompresse) 
           throws IOException {
        
        // TODO séparer en méthodes
        
        LinkedHashMap<Character, String> dictCompression 
        = getDictCompression(cheminArbreHuffman);
        
        // try avec ressources
        try (BufferedReader curseurFichierACompresser 
                = getLiseurChar(cheminFichierACompresser);
                FileOutputStream sortieFichier 
                = new FileOutputStream(cheminFichierCompresse)) {
            
            
            StringBuilder tamponOctets = new StringBuilder();
            
            String chaineOctets;
            int tampon;
            char lettreLue;
            while ((tampon = curseurFichierACompresser.read()) != -1) {
                
                lettreLue = (char) tampon;
                String codeHuffman = dictCompression.get(lettreLue);
                if (codeHuffman != null) {
                    
                    tamponOctets.append(codeHuffman); 
                    while (tamponOctets.length() >= 8) {
                        
                        chaineOctets = tamponOctets.substring(0, 8);
                        tamponOctets.delete(0, 8);
                        sortieFichier.write(Integer.parseInt(chaineOctets, 2));
                    }
                    
                } else {
                    throw new IOException(ERREUR_ARBRE);
                }
            }
            
            // Ecrit les bits restants
            if (tamponOctets.length() > 0) {
                while (tamponOctets.length() < 8) {
                    tamponOctets.append('0'); // Rajoute les 0 manquants
                }
                sortieFichier.write
                (Integer.parseInt(tamponOctets.toString(), 2));
            }
        }
    }
}
