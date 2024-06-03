/**
 * DecompressionHuffman.java    03/06/2024
 * IUT de Rodez, pas de copyright
 */
package utilitairehuffman.src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** 
 * <p>
 * Elle utilise un arbre de Huffman pour décoder les données compressées par 
 * CompressionHuffman et de ArbreHuffman.
 * <p>
 * Elle utilise un arbre de Huffman pour décoder les bits compressés 
 * en caractères UTF-8.
 * 
 * @author TD 2 Groupe 4 Tom Le Beuze
 */
public class DecompressionHuffman {
	
	/**
	 @param fichierACompresser le chemin vers le fichier à decompresser
     * @param arbre  le chemin vers l'arbre de Huffman qui servira pour
     *               le decodage
     * @throws IOException @see java.lang.IOException
	 */
	public DecompressionHuffman(String fichierADecompresser, String arbre) 
			throws IOException {
		
		String donnees;
		
		File fichierDecompression = new File(fichierADecompresser);
		Scanner liseur = new Scanner(fichierDecompression);
		donnees = liseur.nextLine();
		PersistanceHuffman.ecrireDonnees(donnees, fichierADecompresser);
	}
	
}
