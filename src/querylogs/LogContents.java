package querylogs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LogContents extends ArrayList<LogEntry> implements ILogContents {

	private Set<Integer> users = new HashSet<Integer>();

	@Override
	public LogContents getSession(int userId) {
	     
		LogContents sessionContents = new LogContents();
		 
		for (LogEntry entry: this)
		{
			if (entry.getId() == userId)
			{
				sessionContents.add(entry);
			}
		}
		
		return sessionContents;
	}

	@Override
	public Set<Integer> getUsers() {
		return users;
	}

	@Override
	public boolean add(LogEntry arg0) {
		if (arg0 ==null)
		{
			return false;
		}
		users.add(arg0.getId());
		return super.add(arg0);
	} 

	
}
