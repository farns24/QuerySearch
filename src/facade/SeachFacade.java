package facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.StopWords;
import trie.Trie;

public class SeachFacade implements ISearchFacade {

	private static SeachFacade instance;

	private Trie mTrie = new Trie();
	//private Map<String,Integer> wordCount = new TreeMap<String, Integer>();
	private StopWords sw = new StopWords();
	
	
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
		
		String[] words = query.split(" ");
		boolean trimmed = false;
		StringBuilder formattedQuery = new StringBuilder();
		for (String word : words)
		{
			if (!sw.contains(word) && !trimmed)
			{
				trimmed = true;
				formattedQuery.append(word);
				
			}
			else if (trimmed)
			{
				formattedQuery.append(" ").append(word);
			}
			else
			{
				continue;
			}
		}
		
		
		mTrie.add(formattedQuery.toString());
	}

	@Override
	public Collection<String> getSuggestions(String query) {
		
		List<String> result = new ArrayList<String>();
		
		for(String sq: mTrie.getSugestions(query))
		{
			double freq = mTrie.getCount(query);
			
			double wcf = WCF()
			
			
		}
		
		return result;
	}
	
}
