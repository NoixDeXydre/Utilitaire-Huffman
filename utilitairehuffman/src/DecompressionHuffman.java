package utilitairehuffman.src;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
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

    private LinkedHashMap<String, Character> dictDecompression;

    /**
     * @param fichierADecompresser le chemin vers le fichier à décompresser
     * @param arbre le chemin vers l'arbre de Huffman qui servira pour le décodage
     * @throws IOException @see java.lang.IOException
     */
    public DecompressionHuffman(String fichierADecompresser, String arbre) throws IOException {
        // Charger le dictionnaire de décompression
        this.dictDecompression = DictionnaireHuffman.getDictDecompression(arbre);

        // Décompresser le fichier
        decompresser(fichierADecompresser);
    }

    private void decompresser(String fichierADecompresser) throws IOException {
        File fichierDecompression = new File(fichierADecompresser);
        Scanner liseur = new Scanner(fichierDecompression);

        StringBuilder tampon = new StringBuilder();
        StringBuilder sortie = new StringBuilder();

        while (liseur.hasNextLine()) {
            String ligne = liseur.nextLine();
            for (char bit : ligne.toCharArray()) {
                tampon.append(bit);
                if (dictDecompression.containsKey(tampon.toString())) {
                    sortie.append(dictDecompression.get(tampon.toString()));
                    tampon.setLength(0);
                }
            }
        }

        liseur.close();
        PersistanceHuffman.ecrireDonnees(sortie.toString(), fichierADecompresser);
    }
}