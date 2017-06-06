package ranker;

public class QueryModel implements Comparable<QueryModel> {

	private String query;
	private double score;

	public QueryModel(String sq, double score) {
		this.query = sq;
		this.score = score;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(QueryModel o) {
		
		return o.score < this.score?1:0;
	}

}
