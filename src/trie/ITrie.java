package trie;

import java.util.Collection;

public interface ITrie {

	/**
	 * Adds a Word to the structure
	 * @param word
	 */
	public void add(String word);
	
	/**
	 * Gets sugestions based on a word that has been entered
	 * @param input
	 * @return
	 */
	public Collection<String> getSugestions(String input);
	
	public int getCount(String sq);
}
