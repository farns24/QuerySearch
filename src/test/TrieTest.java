package test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import trie.ITrie;
import trie.Trie;

public class TrieTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCats() {
		ITrie trie = new Trie();
		
		trie.add("cat");
		Collection<String> sample1 = trie.getSugestions("c");
		assertTrue(sample1.contains("cat"));
		
		
		
	}
	
	@Test 
	public void testMultipleSugestions()
	{
		ITrie trie = new Trie();
		trie.add("airsupply");
		trie.add("airplane");
		trie.add("aigorp");
		Collection<String> sample = trie.getSugestions("air");
		assertTrue(sample.size()==2);
		assertTrue(sample.contains("airsupply"));
	}
	
	@Test
	public void testDuplicateAdd()
	{
		Trie trie = new Trie();
		trie.add("cheese");
		trie.add("cheese");
		Collection<String> sample = trie.getSugestions("cheese");
	}
	
	@Test
	public void testCount()
	{
		Trie trie = new Trie();
		trie.add("cheese");
		trie.add("cheese");
		int sample = trie.getCount("cheese");
		assertTrue(sample == 2);
	}

}
