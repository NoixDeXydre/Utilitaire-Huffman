/*
 * TestNoeudHuffman.java         08/05/2024
 * IUT de Rodez, pas de copyright.
 */

package iut.info1.codagehuffman.test;

import iut.info1.codagehuffman.src.NoeudHuffman;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test de la classe NoeudHuffman.
 * @author TD 2 Groupe 4 : Adrien Vigué, Noa M'Tima Lesniak
 */
public class TestNoeudHuffman {
	
	@Test
	public void testNoeudHuffman() {
		
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
	}
	
	@Test
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
		assertEquals(new NoeudHuffman('a', 0.18).getFreq(), 0.18);
		assertEquals(new NoeudHuffman(new NoeudHuffman('9', 1 / 70.0),
				                      new NoeudHuffman('8', 1 / 70.0)).getFreq(),
		             (double) 2 / 70.0);
	}
	
	@Test
	public void testGetLettre() {
		assertEquals(new NoeudHuffman('a', 0.18).getLettre(), 'a');
		assertEquals(new NoeudHuffman('2', 0.18).getLettre(), '2');
		assertEquals(new NoeudHuffman('>', 0.18).getLettre(), '>');
		assertEquals(new NoeudHuffman('é', 0.18).getLettre(), 'é');
		assertEquals(new NoeudHuffman('@', 0.18).getLettre(), '@');
		assertEquals(new NoeudHuffman('\t', 0.18).getLettre(), '	');
		assertEquals(new NoeudHuffman(new NoeudHuffman('9', 1 / 70.0),
                     new NoeudHuffman('8', 1 / 70.0)).getLettre(), ' ');
	}
	
	@Test
	public void testGetNombreEnfants() {
		fail("not yet implanted");
	}
	
	@Test
	public void testGetNoeudParent() {
		fail("not yet implanted");
	}
}