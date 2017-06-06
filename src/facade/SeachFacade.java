package facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import provided.WCF;
import querylogs.LogParser;
import ranker.QueryModel;
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
		List<QueryModel> sortableList = new ArrayList<QueryModel>();
		
		Map<String,Integer> getMods1 = new LogParser().getMods(query,"Clean-Data-01.txt"); 
		Map<String,Integer> getMods2 = new LogParser().getMods(query,"Clean-Data-02.txt"); 
		Map<String,Integer> getMods3 = new LogParser().getMods(query,"Clean-Data-03.txt"); 
		Map<String,Integer> getMods4 = new LogParser().getMods(query,"Clean-Data-04.txt"); 
		Map<String,Integer> getMods5 = new LogParser().getMods(query,"Clean-Data-05.txt"); 
		query = query.trim()+ " ";
		for(String sq: mTrie.getSugestions(query))
		{
			
			if (sq.length()< query.length()*2.0)
				continue;
			
			
			
			
			double freq = mTrie.getCount(query);
			
			double wcf = WCF.wcf(query, sq);
			
			
			double numerator = freq + wcf;
			
			double denominator = freq;
			if (denominator>wcf)
			{
				denominator = wcf;
			}
			
			
			denominator = 1 - denominator;
			
			double score = numerator/denominator;
			
			QueryModel model = new QueryModel(sq,score);
			sortableList.add(model);
		}
		
		Collections.sort(sortableList);
		int count = 0;
		for (QueryModel q : sortableList)
		{
			count++;
			result.add(q.getQuery());
			System.out.println(q.getScore());
			if (count == 8)
			{
				break;
			}
		}
		
		return result;
	}
	
}
