/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package iut.info1.codagehuffman.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

// Les LinkedHashMaps ne s'arrangent pas automatiquement !
import java.util.LinkedHashMap;

import java.util.Map;

//import utilitairehuffman.src.NoeudHuffman;

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
 * @author TD 2 Groupe 4 Noa M'Tima Lesniak
 */
public class ArbreHuffman {
	
    /** Encodage supporté par l'arbre */
    public final static String ENCODAGE_TEXTE = "UTF-8";
	
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
    	
    	File fichierTexte = new File(cheminFichier);
    	
    	// ======== Construction progressive de l'arbre ========
    	
    	/* Récupération des lettres et de leur fréquence dans le texte 
    	 * à partir d'un dictionnaire.
    	 */
    	dictionnaireLettresFrequences = getDictLettreFrequence(fichierTexte);
    }
    
    /**
     * Getter de dictionnaireLettresFrequences
     * @return le dictionnaire des lettres et des fréquences
     */
    public LinkedHashMap<Character, Double> getDictLettreFrequence() {
    	return dictionnaireLettresFrequences;
    }
    
    /**
     * Retourne un liseur pouvant lire un fichier caractère par caractère
     * dans l'encodage UTF-8.
     * 
     * @param fichierTexte
     * @return le liseur
     * @throws FileNotFoundException @see java.lang.FileNotFoundException
     */
    private static BufferedReader getLiseurChar(File fichierTexte) 
    			   throws FileNotFoundException {
    	
    	return new BufferedReader(new InputStreamReader
    							 (new FileInputStream(fichierTexte),
    							      Charset.forName(ENCODAGE_TEXTE)));
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
     * @param fichierTexte
     * @return le dictionnaire des lettres et des fréquences
     * @throws IOException @see java.lang.IOException
     */
    private static LinkedHashMap<Character, Double> 
    			   getDictLettreFrequence(File fichierTexte) 
    		throws IOException {
    	
    	LinkedHashMap<Character, Double> lettreFrequence 
    	= new LinkedHashMap<>();
    	
    	char lettreAnalyse;
    	int tampon; // récupère la valeur binaire du caractère
    	/* longueur totale */
    	long nombreCaracteresTexte = getLongueurTexte(fichierTexte); 
    	
    	BufferedReader curseurTexte = getLiseurChar(fichierTexte);
    	while ((tampon = curseurTexte.read()) != -1) {
    		// récupère la lettre en binaire et la converti en char
    		lettreAnalyse = (char) tampon;
    		lettreFrequence.putIfAbsent(lettreAnalyse, .0);
    		
    		// Calcul de la fréquence
    		lettreFrequence.put(lettreAnalyse, 
    				 			lettreFrequence.get(lettreAnalyse) 
    					        + (double) 1 / nombreCaracteresTexte);
    	}
    	
    	curseurTexte.close();
    	return trierDictionnaire(lettreFrequence);
    }
    
    /**
     * Calcule la longueur d'un texte caractère par caractère.
     * 
     * @param fichierTexte
     * @return le nombre de caractère au total
     * @throws IOException @see java.lang.IOException
     */
    private static long getLongueurTexte(File fichierTexte) throws IOException {
    	
    	long nombreCaracteres = 0l;
    	BufferedReader curseurTexte = getLiseurChar(fichierTexte);
    	
    	while (curseurTexte.read() != -1) {
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
    private static LinkedHashMap<Character, Double> trierDictionnaire
                  (LinkedHashMap<Character, Double> dictionnaire) {
    	
    	LinkedHashMap<Character, Double> dictionnaireTrie 
    	= new LinkedHashMap<>();
    	
    	// Tri des valeurs en utilisant les fonctions lambdas et stream()
    	dictionnaire.entrySet().stream()
    	  			.sorted(Map.Entry.comparingByValue())
    	  			.forEach(entry -> dictionnaireTrie.put
    	  				    (entry.getKey(), entry.getValue()));
    	  
    	return dictionnaireTrie;
    }
    
    // TODO faire le reste en suivant le diagramme des classes
}
