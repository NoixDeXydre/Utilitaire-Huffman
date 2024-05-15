/*
 * NoeudHuffman.java         07/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.src;

// TODO mettre à jour le diagramme des classes quand c'est fini :3

/**
 * Composant servant à créer des noeuds de Huffman.
 * Similaire à un noeud classique, à la seule différence 
 * que ces noeuds peuvent uniquement remonter à son noeud parent.
 * 
 * TODO changer les auteurs
 * @author TD 2 Groupe 4
 */
public class NoeudHuffman {
	
	final private static String ERREUR_FREQ_INVALIDE
	= "La fréquence n'est pas comprise entre 0 et 1";
	
	final private static String ERREUR_NOEUD_PARENT_NON_NULL 
	= "Il existe déjà un parent au noeud associé";
	final private static String ERREUR_NOEUD_PARENT_EST_FEUILLE 
	= "Le parent est une feuille et ne peut donc pas recevoir d'enfants";
	final private static String ERREUR_NOEUD_PARENT_LIMITE_ENFANTS
	= "Le parent possède un nombre d'enfants trop élevé";
	
	/** Le nombre maximum d'enfants qu'un noeud peut avoir */
	final private static int NOMBRE_MAX_ENFANTS = 2;
    
    /** Fréquence du noeud */
    final private double freq;
    
    /** Lettre contenue dans le noeud */
    final private char lettre;
    
    /** Noeud parent associé */
    private NoeudHuffman noeudParent;
    
    /** Le nombre d'enfants que possède le noeud */
    private int nbrEnfants = 0;
    
    /**
     * Créer un noeud de Huffman avec une lettre et la fréquence
     * d'apparition associée de la lettre.
     * 
     * @param lettre le caractère donnée à associer au noeud
     * @param freq le nombre d'apparition pondéré du
     *        caractère dans le texte
     * 
     * @throws IllegalArgumentException si la fréquence 
               n'est pas compris entre 0 et 1
     */
    public NoeudHuffman(char lettre, double freq) {
    	
    	if (freq < 0.0 || freq > 1.0) {
    		throw new IllegalArgumentException(ERREUR_FREQ_INVALIDE);
    	}
    	
    	// on calcule la fréquence d'apparition de la lettre
        this.lettre = caractere;
        this.freq = freq;
    }
    
    /**
     * Créer un noeud de Huffman sans lettre associée.
     * @param freq le nombre d'apparition pondéré du
     *        caractère dans le texte
     */
    public NoeudHuffman(double freq) {
    	this(' ', freq);
    }
    
    /**
     * Informe si le noeud est une feuille, en d'autres termes, 
     * s'il ne possède pas de lettre.
     * @return true s'il s'agit d'une feuille, sinon false.
     */
    public boolean estFeuille() {
    	return lettre != ' ';
    }

    /**
     * @return la fréquence d'apparition du noeud dans le texte.
     */
    public double getFreq() {
        return freq;
    }
    
    /**
     * @return la lettre associée au noeud.
     */
    public char getLettre() {
        return lettre;
    }
    
    /**
     * @return le nombre d'enfants attachés au noeud
     */
    public int getNombreEnfants() {
    	return nbrEnfants;
    }
    
    /**
     * @return son noeud parent
     */
    public NoeudHuffman getNoeudParent() {
    	return noeudParent;
    }
    
    /**
     * Insère dans le noeud actuel son parent.
     * Attention, le parent voit alors son instance modifiée !
     * 
     * @param noeudParent
     * @throws IllegalArgumentException si :
     *    <p>- le noeud possède déjà un parent</p>
     *    <p>- le parent est une feuille</p>
     *    <p>- le parent a trop d'enfants</p>
     *    
     *    @see NOMBRE_MAX_ENFANTS
     */
    public void setNoeudParent(NoeudHuffman noeudParent) {

    	if (this.noeudParent != null) {
    		throw new IllegalArgumentException(ERREUR_NOEUD_PARENT_NON_NULL);
    		
    	} else if (noeudParent.estFeuille()) {
    		throw new IllegalArgumentException(ERREUR_NOEUD_PARENT_EST_FEUILLE);
    		
    	} else if (noeudParent.getNombreEnfants() == NOMBRE_MAX_ENFANTS) {
    		throw new IllegalArgumentException
    				 (ERREUR_NOEUD_PARENT_LIMITE_ENFANTS);
    	}
    	
    	this.noeudParent = noeudParent;
    	noeudParent.setIncrementNombreEnfants();
    }
    
    /**
     * Augmente de 1 le nombre d'enfants qu'a le noeud.
     */
    private void setIncrementNombreEnfants() {
    	nbrEnfants++;
    }
}
