/**
 * DictionnaireHuffman.java         24/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.BufferedReader;
import java.io.IOException;
 
import static utilitairehuffman.src.LectureFichier.getLiseurChar;
import static utilitairehuffman.src.LectureFichier.getLongueurTexte;
 
// Les LinkedHashMaps ne s'arrangent pas automatiquement !
import java.util.LinkedHashMap;
 
import java.util.Map;

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
     * et en valeur des fréquences associées à partir d'un fichier texte.
     * 
     * <p>
     * Avant d'être retourné, le dictionnaire est trié par ordre croissant.
     * 
     * <p>
     * On peut calculer la fréquence d'un caractère 
     * à l'aide de la méthode itérative suivante :
     * 
     * <p> u0 = 0;</p>
     * <p> un + 1 = un + 1 / nbrCaractereTotal;</p>
     * Où nbrCaractereTotal est le nombre de caractère total dans un texte.
     * 
     * @param fichierTexte
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
