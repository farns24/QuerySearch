package facade;

import java.util.Collection;
import java.util.List;

import trie.Trie;

public class SeachFacade implements ISearchFacade {

	private static SeachFacade instance;

	private Trie mTrie = new Trie();
	
	public static SeachFacade getInstance() {
		if (instance==null)
		{
			instance = new SeachFacade();
		}
		return instance;
	}

	public static void setInstance(SeachFacade instance) {
		SeachFacade.instance = instance;
	}

	@Override
	public void addToTrie(String query) {
		mTrie.add(query);
	}

	@Override
	public Collection<String> getSuggestions(String query) {
		
		return mTrie.getSugestions(query);
	}
	
}
