/*
 * Gestionnaire.java                                                05/2024
 * IUT de Rodez, pas de copyright.
 */

package iut.info1.codagehuffman.gestionnaire;

import java.io.IOException;

import iut.info1.codagehuffman.src.ArbreHuffman;

/**
 * Gestion de l'entrée utilisateur pour le fonctionnement de 
 * l'algorithme de compression.
 * @author TD 2 Groupe 4 Cylian POUPIN, Adrien VIGUE, Tom LE BEUZE
 */
public class Gestionnaire {

    /**
     * Interface utilisateur en ligne de commande.
     * @param args <p>Arguments selon la syntaxe :</p>
     *             
     * <pre>
     *  $&gt; encode &lt;fichier&gt; output &lt;fichierCodé&gt; abr &lt;arbreHuffman&gt;
     * Compresse le fichier grâce à l’arbre Huffman spécifié.
     * 
     *  $&gt; encode &lt;fichier&gt; &lt;fichierCodé&gt; &lt;arbreHuffman&gt;
     * Même commande que la précédente mais sans les commandes facultatives 
     * (voir note)
     * 
     *  $&gt; decode &lt;fichier&gt; abr &lt;arbreHuffman&gt; output &lt;fichierDécodé&gt;
     * Décompresse le fichier à l’aide d’un arbre de Huffman.
     * 
     *  $&gt; make-abr &lt;fichier&gt; output &lt;arbreHuffman&gt;
     * Construis un arbre de Huffman en se basant sur le fichier donné. 
     * L’arbre doit avoir été généré avant de pouvoir compresser un fichier.
     * 
     *  $&gt; help
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
                  $> encode <fichier> output <fichierCodé> abr <arbreHuffman>
                 Compresse le fichier grâce à l\'arbre Huffman spécifié.
                 
                  $> encode <fichier> <fichierCodé> <arbreHuffman>
                 Même commande que la précédente mais sans les commandes
                 facultatives (voir plus bas)
                
                  $> decode <fichier> abr <arbreHuffman> output <fichierDécodé>
                 Décompresse le fichier à l’aide d’un arbre de Huffman.
                 
                  $> make-abr <fichier> output <arbreHuffman>
                 Construis un arbre de Huffman en se basant sur le fichier 
                 donné. L’arbre doit avoir été généré avant de pouvoir 
                 compresser un fichier.
                 
                  $> help
                 Affiche ce message.
                
                
                "output" et "abr" sont optionnels.
                /!/ Les fichiers destinations écraseront automatiquement un fichier
                /!/ de même nom existant.
                
                Plus d'informations dans la JavaDoc.
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
        final String SYNTAXE_ERREUR =
                """
                Erreur de syntaxe, au moins un des arguments est incorrect.
                Pour obtenir une liste des commandes dispnibles ainsi que leur
                syntaxe, tapez "help"       
                """;
                

        // Impossible de lire
        
        // La source n'existe pas (mauvais nom/chemin)
        final String LECTURE_FICHIER_ERREUR =
                """
                !-!-!!-!-!
                ! Erreur ! : Impossible d'ouvrir le fichier source
                !-!-!!-!-!
                """;
        
        final String LECTURE_ARBRE_ERREUR =
                """
                !-!-!!-!-!
                ! Erreur ! : Impossible d'ouvrir l'arbre
                !-!-!!-!-!
                """;
        
        // Impossible d'écrire
        
        // La destination n'existe pas (chemin)
        final String ECRITURE_FICHIER_ERREUR =
                """
                !-!-!!-!-!
                ! Erreur ! : Impossible d'enregistrer le fichier compressé
                !-!-!!-!-!
                """;
        
        final String ECRITURE_ARBRE_ERREUR =
                """
                !-!-!!-!-!
                ! Erreur ! : Impossible d'ouvrir l'arbre
                !-!-!!-!-!
                """;
        
        
        // La destination n'est pas valide (caractères non autorisés dans le nom du fichier)
        final String NOM_DESTINATION_ERREUR =
                """
                !-!-!!-!-!
                ! Erreur ! : Le nom de la destination est invalide
                !-!-!!-!-!       (caractère(s) non autorisés)
                """;
        
        final String ARGUMENT_MANQUANT_ERREUR = 
                """
                Au moins un argument requis n'est pas présent.
                Arrêt du programme.
                """;
        
        int noArgUtile;
        
        // Chemins d'accès
        String fichierSource;
        String fichierDestination;
        String arbreSource;
        String arbreDestination;
        
        
        /* --------------------------------------------------------*/
        
        // Message de bienvenue
        System.out.println(HABILLAGE_CONSOLE_EN_TETE);
        
        
        // TODO check d'intégrité de la commande utilisateur
        for (int i = 0 ; i < args.length ; i++) {
            System.out.println(args[i]); // DEBUG
        }
        
        try {                                                                   // TODO traitement du deuxièmme argument
            if ("encode".equalsIgnoreCase(args[0])) {
                System.out.println("Demande d'encodage"); // DEBUG
                // TODO faire le lien avec ArbreHuffman()
                fichierSource = args[1];
                System.out.println("pas encore fini"); // stub
            }

            if ("decode".equalsIgnoreCase(args[0])) {
                System.out.println("Demande de décodage"); // DEBUG
                // TODO faire le lien avec ArbreHuffman()
                fichierSource = args[1];
                System.out.println("pas encore fini"); // stub
            }

            if ("make-abr".equalsIgnoreCase(args[0])) {
                System.out.println("Demande de création d'arbre"); // DEBUG
                
                fichierSource = args[1];
                try {
					new ArbreHuffman(fichierSource);
					System.out.println("La création de l'arbre n'a pas été "
							          + "effectuée avec succès !"); // stub
				} catch (IOException e) {
					System.out.println(LECTURE_FICHIER_ERREUR);
				}
            }
            
            if ("help".equalsIgnoreCase(args[0])) {
                System.out.println(DOCUMENTATION);
            }
            
        } catch (ArrayIndexOutOfBoundsException aucunArgument) {
            System.out.println(ARGUMENT_MANQUANT_ERREUR);
        }
    }
}



//// Analyse et lecture des arguments
//        noArgUtile = 0;
//        
//        for (int i = 0 ; i < args.length ; i++) {
//            if ("output".equals(args[i]) || "abr".equals(args[i])) {  // Les arguments "output" et "abr" sont ignorés
//                noArgUtile ++;
//                 System.out.println(noArgUtile); // DEBUG
//            }
//        }