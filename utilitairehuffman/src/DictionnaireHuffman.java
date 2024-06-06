/**
 * DictionnaireHuffman.java         24/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import static utilitairehuffman.src.LectureFichier.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// Les LinkedHashMaps ne s'arrangent pas automatiquement !
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Composant utilitaire à {@link utilitairehuffman.src.ArbreHuffman}
 * permettant de manipuler des dictionnaires.
 * 
 * @author TD 2 Groupe 4 Noa M'Tima Lesniak, Tom Le Beuze
 */
public class DictionnaireHuffman {

    /**
     * <p>
     * Donne un dictionnaire avec en clé des lettres 
     * et en valeur des fréquences associées à partir d'un fichier 
     * texte.
     * 
     * <p>
     * Avant d'être retourné, le dictionnaire est trié par ordre 
     * croissant.
     * 
     * <p>
     * On peut calculer la fréquence d'un caractère 
     * à l'aide de la méthode itérative suivante :
     * 
     * <p>
     * u0 = 0;
     * </p>
     * <p>
     * un + 1 = un + 1 / nbrCaractereTotal;
     * </p>
     * Où nbrCaractereTotal est le nombre de caractère total dans un
     * texte.
     * 
     * @param cheminTexte chemin du fichier à partir 
     *                    duquel le dictionnaire sera créé.
     * @return le dictionnaire des lettres et des fréquences
     * @throws IOException @see java.lang.IOException
     */
    public static LinkedHashMap<Character, Double> 
                  getDictLettresFrequences(String cheminTexte) 
            throws IOException {

        LinkedHashMap<Character, Double> lettreFrequence 
        = new LinkedHashMap<>();

        char lettreAnalyse;
        int tampon; // Récupère la valeur binaire du caractère

        // Longueur totale
        long nombreCaracteresTexte = getLongueurTexte(cheminTexte); 

        BufferedReader curseurTexte = getLiseurChar(cheminTexte);
        while ((tampon = curseurTexte.read()) != -1) {

            // Récupère la lettre en binaire et la converti en char
            lettreAnalyse = (char) tampon;
            lettreFrequence.putIfAbsent(lettreAnalyse, .0);

            // Calcul de la fréquence
            lettreFrequence.put(lettreAnalyse, 
                    lettreFrequence.get(lettreAnalyse) 
                    + (double) 1 / nombreCaracteresTexte);
        }

        curseurTexte.close();
        return trierDictionnaireParValeur(lettreFrequence); 
    }

    /**
     * <p>
     * À partir d'un fichier arbre de Huffman 
     * généré par {@link utilitairehuffman.src.ArbreHuffman},
     * rend un dictionnaire des caractères existants en tant que clé,
     * et le code compressé en tant que valeur.
     * 
     * <p>
     * Cela est utile pour la compression car chaque lettre détecté 
     * en clé pourra être remplacé par son code Huffman.
     * 
     * @see getDictDecompression
     * 
     * @param cheminArbreHuffman chemin du fichier Huffman indiqué
     * @return le dictionnaire fait pour la compression
     * @throws FileNotFoundException 
     */
    public static LinkedHashMap<Character, String> 
                  getDictCompression(String cheminArbreHuffman)
            throws FileNotFoundException {
        
        /*
         * La méthode dépend grandement de la syntaxe des arbres,
         * l'un dépend donc de l'autre !!!
         */
        
        LinkedHashMap<Character, String> dictCompression 
        = new LinkedHashMap<>();
        Scanner analyseur = getLiseurString(cheminArbreHuffman);
        
        String texteAnalyse;

        int indexCode,
            indexSymbole,
            indexSymboleFin;
        
        String valeur;
        char cle;
        while (analyseur.hasNextLine()) {
            
            texteAnalyse = analyseur.nextLine();

            // Recherche des données dans la ligne
            indexCode = texteAnalyse.indexOf("codehuffman =") + 14;
            indexSymbole = texteAnalyse.indexOf("symbole =") + 10;
            indexSymboleFin = texteAnalyse.length();
            
            // Evite un dépassement dans le String
            if (indexSymbole - 10 != -1 && indexCode - 14 != -1) {
                
                // Vérification si la clé existe à l'index prévu (\n)
                cle = indexSymbole < indexSymboleFin 
                         ? texteAnalyse.charAt(indexSymbole) : '\n'; 
                valeur = texteAnalyse.substring
                        (indexCode, texteAnalyse.indexOf(" ; encode",
                                                         indexCode));

                dictCompression.put(cle, valeur);
            }
        }
        
        analyseur.close();
        return dictCompression;
    }
    
    /**
     * <p>
     * À partir d'un fichier arbre de Huffman 
     * généré par {@link utilitairehuffman.src.ArbreHuffman},
     * rend un dictionnaire des codes compressés 
     * de chaque caractère en tant que clé,
     * et le caractère UTF-8 en valeur.
     * 
     * * <p>
     * Cela est utile pour la decompression en faisant 
     * une analyse octet par octet d'un fichier.
     * 
     * @see getDictCompression
     * 
     * @param cheminArbreHuffman chemin du fichier Huffman indiqué
     * @return dictionnaire pour la décompression
     * @throws FileNotFoundException
     */
    public static LinkedHashMap<String, Character> 
                  getDictDecompression(String cheminArbreHuffman)
           throws FileNotFoundException {

        LinkedHashMap<String, Character> dictDecompression 
        = new LinkedHashMap<>();
        Scanner analyseur = getLiseurString(cheminArbreHuffman);
        
        String texteAnalyse;

        int indexCode,
            indexSymbole,
            indexSymboleFin;
        
        char valeur;
        String cle;
        while (analyseur.hasNextLine()) {
            
            texteAnalyse = analyseur.nextLine();
            
            indexCode = texteAnalyse.indexOf("codehuffman =") + 14;
            indexSymbole = texteAnalyse.indexOf("symbole =") + 10;
            indexSymboleFin = texteAnalyse.length();
            
            if (indexSymbole - 10 != -1 && indexCode - 14 != -1) {
                
                // Vérification si la clé existe à l'index prévu (\n)
                valeur = indexSymbole < indexSymboleFin 
                         ? texteAnalyse.charAt(indexSymbole) : '\n'; 
                cle = texteAnalyse.substring
                        (indexCode, texteAnalyse.indexOf(" ; encode",
                                                         indexCode));

                dictDecompression.put(cle, valeur);
            }
        }
        
        analyseur.close();
        return dictDecompression;
    }

    /**
     * Tri du dictionnaire par rapport aux valeurs de façon croissante.
     * 
     * @param dictionnaire
     * @return le dictionnaire trié
     */
    private static LinkedHashMap<Character, Double> trierDictionnaireParValeur
                  (LinkedHashMap<Character, Double> dictionnaire) {

        LinkedHashMap<Character, Double> dictionnaireTrie 
        = new LinkedHashMap<>();

        // Tri des valeurs en utilisant les fonctions lambdas et stream()
        dictionnaire.entrySet().stream() // Sépare les clés et les valeurs
        .sorted(Map.Entry.comparingByValue())

        // Réecrit les données dans un autre dictionnaire
        .forEach(entry -> dictionnaireTrie.put 
                (entry.getKey(), entry.getValue()));

        return dictionnaireTrie;
    }
}
