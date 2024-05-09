/*
 * NoeudHuffman.java         07/05/2024
 * IUT de Rodez, pas de copyright.
 */
package utilitairehuffman.src;

// TODO mettre à jour le diagramme des classes quand c'est fini :3
// TODO éviter de casser la limite DOS

/**
 * Composant servant à créer des noeuds de Huffman.
 * Similaire à un noeud classique, à la seule différence 
 * que ces noeuds peuvent uniquement remonter à son noeud parent.
 * 
 * TODO changer les auteurs
 * @author TD 2 Groupe 4
 */
public class NoeudHuffman {
	
	final private static String ERREUR_FREQ_NEGATIF = "Il y a une valeur négative "
													+ "dans les valeurs données";
	
	final private static String ERREUR_NOEUD_PARENT_NON_NULL = "Il existe déjà un"
															 + "parent au noeud associé";
	final private static String ERREUR_NOEUD_PARENT_EST_FEUILLE = "Le parent est une feuille "
																+ "et ne peut donc pas recevoir d'enfants";
	
	/** Lettre contenue dans le noeud */
    final private char lettre;
    
    /** Fréquence du noeud */
    final private double freq;
    
    /** Noeud parent associé */
    private NoeudHuffman noeudParent;
    
    /*
     *  FIXME Il y a peut être un moyen de le calculer avec
     *  moins de chance d'overflow dans la classe Arbre de Huffman 
     *  (voir avec Noa lors de la conception de la classe ArbreHuffman)
     */
    
    /**
     * Créer un noeud de Huffman avec une lettre et la fréquence
     * d'apparition associée de la lettre en fonction du nombre du
     * nombre de lettres total du texte.
     * 
     * @param caractereDonnee le caractère donnée à associer au noeud
     * @param nombreApparitionCaractère le nombre d'apparition du
     *        caractère dans le texte
     * @param caracteresTotaux le nombre total du caractère du texte
     * 
     * @throws IllegalArgumentException si l'une des deux fréquences
     *         est négative ou si le nombre total de caractère est
     *         égal à 0
     */
    public NoeudHuffman(char caractere, double nombreOccurenceCaractere,
    		            double caracteresTotaux) {
    	
    	if (nombreOccurenceCaractere < 0 || caracteresTotaux <= 0) {
    		throw new IllegalArgumentException(ERREUR_FREQ_NEGATIF);
    	}
    	
    	// on calcule la fréquence d'apparition de la lettre
        lettre = caractere;
        freq = nombreOccurenceCaractere / caracteresTotaux;
    }
    
    /**
     * Créer un noeud de Huffman sans lettre associée.
     * @param nombreOccurenceCaractere
     * @param caracteresTotaux
     */
    public NoeudHuffman(double nombreOccurenceCaractere, double caracteresTotaux) {
    	this(' ', nombreOccurenceCaractere, caracteresTotaux);
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
     * @return son noeud parent
     */
    public NoeudHuffman getNoeudParent() {
    	return noeudParent;
    }
    
    /**
     * Insère dans le noeud parent le noeud actuel
     * 
     * @param noeudParent
     * @throws IllegalArgumentException si :
     *    <p>- le noeud possède déjà un parent</p>
     *    <p>- le parent est une feuille</p>
     */
    public void setNoeudParent(NoeudHuffman noeudParent) {
    	
    	// TODO exception s'il y a plus de deux noeuds attachés au parent
    	if (this.noeudParent != null) {
    		throw new IllegalArgumentException(ERREUR_NOEUD_PARENT_NON_NULL);
    	} else if (noeudParent.estFeuille()) {
    		throw new IllegalArgumentException(ERREUR_NOEUD_PARENT_EST_FEUILLE);
    	} 
    	
    	this.noeudParent = noeudParent;
    }
}
