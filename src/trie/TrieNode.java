package trie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TrieNode implements ITrieNode {
	
	//private boolean isComplete;
	
	public TrieNode() {
		super();
	}

	private TrieNode[] data = new TrieNode[28];

	
	@Override
	public Collection<String> getChildren() {
		
		Collection<String> result = new TreeSet<String>();
		for (int i = 0; i<28;i++)//(ITrieNode value: data)
		{
			
			TrieNode value = data[i];
			if (value == null)
			{
				continue;
			}
			
			
			if (value instanceof CompleteNode)
			{
				// a + t;
				result.add(String.valueOf(CharConverter.toCharictor(i)));
			}
			else
			{
				//this = c 
				Collection<String> childSet = value.getChildren();
				
				for (String childWord: childSet)
				{
					//c + at
					result.add(String.valueOf(CharConverter.toCharictor(i)).concat(childWord));
				}
			}
			
			
		}
		return result; 
	
	}

	@Override
	public boolean hasChild(char letter) {
		int idx = CharConverter.toInt(letter);
		if (idx < data.length && idx > -1)
		{
		return data[idx]!=null;
		}
		else
		{
			throw new RuntimeException("index " + idx + " is out of bounds");
		}
	}

	@Override
	public TrieNode getChild(char letter) {

		int idx = CharConverter.toInt(letter);
		return data[idx];
	}

	public int parseChar(char c)
	{
		c = Character.toLowerCase(c);
		return CharConverter.toInt(c);//Character.digit('a', c);//getNumericValue(c);
	}
	
	public char toChar(int i)
	{
		return CharConverter.toCharictor(i);//Character.forDigit(i, 'a');
	}
	@Override
	public boolean add(String partialInput) {
	//input: cat 
		//mLetter = partialInput.charAt(0);
		if (partialInput.length()==1)
		{
			return true;
		}
		else
		{
			
			String rest = partialInput.substring(1);
			char nextLetter = rest.charAt(0);
			TrieNode child = null;
			if (!this.hasChild(nextLetter))
			{ 
				if (rest.length()==1)
				{
					child = new CompleteNode();
				}
				else
				{
					child = new TrieNode();
				}
				int idx = CharConverter.toInt(nextLetter);
				data[idx] = child;
			}
			else
			{
				child = getChild(nextLetter);
				
			}
			child.add(rest);
			return false;
		}

	}

//	@Override
//	public boolean isComplete() {
//		return isComplete;
//	}

	

}