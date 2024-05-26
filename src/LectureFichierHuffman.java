/**
 * LectureFichierHuffman.java         24/05/2024
 * IUT de Rodez, pas de copyright
 */

 package utilitairehuffman.src;

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.InputStreamReader;
 
 import java.nio.charset.Charset;

 public class LectureFichierHuffman {
     
    /** Encodage supporté par l'arbre */
    public final static String ENCODAGE_TEXTE = "UTF-8";
    
    /**
     * Retourne un liseur pouvant lire un fichier caractère par caractère
     * dans l'encodage UTF-8.
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
 }
