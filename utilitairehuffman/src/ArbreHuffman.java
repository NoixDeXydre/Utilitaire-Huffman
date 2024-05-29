/**
 * ArbreHuffman.java         06/05/2024
 * IUT de Rodez, pas de copyright
 */

package utilitairehuffman.src;

import static utilitairehuffman.src.DictionnaireHuffman
.getDictLettresFrequences;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	private static final String FORMAT_ARBRE_HUFFMAN
	= "codeHuffman = %s ; encode = %s ; symbole = %c%n";
	
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
     * @return représentation de l'arbre de Huffman
     */
    @Override 
    public String toString() {
    	
    	/*
    	 *  Itérer la boucle dans le sens inverse
    	 *  pour trier de façon croissante.
    	 */
    	String representationHuffman = "";
    	for (int i = noeudsFeuilles.length - 1 ; i != -1 ; i--) {
    		representationHuffman 
    		+= construireRepresentationNoeud(noeudsFeuilles[i]);
    	}
    	
    	return representationHuffman;
    }
    
    /**
     * Construit la représentation d'un seul noeud contenu
     * dans le noeud de Huffman.
     * 
     * <p>
     * Un code de Huffman se détermine en remontant 
     * à partir d'un noeud feuille jusqu'à la racine.
     * On remarquera ainsi que remonter depuis la gauche 
     * ajoute “1” et remonter depuis la droite ajoute “0".
     * 
     * @param noeud un noeud appartenant à l'arbre de Huffman
     * @return représentation d'un noeud
     * @throws UnsupportedEncodingException 
     */
    private static String construireRepresentationNoeud(NoeudHuffman noeud) {
    	
    	// Détermination du code de Huffman
    	NoeudHuffman noeudFeuille = noeud;
    	NoeudHuffman noeudEnfant;
    	String codeHuffman = "";
    	do {
			noeudEnfant = noeud;
			noeud = noeud.getNoeudParent();
			if (noeud.getNoeudEnfantGauche() == noeudEnfant) {
				codeHuffman += "1"; // remonte depuis la gauche
			} else {
				codeHuffman += "0"; // remonte depuis la gauche
			}
		
		} while (noeud.getNoeudParent() != null);
    	
    	// Conversion du caractère en sa représentation binaire UTF-8
    	
    	byte[] bits = {};
    	try {
    		
    		// String = char* ; division en paquet de 8 bits
    		bits = Character.toString(noeudFeuille.getLettre())
    						.getBytes("UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		// Corps vide
    	}
    	
    	// Distribution des paquets dans une chaîne
    	String codeBinaireUTF8 = "";
    	for (byte b : bits) {
    		codeBinaireUTF8 
    		+= (String.format("%8s",
    			Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
    	}
    	
		return String.format(FORMAT_ARBRE_HUFFMAN, codeHuffman, 
							 codeBinaireUTF8, noeudFeuille.getLettre());
    }
    
    /**
     * <p>
     * Met en place les noeuds parents et leurs connexions 
     * dans l'arbre de Huffman.
     * 
     * <p>
     * Fonctionnement de l'algorithme :
     * 
     * <p>
     * <ul>
     * <li> 
     * 		Initialisation d'un tableau 
     * 		contenant les noeuds feuilles 
     * </li>
     * <li> DEBUT DE LA BOUCLE </li> 
     * <li>
     * 		Existe t-il deux noeuds 
     *		à fusionner dans le tableau ? 
     * </li>
     * <li> 
     * 		Oui : création d'un parent à partir des deux noeuds,
     * 	    puis mettre le parent dans le tableau.
     * </li>
     * <li> Non : STOP </li>
     */
    private void setConnexionsNoeuds() {

    	/*
    	 * Indice pour itérer dans les noeuds tampon
    	 * en commençant par les noeuds feuilles.
    	 */
    	int i = 0; 
        int o = noeudsFeuilles.length; // Indice des noeuds fusionnés

        /*
         *  Continue de fusionner les nœuds tant
         *  qu'il reste plus d'un nœud à fusionner.
         */
        while (i + 1 < o) {
        	
            /*
             *  Prend les deux nœuds ayant les
             *  plus petites fréquences par paire,
             *  puis ajoute le nouveau nœud parent à noeudsTampon.
             */
            noeudsTampon[o++] = new NoeudHuffman(noeudsTampon[i++],
            									 noeudsTampon[i++]);
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
    		noeudsFeuilles[i++]
    		= new NoeudHuffman(k, dictionnaireLettresFrequences.get(k));
    	}
    	
    	/* 
    	 * Création des noeuds tampon servant à stocker à la fois 
    	 * les noeuds feuilles et les nouveaux noeuds parents.
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
    	
    	System.arraycopy(noeudsFeuilles, 0, noeudsTampon,
    					                 0, noeudsFeuilles.length);
    	
    }
}
