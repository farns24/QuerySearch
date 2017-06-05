package ranker;

public interface ISugRank {

	public double suggestedRank();
	
	public double freq(QueryModel sq);
	
	public double WCF(QueryModel q, QueryModel sq);
	
	public double Mod(QueryModel q, QueryModel sq);
	
	public double min(double f,double w,double m);
}
