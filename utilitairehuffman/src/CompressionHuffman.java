/**
 * CompressionHuffman.java       28/05/2024
 * IUT de Rodez, pas de copyright
 */
package utilitairehuffman.src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Compresse un fichier selon un arbre de huffman donné puis créé un
 * nouveau fichier dans lequel on écrit le texte en version compressé
 * puis on supprime le fichier original non compressé pour gagner de
 * la place
 * 
 * @author TD2 groupe 4 Adrien Vigué, Cylian Poupin
 */
public class CompressionHuffman {

	/**
	 * TODO définir la fonction du constructeur
	 * @param fichierACompresser le chemin vers le fichier à
	 *		  compresser
	 * @param le chemin vers l'arbre de Huffman qui servira pour le
	 *		  codage
	 * @throws IOException @see java.lang.IOException
	 * 		   FileNotFoundException @see java.lang.IOException
	 */
	public CompressionHuffman(String fichierACompresser, String arbre,
	                          String fichierCompresse) throws IOException {
		String donnees;
		
		File fichierCompression = new File(fichierACompresser);
		Scanner liseur = new Scanner(fichierCompression);
		donnees = liseur.nextLine();
		PersistanceHuffman.ecrireDonnees(donnees, fichierCompresse);
	}
}
