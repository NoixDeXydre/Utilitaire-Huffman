/*
 * Gestionnaire.java                                                05/2024
 * IUT de Rodez, pas de copyright.
 */

package iut.info1.codagehuffman.gestionnaire;

/**
 * Gestion de l'entrée utilisateur pour le fonctionnement de 
 * l'algorithme de compression.
 * @author Noa M'TIMA LESNIAK, Cylian POUPIN, Adrien VIGUE, 
 *         Tom LE BEUZE
 */
public class Gestionnaire {

    /**
     * Interface utilisateur en ligne de commande.
     * @param args <p>Arguments selon la syntaxe :</p>
     *             
     * <pre>
     *  $> encode <fichier> output <fichierCodé> <arbreHuffman>
     * Compresse le fichier et copie le nom donné.
     * Génère l’arbre Huffman avec le nom spécifié.
     * 
     *  $> encode <fichier> output <fichierCodé> abr <arbreHuffman>
     * Compresse le fichier d’après un arbre de Huffman et copie le 
     * nom donné.
     * 
     *  $> decode <fichier> abr <arbreHuffman> output <fichierDécodé>
     * Décompresse le fichier à l’aide d’un arbre de Huffman.
     * 
     *  $> make-abr <fichier> output <arbreHuffman>
     * Construit un arbre de Huffman en se basant sur le fichier.
     * 
     *  $> help
     * Donne les commandes disponibles pour l’utilisateur.
     * (affiche cette documentation)
     * </pre>
     * 
     * <p>
     * Note : "output" et "abr" sont optionnels, mais recommandés 
     * pour éviter les erreurs.
     * Les utilisateurs expérimentés pourront se passer de ces mots 
     * clés, le programme assumera que les chemins donnés 
     * correspondent à la syntaxe correcte de la commande. 
     * </p>
     * 
     * <h4>ATTENTION :</h4> 
     * <p>
     * Si un fichier de destination a le même nom qu'un
     * fichier existant, ce dernier sera écrasé sans confirmation !
     * </p>
     */
    public static void main(String[] args) {
        
        final String DOCUMENTATION = 
                """
                  $> encode <fichier> output <fichierCodé> <arbreHuffman>
                 Compresse le fichier et copie le nom donné.
                 Génère l’arbre Huffman avec le nom spécifié.
                 
                  $> encode <fichier> output <fichierCodé> abr <arbreHuffman>
                 Compresse le fichier d’après un arbre de Huffman et copie le 
                 nom donné.
                
                  $> decode <fichier> abr <arbreHuffman> output <fichierDécodé>
                 Décompresse le fichier à l’aide d’un arbre de Huffman.
                 
                  $> make-abr <fichier> output <arbreHuffman>
                 Construit un arbre de Huffman en se basant sur le fichier.
                 
                  $> help
                 Affiche ce message
                
                
                "output" et "abr" sont optionnels.
                /!/ Les fichiers destinations écraseront automatiquement un fichier
                /!/ de même nom existant.
                
                Plus d'informations dans la JavaDoc.
                """;
        
        final String SYNTAXE_ERREUR =
                """
                Erreur de syntaxe, au moins un des arguments est incorrect.
                Pour obtenir une liste des commandes dispnibles ainsi que leur
                syntaxe, tapez "help".
                """;
        
        
        final String HABILLAGE_CONSOLE_EN_TETE =
                """
                _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
                |        Programme de compression via arbre de Huffman        |
                |                VERSION 0.1 PREPRODUCTION DEMO               |
                |-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|
                
                Tapez "help" en argument pour une liste des commandes 
                disponibles.
                """;
        
        final String SEPARATEUR_CONSOLE =
                """
                ----------------------------------------------------------------
                
                """;
        
        final String ARRET_PROGRAMME_MESSAGE = 
                """
                ---
                Arrêt du programme.
                
                Ce rogramme a été réalisé par : 
                Noa M'TIMA LESNIAK, Cylian POUPIN, Adrien VIGUE, Tom LE BEUZE
                """;
        
        // TODO messages d'erreurs (à voir en fonction en fonction du framework de gestion de fichiers) (Indiquer si cela conserne le fichier ou l'arbre) :
        // Impossible de lire
        // Impossible d'écrire
        // La source n'existe pas (mauvais nom/chemin)
        // La destination n'existe pas (chemin)
        // La destination n'est pas valide (caractères non autorisés dans le nom du fichier)
        
        
        
        // TODO check d'intégrité de la commande utilisateur
        
        /* TODO skipper les "output" et "abr" du tableau d'arguments
           lors de la lecture de la commande utilisateur */
        
        System.out.println(HABILLAGE_CONSOLE_EN_TETE);
    }
}
