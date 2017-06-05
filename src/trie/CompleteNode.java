package trie;

public class CompleteNode extends TrieNode {

	int count = 1;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incrimentCount() {
		count+= 1;
		
	}
	
}
