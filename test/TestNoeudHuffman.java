package utilitairehuffman.test;

import utilitairehuffman.src.NoeudHuffman;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestNoeudHuffman {

	@Test
	void testNoeudHuffman() {
		assertEquals(new NoeudHuffman('a', 18.0, 100.0).getLettre(), 'a');
		assertEquals(new NoeudHuffman('a', 18.0, 100.0).getFreq(), 0.18);
		assertThrows(IllegalArgumentException.class,()-> 
		             new NoeudHuffman('a', -18.0, 100.0));
	}

	@Test
	void testGetLettre() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFreq() {
		fail("Not yet implemented");
	}

}
