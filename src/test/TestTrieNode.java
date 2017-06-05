package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import trie.TrieNode;

public class TestTrieNode {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCharEncoder() {
		TrieNode node = new TrieNode();
		
		int a = node.parseChar('a');
		char letterA = node.toChar(a);
		assertTrue(letterA =='a');
		
		int space = node.parseChar('\'');
		
	}

}
