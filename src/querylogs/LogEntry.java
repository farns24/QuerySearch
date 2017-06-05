package querylogs;

import java.util.Date;

public class LogEntry {

	private int id;
	
	private String query;
	
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void addWordToQuery(String nextLine) {
		
		if (query ==null)
		{
			query = "";
		}
		else
		{
			query = query.concat(" ");
		}
		
		query = query.concat(nextLine);
		
	}
	
}
