/*
 * NoeudHuffman.java         07/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.src;

/**
 * Composant servant à créer des noeuds de Huffman.
 * Similaire à un noeud classique, à la seule différence 
 * que ces noeuds peuvent aussi remonter à son noeud parent 
 * que descendre à ses noeuds enfant.
 * @author TD 2 Groupe 4 : Adrien Vigué, Noa M'Tima Lesniak
 */
public class NoeudHuffman {
	
	final private static String ERREUR_NOEUD_DEJA_ENFANT
	= "Un noeud est déjà enfant d'un autre parent";
	
	final private static String ERREUR_FREQ_INVALIDE
	= "La fréquence n'est pas comprise entre 0 et 1";
	
	final private static String ERREUR_FREQ_DEPASSEMENT
	= "L'addition des fréquences enfant est supérieure à 1";
	
	final private static String ERREUR_NOEUD_PARENT_NON_FEUILLE 
	= "Le noeud n'est pas une feuille et ne contient donc pas lettre";
    
    /** Lettre contenue dans le noeud */
    private char lettre;
    
    /** Fréquence du noeud */
    private double freq;
    
    /** Vérification si le noeud est une feuille */
    private boolean estFeuille;
    
    /** Noeuds enfant du parent */
    private NoeudHuffman noeudEnfantDroit, 
    					 noeudEnfantGauche;
    
    /** Noeud parent associé */
    private NoeudHuffman noeudParent;
    
    /**
     * Créer un noeud de Huffman avec une lettre et la fréquence
     * d'apparition associée de la lettre.
     * @param lettre le caractère donnée à associer au noeud
     * @param freq le nombre d'apparition pondéré du
     * @throws IllegalArgumentException si la fréquence 
     *         n'est pas comprise entre 0 (non inclut) et 1
     * @see java.lang.IllegalArgumentException   
     */
    public NoeudHuffman(char lettre, double freq) {
    	
    	if (freq <= 0.0 || freq > 1.0) {
    		throw new IllegalArgumentException(ERREUR_FREQ_INVALIDE);
    	}
    	
        this.lettre = lettre;
        this.freq = freq;
        estFeuille = true;
    }

    
    /**
     * Créer un noeud de Huffman parent de deux noeud feuille
     * sans lettre associée en aditionnant les deux fréquences des
     * noeuds fils.
     * @param enfantGauche l'enfant gauche du futur noeud parent
     * @param enfantDroit l'enfant droit du futur noeud parent
     * @throws IllegalArgumentException si la fréquence n'est pas
     *         inférieur à 1
     * @see java.lang.IllegalArgumentException   
     */
    public NoeudHuffman(NoeudHuffman noeudEnfantGauche,
    		            NoeudHuffman noeudEnfantDroit) {
    	
    	if (noeudEnfantGauche.getFreq() 
    	  + noeudEnfantDroit.getFreq() > 1) {
    		throw new IllegalArgumentException(ERREUR_FREQ_DEPASSEMENT);
    		
    	} else if (noeudEnfantGauche.getNoeudParent() != null 
    			|| noeudEnfantDroit.getNoeudParent()  != null) {
    		throw new IllegalArgumentException(ERREUR_NOEUD_DEJA_ENFANT);
    	}
    	
    	estFeuille = false;
    	setNoeudsEnfant(noeudEnfantGauche, noeudEnfantDroit);
    }

	/**
     * Informe si le noeud est une feuille, en d'autres termes, 
     * s'il ne possède pas de lettre.
     * @return true s'il s'agit d'une feuille, sinon false.
     */
    public boolean estFeuille() {
    	return estFeuille;
    }

    /**
     * Getter de la fréquence.
     * @return la fréquence d'apparition du noeud dans le texte.
     */
    public double getFreq() {
        return freq;
    }
    
    /**
     * Getter de la lettre
     * @return la lettre associée au noeud.
     * @throws IllegalStateException si le noeud n'est pas une feuille
     * 		   (ne contient pas de lettre)
     */
    public char getLettre() {
    	
    	if (!estFeuille()) {
    		throw new IllegalStateException(ERREUR_NOEUD_PARENT_NON_FEUILLE);
    	}
        return lettre;
    }
    
    /**
     * Getter du noeud enfant de gauche.
     * @return son enfant de gauche
     */
    public NoeudHuffman getNoeudEnfantGauche() {
    	return noeudEnfantGauche;
    }
    
    /**
     * Getter du noeud enfant de droite.
     * @return son enfant de droite
     */
    public NoeudHuffman getNoeudEnfantDroit() {
    	return noeudEnfantDroit;
    }
    
    /**
     * Getter du noeud parent.
     * @return son noeud parent
     */
    public NoeudHuffman getNoeudParent() {
    	return noeudParent;
    }
    
    /**
     * Attache deux enfants dans le noeud pour en faire un parent
     * et fusionne la fréquence des deux enfants d'après
     * l'algorithme de Huffman.
     * @param enfantGauche
     * @param enfanDroit
     * @throws IllegalArgumentException si les deux fréquences
     * 		   sont supérieures à 1
     */
    private void setNoeudsEnfant(NoeudHuffman noeudEnfantGauche,
    							 NoeudHuffman noeudEnfantDroit) {
    	
    	this.noeudEnfantGauche = noeudEnfantGauche;
    	this.noeudEnfantDroit = noeudEnfantDroit;
    	
    	noeudEnfantDroit.setNoeudParent(this);
    	noeudEnfantGauche.setNoeudParent(this);
    	
    	freq = noeudEnfantGauche.getFreq() 
			 + noeudEnfantDroit.getFreq();
    }
    
    /** 
     * Attache un parent à l'enfant.
     * @param noeudParent le noeud parent
     */
    private void setNoeudParent(NoeudHuffman noeudParent) {
    	this.noeudParent = noeudParent;
    }
}
