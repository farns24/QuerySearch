package querylogs;

import java.util.Set;

public interface ILogContents {

	public LogContents getSession(int userId);
	
	public Set<Integer> getUsers(); 
}
