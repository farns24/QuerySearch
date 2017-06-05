package trie;

import java.util.Collection;

public interface ITrieNode {

	public Collection<String> getChildren();
	
	public boolean hasChild(char letter);
	
	public TrieNode getChild(char letter);
	
	/**
	 * 
	 * @param partialInput
	 * @return True if the node added is final
	 */
	public boolean add(String partialInput);
	
	//public boolean isComplete();

}
