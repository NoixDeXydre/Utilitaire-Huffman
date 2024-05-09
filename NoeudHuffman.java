/*
 * NoeudHuffman.java         07/05/2024
 * iut de Rodez, pas de copyright
 */
package iut.info1.codagehuffman;

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
     */
    public NoeudHuffman() {
        // TODO Auto-generated constructor stub
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
