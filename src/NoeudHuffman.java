/*
 * NoeudHuffman.java         07/05/2024
 * IUT de Rodez, pas de copyright.
 */

package iut.info1.codagehuffman.src;

/**
 * Composant servant à créer des noeuds de Huffman.
 * Similaire à un noeud classique, à la seule différence 
 * que ces noeuds peuvent uniquement remonter à son noeud parent.
 * @author TD 2 Groupe 4 : Adrien Vigué, Noa M'Tima Lesniak
 */
public class NoeudHuffman {
	
	final private static String ERREUR_FREQ_INVALIDE
	= "La fréquence n'est pas comprise entre 0 et 1";
	final private static String ERREUR_NOEUD_PARENT_DEJA_DEFINI 
	= "Il existe déjà un parent au noeud associé";
	final private static String ERREUR_NOEUD_PARENT_EST_FEUILLE 
	= "Le parent est une feuille et ne peut donc pas recevoir d'enfants";
	final private static String ERREUR_NOEUD_PARENT_LIMITE_ENFANTS
	= "Le parent possède un nombre d'enfants trop élevé";
	
	/** Le nombre maximum d'enfants qu'un noeud peut avoir */
	final public static int NOMBRE_MAX_ENFANTS = 2;
    
    /** Lettre contenue dans le noeud */
    private char lettre;
    
    /** Fréquence du noeud */
    private double freq;
    
    /** Vérification si le noeud est une feuille */
    private boolean estFeuille;
    
    /** Le nombre d'enfants que possède le noeud */
    private int nbrEnfants = 0;
    
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
    	
    	// on calcule la fréquence d'apparition de la lettre
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
    public NoeudHuffman(NoeudHuffman enfantGauche,
    		            NoeudHuffman enfantDroit) {
    	freq = enfantGauche.getFreq() + enfantDroit.getFreq();
    	
    	// Les deux enfants sont forcément supérieurs à 0
    	if (freq > 1.0) { 
    		throw new IllegalArgumentException(ERREUR_FREQ_INVALIDE);
    	}
    	
    	lettre = ' ';
    	estFeuille = false;
    	
    	setNoeudsEnfant(enfantGauche, enfantDroit);
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
     * Getter de la fréquence
     * @return la fréquence d'apparition du noeud dans le texte.
     */
    public double getFreq() {
        return freq;
    }
    
    /**
     * Getter de lettre
     * @return la lettre associée au noeud.
     */
    public char getLettre() {
        return lettre;
    }
    
    /**
     * Getter du nombre d'enfants
     * @return le nombre d'enfants attachés au noeud
     */
    public int getNombreEnfants() {
    	return nbrEnfants;
    }
    
    /**
     * Getter du noeud parent
     * @return son noeud parent
     */
    public NoeudHuffman getNoeudParent() {
    	return noeudParent;
    }
    
    // TODO getter noeudEnfantGauche et Droit
    
    /** 
     * Attache un parent à l'enfant.
     * @throws IllegalArgumentException lévée si :
     * <ul>
     * <li>Le noeud parent est nul</li>
     * <li>Le noeud parent est une feuille</li>
     * <li>le noeud parent à déjà 2 enfants ou plus</li>
     * </ul>
     */
    private void setNoeudParent(NoeudHuffman noeudParent) {
    	
    	if (this.noeudParent != null) {
    		throw new IllegalArgumentException (
    				  ERREUR_NOEUD_PARENT_DEJA_DEFINI);
    	} else if (noeudParent.estFeuille()) {
    		throw new IllegalArgumentException (
    				  ERREUR_NOEUD_PARENT_EST_FEUILLE);
    	} else if (noeudParent.getNombreEnfants() >= NOMBRE_MAX_ENFANTS) {
    		throw new IllegalArgumentException
    				 (ERREUR_NOEUD_PARENT_LIMITE_ENFANTS);
    	}
    	
    	this.noeudParent = noeudParent;
    	nbrEnfants++;
    }
    
    /**
     * Attache deux enfants dans le noeud pour en faire un parent.
     * @param enfantGauche
     * @param enfanDroit
     */
    private void setNoeudsEnfant(NoeudHuffman enfantDroit, NoeudHuffman enfantGauche) {
    	this.noeudEnfantDroit = noeudEnfantDroit;
    	this.noeudEnfantGauche = noeudEnfantGauche;
    	
    	noeudEnfantDroit.setNoeudParent(this);
    	noeudEnfantGauche.setNoeudParent(this);
    }
}
