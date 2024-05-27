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
	
	/** 
	 * <p>
	 * Noeuds permettant de contruire le noeud de Huffman.
	 * 
	 * <p>
	 * En d'autres termes, il s'agit d'un tableau 
	 * qui contient les noeuds feuilles ou non à fusionner.
	 */
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

    	dictionnaireLettresFrequences 
    	= getDictLettresFrequences(cheminFichier);
    	
    	// Prépare les noeuds à utiliser
    	setInitNoeuds();
    	
    	// Sinon, cela veut dire que l'arbre est vide
    	if (noeudsFeuilles.length != 0) {
    		
    		int i = 1;
        	int o = 0;
        	noeudsTampon[0] = noeudsFeuilles[0];
        	
        	// Condition d'arrêt : la racine a une fréquence de 1
        	while (noeudsTampon[o].getFreq() != 1.0) {
        		// TODO boucle
        	}
    	}
    }
    
    /**
     * Getter de dictionnaireLettresFrequences
     * @return le dictionnaire des lettres et des fréquences
     */
    public LinkedHashMap<Character, Double> getDictHuffman() {
    	return dictionnaireLettresFrequences;
    }
    
    /**
     * <p>
     * Représente l'arbre de Huffman sous forme d'un texte multilignes
     * avec le code encodé, décodé et le symbole char de chaque noeud.
     * @see FORMATAGE_ARBRE_HUFFMAN
     * 
     * <p>
     * Chaque code de Huffman doit être déterminé en 
     * remontant les noeuds feuilles de l'arbre. On remarquera ainsi que
     * remonter depuis la gauche 
     * ajoute “1” et remonter depuis la droite ajoute “0"
     * 
     * @return représentation de l'arbre de Huffman
     */
    @Override 
    public String toString() {
    	
    	// TODO méthode
    	// stub
    	return "";
    }
    
    /**
     * Initialise les noeuds feuilles et les noeuds tampon.
     */
    private void setInitNoeuds() {
    	
    	int nbrLettres = dictionnaireLettresFrequences.size();
    	
    	// Création des noeuds feuilles
    	int i = 0;
    	noeudsFeuilles = new NoeudHuffman[nbrLettres];
    	for (char k : dictionnaireLettresFrequences.keySet()) {
    		noeudsFeuilles[i++] // À vérifier
    		= new NoeudHuffman(k, dictionnaireLettresFrequences.get(k));
    	}
    	
    	/* 
    	 * Création des noeuds tampon.
    	 * 
    	 * Il a été vu après plusieurs itérations manuelles que 
    	 * le nombre maximal de noeuds pouvant être créé est de n² - 1.
    	 * 
    	 * Si n est égal à 1 ou 0, alors le nombre maximal est de n.
    	 */
    	if (nbrLettres > 1) { // À vérifier
    		noeudsTampon = new NoeudHuffman[nbrLettres * nbrLettres - 1];
    	} else {
    		noeudsTampon = new NoeudHuffman[nbrLettres];
    	}
    	
    }
}
