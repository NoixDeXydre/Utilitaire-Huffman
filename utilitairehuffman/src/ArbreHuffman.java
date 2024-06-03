/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import java.io.IOException;

import static utilitairehuffman.src.DictionnaireHuffman
								   .getDictLettresFrequences;

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
     * Vérifie à la fois si la fréquence 
     * d'un noeud feuille et d'un noeud du tampon
     * est inférieur ou égal à tout les noeuds feuilles actuelles.
     * 
     * @param noeudFeuille un des noeuds feuilles de l'arbre Huffman
     * @param noeudTampon un des noeuds tampon de l'arbre Huffman
     * @return true si les fréquences sont plus petites, sinon false
     */
    private boolean sontFrequencesPlusPetites(NoeudHuffman noeudFeuille,
    										  NoeudHuffman noeudTampon,
    										  int indexCommencementFeuille) {
    	
    	int i = indexCommencementFeuille;
    	boolean estPlusPetit = true;
    	while (estPlusPetit && i < noeudsFeuilles.length - 1) {
    		estPlusPetit = noeudFeuille.getFreq() 
    					<= noeudsFeuilles[i].getFreq() 
    					&& noeudTampon.getFreq() 
    					<= noeudsFeuilles[i].getFreq();
    		i++;
    	}

    	return estPlusPetit;
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
    	
    	int i = 1;
    	int o = 0;
    	noeudsTampon[0] = noeudsFeuilles[0];
    	
    	// Condition d'arrêt : la racine a une fréquence de 1
    	while (noeudsTampon[o].getFreq() != 1.0) {
    		
    		//System.out.println(o);
    		if (noeudsFeuilles[i] != null 
    		 && sontFrequencesPlusPetites(noeudsFeuilles[i],
    									  noeudsTampon[o], i + 1)) {
    			noeudsTampon[o + 1] 
    			= new NoeudHuffman(noeudsFeuilles[i], noeudsTampon[o]);
    			o++; // Place le noeud parent dans le tampon
    		} else {
    			/* 
        		 * Place un noeud feuille dans le tampon
        		 * dans le prochain endroit libre.
        		 */
        		for (int v = 0 ; v < noeudsTampon.length ; v++) {
        			noeudsTampon[v] = noeudsTampon[v] == null 
        							? noeudsFeuilles[i] : noeudsTampon[v];
        		}
    		}
    		
    		i++;
    	}
    }
    
    /**
     * Initialise les noeuds feuilles et les noeuds tampon.
     */
    private void setInitNoeuds() {
    	
    	int nbrLettres = dictionnaireLettresFrequences.size();
    	
    	// Création des noeuds feuilles
    	int i = 0;
    	noeudsFeuilles = new NoeudHuffman[nbrLettres + 1];
    	for (char k : dictionnaireLettresFrequences.keySet()) {
    		noeudsFeuilles[i++] // À vérifier
    		= new NoeudHuffman(k, dictionnaireLettresFrequences.get(k));
    	}
    	
    	/* 
    	 * Création des noeuds tampon.
    	 * 
    	 * Il a été vu après plusieurs itérations manuelles que 
    	 * le nombre maximal de noeuds pouvant être créé est de n² - 1.
    	 * 
    	 * Si n est égal à 1 ou 0, alors le nombre maximal est de n.
    	 */
    	if (nbrLettres > 1) {
    		noeudsTampon = new NoeudHuffman[nbrLettres * nbrLettres - 1];
    	} else {
    		noeudsTampon = new NoeudHuffman[nbrLettres];
    	}
    	
    }
}