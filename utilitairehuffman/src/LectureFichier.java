/**
 * LectureFichier.java         24/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
import java.nio.charset.Charset;

/**
 * Offre des méthodes utiles afin de lire des fichiers textes.
 * 
 * @author TD 2 Groupe 4 Noa M'Tima Lesniak, Tom Le Beuze
 */
public class LectureFichier {
    
	// TODO faire en sorte que ce soit l'appelant qui précise l'encodage
	
	/** Encodage supporté par l'arbre */
    public final static String ENCODAGE_TEXTE = "UTF-8";
    
    /**
     * <p>
     * Retourne un liseur pouvant lire un fichier caractère par caractère
     * dans l'encodage UTF-8.
     * 
     * <p>
     * Les données que donne la liseuse sont sous la forme de bytecode,
     * il faut donc effectuer du casting pour bien lire les données.
     * @see java.io.BufferedReader
     * 
     * @param fichierTexte
     * @return le liseur
     * @throws FileNotFoundException @see java.lang.FileNotFoundException
     */
    public static BufferedReader getLiseurChar(File fichierTexte) 
    			   throws FileNotFoundException {
    	
    	return new BufferedReader(new InputStreamReader
    							 (new FileInputStream(fichierTexte),
    							      Charset.forName(ENCODAGE_TEXTE)));
    }
    
    /**
     * Calcule la longueur d'un texte caractère par caractère.
     * 
     * @param fichierTexte
     * @return le nombre de caractère au total
     * @throws IOException @see java.lang.IOException
     */
    public static long getLongueurTexte(File fichierTexte) throws IOException {
    	
    	long nombreCaracteres = 0l;
    	BufferedReader curseurTexte = getLiseurChar(fichierTexte);
    	while (curseurTexte.read() != -1) {
    		nombreCaracteres++;
    	}
    	
    	curseurTexte.close();
    	return nombreCaracteres;
    }
 }
