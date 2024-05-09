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
		
		// TODO compléter le jeu de test
		assertThrows(IllegalArgumentException.class,
				    () -> new NoeudHuffman('a', -18.0, 100.0));
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
		
		// TODO compléter le jeu de test
		assertEquals(new NoeudHuffman('a', 18.0, 100.0).getFreq(), 0.18);
	}
	
	@Test
	public void testGetLettre() {
		
		// TODO compléter le jeu de test
		assertEquals(new NoeudHuffman('a', 18.0, 100.0).getLettre(), 'a');
	}
	
	@Test
	public void testGetNoeudParent() {
		
		// TODO À voir comment tester ce truc
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetNoeudParent() {
		
		// TODO À compléter dans le futur
		
		// Cas où le noeud peut-être inséré
		assertDoesNotThrow(() -> new NoeudHuffman('a', 1, 1)
						   	     .setNoeudParent(new NoeudHuffman(1, 1)));
		
		assertDoesNotThrow(() -> new NoeudHuffman('a', 1, 1)
		   	     				 .setNoeudParent(new NoeudHuffman(' ', 1, 1)));
		
		// Cas où le noeud ne peut pas être inséré (feuille)
		assertThrows(IllegalArgumentException.class,
				   () -> new NoeudHuffman(' ', 1, 1)
		   	     	     .setNoeudParent(new NoeudHuffman('q', 1, 1)));
		
		// Cas où le noeud ne peut pas être inséré (parent déjà existant)
		NoeudHuffman Test = new NoeudHuffman('a', 1, 1);
		Test.setNoeudParent(new NoeudHuffman(1, 1));
		assertThrows(IllegalArgumentException.class, 
					() -> Test.setNoeudParent(new NoeudHuffman(1, 1)));
		
		// TODO cas où insertion impossible (règle binaire)
	}
}