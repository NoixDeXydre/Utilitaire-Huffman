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
 * 
 * TODO changer les auteurs
 * @author TD 2 Groupe 4
 */
public class TestNoeudHuffman {
	
	@Test
	public void testNoeudHuffman() {
		
		assertThrows(IllegalArgumentException.class,
				    () -> new NoeudHuffman('a', -18, 100));
		
		assertThrows(IllegalArgumentException.class,
					() -> new NoeudHuffman(0, -1));
	}
	
	@Test
	public void testEstFeuille() {
		
		// Le noeud est sensé être une feuille
		assertEquals(new NoeudHuffman('a', 1, 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('\t', 1, 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('\n', 1, 1).estFeuille(), true);
		assertEquals(new NoeudHuffman('9', 1, 1).estFeuille(), true);
		
		// Le noeud n'est pas sensé être une feuille
		assertEquals(new NoeudHuffman(' ', 1, 1).estFeuille(), false);
		assertEquals(new NoeudHuffman(1, 1).estFeuille(), false);
	}

	@Test
	public void testGetFreq() {
		
		assertEquals(new NoeudHuffman('a', 18, 100).getFreq(), 0.18);
		assertEquals(new NoeudHuffman(2, 70).getFreq(), (double) 2 / 70.0);
	}
	
	@Test
	public void testGetLettre() {
		
		assertEquals(new NoeudHuffman('a', 18, 100).getLettre(), 'a');
		assertEquals(new NoeudHuffman(2, 70).getLettre(), ' ');
	}
	
	@Test
	public void testGetNoeudParent() {
		
		NoeudHuffman test = new NoeudHuffman(1, 1);
		NoeudHuffman test2 = new NoeudHuffman(1, 1);
		test.setNoeudParent(test2);
		
		assertEquals(test.getNoeudParent(), test2);
		assertEquals(test2.getNoeudParent(), null);
	}
	
	@Test
	public void testSetNoeudParent() {
		
		// TODO À compléter dans le futur
		
		// Variable regénérative pour des tests complexes
		NoeudHuffman test;
		NoeudHuffman test2;
		
		// Cas où le noeud peut-être inséré
		assertDoesNotThrow(() -> new NoeudHuffman('a', 1, 1)
						   	     .setNoeudParent(new NoeudHuffman(1, 1)));
		
		test = new NoeudHuffman(1, 1);
		test.setNoeudParent(new NoeudHuffman(1, 1));
		assertDoesNotThrow(() -> new NoeudHuffman(' ', 1, 1)
  	     	     			     .setNoeudParent(test));
		
		// Cas où le noeud ne peut pas être inséré (feuille)
		assertThrows(IllegalArgumentException.class,
				   () -> new NoeudHuffman(' ', 1, 1)
		   	     	     .setNoeudParent(new NoeudHuffman('q', 1, 1)));
		
		// Cas où le noeud ne peut pas être inséré (parent déjà existant)
		test2 = new NoeudHuffman('a', 1, 1);
		test2.setNoeudParent(new NoeudHuffman(1, 1));
		assertThrows(IllegalArgumentException.class, 
					() -> test.setNoeudParent(new NoeudHuffman(1, 1)));
		
		// TODO cas où insertion impossible (règle binaire)
	}
}