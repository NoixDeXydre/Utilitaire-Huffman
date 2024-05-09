/*
 * NoeudHuffman.java         07/05/2024
 * iut de Rodez, pas de copyright
 */
package utilitairehuffman.src;

/**
 * Composant servant à créer des noeuds de Huffman.
 * Similaire à un noeud classique à la seule différence qui peut
 * remonter à son noeud parent.
 * @author TD 2 Groupe 4
 */
public class NoeudHuffman {

    private char lettre;
    private double freq;
    
    /**
     * Créer un noeud de huffman avec une lettre et la fréquence
     * d'apparition associée de la lettre en fonction du nombre du
     * nombre de lettres total du texte
     * @param caractereDonnee le caractère donnée à associer au noeud
     * @param nombreApparitionCaractère le nombre d'apparition du
     *        cacractère dans le texte
     * @param caracteresTotaux le nombre total du caractère du texte
     * @throws IllegalArgumetException si l'une des deux fréquences
     *         est négative ou si le nombre total de carctère est
     *         égal à 0
     */
    public NoeudHuffman(char caractereDonnee, double nombreApparitionCaractère,
    		            double caracteresTotaux) {
    	if (nombreApparitionCaractère < 0 || caracteresTotaux <= 0) {
    		throw new IllegalArgumentException("Il y a une valeur négative " +
    				                           "dans les valeurs données");
    	}
    	/* on calcule la fréquence d'apparition de la lettre */
        this.lettre = caractereDonnee;
        this.freq = nombreApparitionCaractère/caracteresTotaux;
    }

    /**
     * @return la lettre associée au noeud
     */
    public char getLettre() {
        return lettre;
    }

    /**
     * @return la fréquence d'apparition du noeud dans le texte
     */
    public double getFreq() {
        return freq;
    }

}
