package provided;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WCF {

public static double wcf(String w1, String w2){
	try{

//		String w1="cinema"; //stemmed version of a word
//		String w2="movi"; //stemmed version of a word


		String myURL = "http://peacock.cs.byu.edu/CS453Proj2/?word1="+w1+"&word2="+w2;

		//System.out.println("Fetching content: "+myURL);

		Document pageDoc = Jsoup.connect(myURL).get();
		String htmlContent = pageDoc.html();		
		Document contentDoc = Jsoup.parse(htmlContent);
		String contentVal = contentDoc.body().text();
		
		//System.out.println(contentVal);

		Double val= Double.parseDouble(contentVal);

		return val;

	}
	catch (IOException e) {
		e.printStackTrace();
	}
	return 0;

}
}
