/*
 * TestNoeudHuffman.java         08/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import utilitairehuffman.src.NoeudHuffman;

/**
 * Test de la classe NoeudHuffman.
 * @author TD 2 Groupe 4 : Adrien Vigué, Noa M'Tima Lesniak
 */
public class TestNoeudHuffman {
	
    /**
     * Test de la construction d'un parent (noeudHuffmanParent)
     */
	@Test
    public static void testNoeudHuffmanParent() { // static auto d'Eclipse
		
		// Fréquences invalides
		assertThrows(IllegalArgumentException.class,
				     ()-> new NoeudHuffman(new NoeudHuffman('b', 1),
				    		               new NoeudHuffman('a', 0.01)));
		assertThrows(IllegalArgumentException.class,
			     	 ()-> new NoeudHuffman(new NoeudHuffman('b', 0.6),
			    		               	   new NoeudHuffman('a', 0.5)));
		assertThrows(IllegalArgumentException.class,
			     	 ()-> new NoeudHuffman(new NoeudHuffman('b', 0.01),
			    		               	   new NoeudHuffman('a', 1)));
		assertThrows(IllegalArgumentException.class,
			     	 ()-> new NoeudHuffman(new NoeudHuffman('b',0.02),
			    		               	   new NoeudHuffman('a', 0.99)));
		
		// Un des noeuds a déjà un parent
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertThrows(IllegalArgumentException.class,
				 	 ()-> new NoeudHuffman(enfant1, enfant2));
		
		// Cas nominal
		assertDoesNotThrow(() -> new NoeudHuffman
								(new NoeudHuffman('a', 0.19),
					 			 new NoeudHuffman('b', 0.19)));
	}
	
        /**
         * Tests de noeudHuffmanFeuille
         */
	@Test
	// Test de la construction d'un noeud simple
        public static void testNoeudHuffmanFeuille() { // static auto d'Eclipse
		
		assertThrows(IllegalArgumentException.class,
				    () -> new NoeudHuffman('a', -0.1));
		assertThrows(IllegalArgumentException.class,
					() -> new NoeudHuffman('b', 1.1));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman('Q', 0));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman('<', 5));
	}
	
        /**
         * Tests de estFeuille
         */
	@Test
        public static void testEstFeuille() { // static auto d'Eclipse
		
		// Le noeud est sensé être une feuille
		assertEquals(new NoeudHuffman('a', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('\t', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('\n', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('9', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('<', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('é', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('@', 1).estFeuille(), true);
		assertEquals(new NoeudHuffman(' ', 1).estFeuille(), true);
		
		// Le noeud n'est pas sensé être une feuille		
		assertEquals(new NoeudHuffman(new NoeudHuffman('9', 0.2),
				     new NoeudHuffman('8', 0.5)).estFeuille(), false);
	}

        /**
         * Tests de getFreq
         */
	@Test
        public static void testGetFreq() {
		
		// Un getter simple
		assertEquals(new NoeudHuffman('a', 0.18).getFreq(), 0.18);
		
		// Version avec la mise en place d'un parent et deux enfants.
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getFreq(), enfant1.getFreq() 
									 + enfant2.getFreq());
	}
	
        /**
         * Tests de getLettre
         */
	@Test
        public static void testGetLettre() { // static auto d'Eclipse
		
		assertEquals(new NoeudHuffman('a', 0.18).getLettre(), 'a');
		assertEquals(new NoeudHuffman('2', 0.18).getLettre(), '2');
		assertEquals(new NoeudHuffman('>', 0.18).getLettre(), '>');
		assertEquals(new NoeudHuffman('é', 0.18).getLettre(), 'é');
		assertEquals(new NoeudHuffman('@', 0.18).getLettre(), '@');
		assertEquals(new NoeudHuffman('\t', 0.18).getLettre(), '	');
		
		// Cas où le noeud est une feuille
		assertThrows(IllegalStateException.class,
				() -> new NoeudHuffman(new NoeudHuffman('a', 0.19),
                                new NoeudHuffman('b', 0.19)).getLettre());
	}
	
        /**
         * Tests de getEnfantGauche
         */
	@Test
        public static void testGetEnfantGauche() { // static auto d'Eclipse
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getNoeudEnfantGauche(), enfant1);
	}
	
        /**
         * Tests de getEnfantDroit
         */
	@Test
        public static void testGetEnfantDroit() { // static auto d'Eclipse
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getNoeudEnfantDroit(), enfant2);
	}
	
        /**
         * Tests de getNoeudParent
         */
	@Test
        public static void testGetNoeudParent() { // static auto d'Eclipse
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		
		assertEquals(enfant1.getNoeudParent(), parent);
		assertEquals(enfant2.getNoeudParent(), parent);
	}
}