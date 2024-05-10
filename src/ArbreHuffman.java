/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

//import utilitairehuffman.src.NoeudHuffman;

//TODO meilleure description du composant

/**
 * ArbreHuffman est un composant permettant de créer
 * un arbre binaire étant utile à coder et décoder 
 * un ensemble de données.
 * 
 * Contrairement à un arbre classique, il se créer à partir de ses feuilles.
 * 
 * TODO changer auteurs
 * @author TD 2 Groupe 4
 */
public class ArbreHuffman {
	
	// FIXME Java gère par défaut du UTF-16, et pas du UTF-8
	
	/** 
	 * Dictionnaire ayant pour clé un caractère, et pour valeur sa fréquence.
	 * Les valeurs sont triés dans l'ordre croissant 
	 * pour bien faire fonctionner l'algorithme 
	 */
	private HashMap<Character, Double> dictionnaireLettresFrequences;
	
    /**
     * Création d'un arbre de Huffman à partir d'un fichier texte.
     * 
     * @param fichierTexte le chemin vers le fichier texte.
     * @throws IOException 
     */
    public ArbreHuffman(String cheminFichier) throws IOException {
    	
    	File fichierTexte = new File(cheminFichier);
    	
    	// ======== Construction progressive de l'arbre ========
    	
    	/* Récupération des lettres et de leur fréquence dans le texte 
    	 * à partir d'un dictionnaire.
    	 */
    	dictionnaireLettresFrequences = getDictLettreFrequence(fichierTexte);
    }
    
    /**
     * @return le dictionnaire des lettres et des fréquences
     */
    public HashMap<Character, Double> getDictLettreFrequence() {
    	return dictionnaireLettresFrequences;
    }
    
    /**
     * Donne un dictionnaire des lettres et des fréquences associées 
     * à partir d'un fichier texte.
     * 
     * Avant d'être retourné, le dictionnaire est trié par ordre croissant.
     * 
     * On peut calculer la fréquence d'un caractère 
     * à l'aide de la méthode itérative suivante :
     * 
     * <p> u0 = 0;</p>
     * <p> un + 1 = un + 1 / nbrCaractereTotal;</p>
     * Où nbrCaractereTotal est le nombre de caractère total dans un texte.
     * 
     * @param curseurTexte
     * @return le dictionnaire des lettres et des fréquences
     * @throws IOException 
     */
    private static HashMap<Character, Double> 
    			   getDictLettreFrequence(File cheminTexte) throws IOException {
    	
    	HashMap<Character, Double> lettreFrequence 
    	= new HashMap<Character, Double>();
    	
    	char lettreAnalyse;
    	Scanner curseurTexte = new Scanner(cheminTexte);
    	long nombreCaracteresTexte = getLongueurTexte(cheminTexte);
    	while (curseurTexte.hasNext()) {
    		
    		lettreAnalyse = curseurTexte.next().charAt(0);
    		lettreFrequence.putIfAbsent(lettreAnalyse, .0);
    		
    		// Calcul de la fréquence
    		lettreFrequence.replace(lettreAnalyse, 
    					   (double) lettreFrequence.get(lettreAnalyse) 
    							    + 1 / nombreCaracteresTexte);
    	}
    	
    	curseurTexte.close();
    	return trierDictionnaire(lettreFrequence);
    }
    
    /**
     * Calcule la longueur d'un texte caractère par caractère.
     * 
     * @param cheminTexte
     * @return le nombre de caractère au total
     * @throws IOException
     */
    private static long getLongueurTexte(File cheminTexte) throws IOException {
    	
    	long nombreCaracteres = 0l;
    	Scanner curseurTexte = new Scanner(cheminTexte);
    	while (curseurTexte.hasNext()) {
    		
    		curseurTexte.next().charAt(0);
    		nombreCaracteres++;
    	}
    	
    	curseurTexte.close();
    	return nombreCaracteres;
    }
    
    /**
     * Tri du dictionnaire par rapport aux valeurs de façon croissante.
     * 
     * @param dictionnaire
     * @return le dictionnaire trié
     */
    private static HashMap<Character, Double> trierDictionnaire
                  (HashMap<Character, Double> dictionnaire) {
    	
    	// Tri des valeurs en utilisant les fonctions lambdas et stream()
    	dictionnaire.entrySet()
    	  			.stream()
    	  			.sorted(HashMap.Entry.comparingByValue());
    	  
    	return dictionnaire;
    }
    
    // TODO faire le reste en suivant le diagramme des classes
}
