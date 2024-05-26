/*
 * TestNoeudHuffman.java         08/05/2024
 * IUT de Rodez, pas de copyright.
 */

package utilitairehuffman.test;

import utilitairehuffman.src.NoeudHuffman;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test de la classe NoeudHuffman.
 * @author TD 2 Groupe 4 : Adrien Vigué, Noa M'Tima Lesniak
 */
public class TestNoeudHuffman {
	
	@Test
	// Test de la construction d'un parent
	public void testNoeudHuffmanParent() {
		
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
	
	@Test
	// Test de la construction d'un noeud simple
	public void testNoeudHuffmanFeuille() {
		
		assertThrows(IllegalArgumentException.class,
				    () -> new NoeudHuffman('a', -0.1));
		assertThrows(IllegalArgumentException.class,
					() -> new NoeudHuffman('b', 1.1));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman('Q', 0));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman('<', 5));
	}
	
	@Test
	public void testEstFeuille() {
		
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

	@Test
	public void testGetFreq() {
		
		// Un getter simple
		assertEquals(new NoeudHuffman('a', 0.18).getFreq(), 0.18);
		
		// Version avec la mise en place d'un parent et deux enfants.
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getFreq(), enfant1.getFreq() 
									 + enfant2.getFreq());
	}
	
	@Test
	public void testGetLettre() {
		
		assertEquals(new NoeudHuffman('a', 0.18).getLettre(), 'a');
		assertEquals(new NoeudHuffman('2', 0.18).getLettre(), '2');
		assertEquals(new NoeudHuffman('>', 0.18).getLettre(), '>');
		assertEquals(new NoeudHuffman('é', 0.18).getLettre(), 'é');
		assertEquals(new NoeudHuffman('@', 0.18).getLettre(), '@');
		assertEquals(new NoeudHuffman('\t', 0.18).getLettre(), '	');
		
		// Cas où le noeud est une feuille
		assertThrows(IllegalStateException.class,
					() -> new NoeudHuffman(new NoeudHuffman('a', 0.19),
						 			   	   new NoeudHuffman('b', 0.19))
				    	 .getLettre());
	}
	
	@Test
	public void testGetEnfantGauche() {
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getNoeudEnfantGauche(), enfant1);
	}
	
	@Test
	public void testGetEnfantDroit() {
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		assertEquals(parent.getNoeudEnfantDroit(), enfant2);
	}
	
	@Test
	public void testGetNoeudParent() {
		NoeudHuffman enfant1 = new NoeudHuffman('a', 0.18);
		NoeudHuffman enfant2 = new NoeudHuffman('b', 0.18);
		NoeudHuffman parent = new NoeudHuffman(enfant1, enfant2);
		
		assertEquals(enfant1.getNoeudParent(), parent);
		assertEquals(enfant2.getNoeudParent(), parent);
	}
}