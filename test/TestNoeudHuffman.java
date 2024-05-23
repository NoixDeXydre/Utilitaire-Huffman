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
				    () -> new NoeudHuffman(-0.18));
		assertThrows(IllegalArgumentException.class,
					() -> new NoeudHuffman(0));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman(1.1));
		assertThrows(IllegalArgumentException.class,
				() -> new NoeudHuffman(5.5));
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
	public void testSetNoeudParent() {
		
		// Variable regénérative pour des tests complexes
		final NoeudHuffman noeudDeTest;
		NoeudHuffman noeudDeTest2;
		NoeudHuffman noeudDeTest3;
		NoeudHuffman noeudDeTest4;
		
		// Cas où le noeud peut-être inséré
		assertDoesNotThrow(() -> new NoeudHuffman('a', 1)
						   	     .setNoeudParent(new NoeudHuffman(1)));
		
		noeudDeTest = new NoeudHuffman(1);
		noeudDeTest.setNoeudParent(new NoeudHuffman(1));
		assertDoesNotThrow(() -> new NoeudHuffman(' ', 1)
  	     	     			     .setNoeudParent(noeudDeTest));
		
		// Cas où le noeud ne peut pas être inséré (feuille)
		assertThrows(IllegalArgumentException.class,
				     () -> new NoeudHuffman(' ', 1)
		   	     	       .setNoeudParent(new NoeudHuffman('q', 1)));
		
		// Cas où le noeud ne peut pas être inséré (parent déjà existant)
		noeudDeTest2 = new NoeudHuffman('a', 1);
		noeudDeTest2.setNoeudParent(new NoeudHuffman(1));
		assertThrows(IllegalArgumentException.class, 
					() -> noeudDeTest.setNoeudParent(new NoeudHuffman(1)));
		
		// Cas où il y a trop d'enfants
		noeudDeTest4 = new NoeudHuffman(1);
		noeudDeTest2 = new NoeudHuffman(1);
		noeudDeTest3 = new NoeudHuffman(1);
		
		noeudDeTest4.setNoeudParent(noeudDeTest3);
		noeudDeTest2.setNoeudParent(noeudDeTest3);
		
		assertThrows(IllegalArgumentException.class, 
		() -> new NoeudHuffman(1).setNoeudParent(noeudDeTest3));
		
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
		
		// Le noeud n'est pas sensé être une feuille
		assertEquals(new NoeudHuffman(' ', 1).estFeuille(), false);
		assertEquals(new NoeudHuffman(1).estFeuille(), false);
	}

	@Test
	public void testGetFreq() {
		assertEquals(new NoeudHuffman('a', 0.18).getFreq(), 0.18);
		assertEquals(new NoeudHuffman((double) 2 / 70.0).getFreq(), 
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
		assertEquals(new NoeudHuffman(1).getLettre(), ' ');
	}
	
	@Test
	public void testGetNombreEnfants() {
		
		NoeudHuffman noeudDeTest = new NoeudHuffman(1);
		NoeudHuffman noeudDeTest2 = new NoeudHuffman(1);
		NoeudHuffman noeudDeTest3 = new NoeudHuffman(1);
		
		assertEquals(noeudDeTest.getNombreEnfants(), 0);
		
		noeudDeTest2.setNoeudParent(noeudDeTest);
		assertEquals(noeudDeTest.getNombreEnfants(), 1);
		
		noeudDeTest3.setNoeudParent(noeudDeTest);
		assertEquals(noeudDeTest.getNombreEnfants(), 2);
	}
	
	@Test
	public void testGetNoeudParent() {
		
		NoeudHuffman noeudDeTest = new NoeudHuffman(1);
		NoeudHuffman noeudDeTest2 = new NoeudHuffman(1);
		noeudDeTest.setNoeudParent(noeudDeTest2);
		
		assertEquals(noeudDeTest.getNoeudParent(), noeudDeTest2);
		assertEquals(noeudDeTest2.getNoeudParent(), null);
	}
}