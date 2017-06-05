package facade;

import java.util.Collection;

public interface ISearchFacade {

	public void addToTrie(String query);
	
	public Collection<String> getSuggestions(String query);
}
