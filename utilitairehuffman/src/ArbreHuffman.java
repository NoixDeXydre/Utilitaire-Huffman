/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.IOException;

//import utilitairehuffman.src.NoeudHuffman;
import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

// Les LinkedHashMaps ne s'arrangent pas automatiquement !
import java.util.LinkedHashMap;

//TODO meilleure description du composant

/**
 * ArbreHuffman est un composant permettant de créer
 * un arbre binaire étant utile à coder et décoder 
 * un ensemble de données.
 * 
 * Contrairement à un arbre classique, il se créer à partir de ses feuilles.
 * 
 * D'après les spécifications du document, 
 * l'arbre de Huffman ne supporte que la lecture des fichiers UTF-8 !
 * 
 * @author TD 2 Groupe 4 Noa M'Tima Lesniak, Tom Le Beuze
 */
public class ArbreHuffman {
	
    /** 
     * Dictionnaire ayant pour clé un caractère, et pour valeur sa fréquence.
     * Les valeurs sont triés dans l'ordre croissant 
     * pour bien faire fonctionner l'algorithme 
     */
    private LinkedHashMap<Character, Double> dictionnaireLettresFrequences;
	
    /**
     * Création d'un arbre de Huffman à partir d'un fichier texte.
     * 
     * @param cheminFichier le chemin vers le fichier texte.
     * @throws IOException @see java.lang.IOException
     */
    public ArbreHuffman(String cheminFichier) throws IOException {
    	
    	// ======== Construction progressive de l'arbre ========
    	
    	/* 
    	 * Récupération des lettres et de leur fréquence dans le texte 
    	 * à partir d'un dictionnaire.
    	 */
    	dictionnaireLettresFrequences = getDictLettresFrequences(cheminFichier);
    }
    
    /**
     * Getter de dictionnaireLettresFrequences
     * @return le dictionnaire des lettres et des fréquences
     */
    public LinkedHashMap<Character, Double> getDictHuffman() {
    	return dictionnaireLettresFrequences;
    }
    
    // TODO faire le reste en suivant le diagramme des classes
}
