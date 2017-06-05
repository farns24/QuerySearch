package trie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class Trie implements ITrie {

	TrieNode base = new TrieNode();
	String wildCard = " ";

	@Override
	public Collection<String> getSugestions(String input) {
		char next = input.charAt(0);
		ITrieNode sugest = base;
		int i = 0;
		while (sugest.hasChild(next) && ++i<input.length())
		{
			sugest = sugest.getChild(next);
			next = input.charAt(i);
		}
		sugest = sugest.getChild(next);
		Collection<String> result = new HashSet<String>();
		Collection<String> partials = sugest.getChildren();
		
		for (String part: partials)
		{
			result.add(input.concat(part));
		}
		
		return result;
	}

	@Override
	public void add(String word) {
		if (word!=null)
		base.add(wildCard.concat(word));
	}

	@Override
	public int getCount(String sq) {
		return base.getCount(wildCard.concat(sq));
	}

}
