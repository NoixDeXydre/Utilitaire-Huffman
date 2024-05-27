/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.IOException;

import utilitairehuffman.src.NoeudHuffman;
import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

import java.util.LinkedHashMap;

/**
 * <p>
 * ArbreHuffman est un composant permettant de créer
 * un arbre binaire étant utile à coder et décoder 
 * un ensemble de données.
 * 
 * <p>
 * Contrairement à un arbre classique, il se créer à partir de ses feuilles.
 * 
 * <p>
 * D'après les spécifications du document, 
 * l'arbre de Huffman ne supporte que la lecture des fichiers UTF-8 !
 * 
 * @author TD 2 Groupe 4 Noa M'Tima Lesniak, Tom Le Beuze
 */
public class ArbreHuffman {
	
	/** Représentation d'un noeud sous forme texte */
	private static final String FORMATAGE_ARBRE_HUFFMAN
	= "codeHuffman = %s ; encode = %s ; symbole = %c";
	
	/** Contient tout les noeuds possédant des lettres */
	private NoeudHuffman[] noeudsFeuilles;
	
	/** Noeuds permettant de contruire le noeud de Huffman */
	private NoeudHuffman[] noeudsTampon;
	
    /** 
     * Dictionnaire ayant pour clé un caractère, et pour valeur sa fréquence.
     * Les valeurs sont triés dans l'ordre croissant 
     * pour bien faire fonctionner l'algorithme.
     */
    private LinkedHashMap<Character, Double> dictionnaireLettresFrequences;
	
    /**
     * Création d'un arbre de Huffman à partir d'un fichier texte en chemin.
     * 
     * @param cheminFichier le chemin vers le fichier texte.
     * @throws IOException @see java.lang.IOException
     */
    public ArbreHuffman(String cheminFichier) throws IOException {    	
    	dictionnaireLettresFrequences = getDictLettresFrequences(cheminFichier);
    }
    
    /**
     * Getter de dictionnaireLettresFrequences
     * @return le dictionnaire des lettres et des fréquences
     */
    public LinkedHashMap<Character, Double> getDictHuffman() {
    	return dictionnaireLettresFrequences;
    }
    
    /**
     * Représente l'arbre de Huffman sous forme d'un texte multilignes
     * avec le code encodé, décodé et le symbole char de chaque noeud.
     * @see FORMATAGE_ARBRE_HUFFMAN
     * 
     * @return représentation de l'arbre de Huffman
     */
    @Override 
    public String toString() {
    	
    	// stub
    	return "";
    }
}
