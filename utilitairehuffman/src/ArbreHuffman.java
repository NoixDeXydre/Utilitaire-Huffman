/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import static utilitairehuffman.src.DictionnaireHuffman
.getDictLettresFrequences;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * <p>
 * ArbreHuffman est un composant permettant de créer
 * un arbre binaire étant utile à coder et décoder 
 * un ensemble de données.
 * 
 * <p>
 * Contrairement à un arbre classique, il se créer à partir de ses feuilles,
 * composant provenant de {@link utilitairehuffman.src.NoeudHuffman}
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
     * Création d'un arbre de Huffman à partir d'un fichier texte.
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
    		setConnexionsNoeuds();
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
     * <p>
     * Met en place les noeuds parents et leurs connexions 
     * dans l'arbre de Huffman.
     * 
     * <p>
     * Fonctionnement de l'algorithme :
     * 
     * <ul>
     * <li> Initialisation des noeuds feuilles triés 
     * 		par fréquences dans un tableau ainsi qu'un 
     * 		autre tableau contenant des noeuds en tant que tampon.
     * </li>
     * <li> DEBUT DE LA BOUCLE </li>
     * <li> 	Y a t-il le noeud[i] ET un noeud tampon qui est plus 
     * 			petit ou égal aux noeuds noeud[i + n] ?
     * </li>
     * <li> 	Oui : on créer un parent à partir 
     * 			des deux noeuds sélectionnés,
     * 		    et on place ce parent dans le tampon
     * </li>
     * <li> 	Non : on place le noeud[i] dans le tampon 
     *      	en attendant qu'il créer un parent.
     * </li>
     * <li> FIN lorsqu'un noeud possède une fréquence de 1. </li>
     * </ul>
     */
    private void setConnexionsNoeuds() {

        int i = 0;
        int o = noeudsFeuilles.length;
        while (i + 1 < o) {
        	NoeudHuffman gauche = noeudsTampon[i++];
            NoeudHuffman droite = noeudsTampon[i++];

            noeudsTampon[o++] = new NoeudHuffman(gauche, droite);
        } // TODO explications
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
    		noeudsFeuilles[i++]
    		= new NoeudHuffman(k, dictionnaireLettresFrequences.get(k));
    	}
    	
    	/* 
    	 * Création des noeuds tampon.
    	 * 
    	 * Il a été vu après plusieurs itérations manuelles que 
    	 * le nombre maximal de noeuds pouvant être créé est de 2n - 1.
    	 * 
    	 * Si n est égal à 1 ou 0, alors le nombre maximal est de n.
    	 */
    	if (nbrLettres > 1) {
    		noeudsTampon = new NoeudHuffman[2 * nbrLettres - 1];
    	} else {
    		noeudsTampon = new NoeudHuffman[nbrLettres];
    	}
    	
    	// Placement des feuilles dans le tampon
    	for (i = 0; i < noeudsFeuilles.length ; i++) {
            noeudsTampon[i] = noeudsFeuilles[i];
        }
    	
    }
}
