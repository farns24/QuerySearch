package querylogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import facade.SeachFacade;
import main.StopWords;

public class LogParser implements ILogParser {

	@Override
	public LogContents gatherLogs(String logFile) {
	LogContents contents = new LogContents();	
	try {
		StopWords stops = new StopWords();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String filePath = new File("").getAbsolutePath();
		// Read the unorder file in
		BufferedReader in = new BufferedReader(new FileReader(filePath.concat(logFile))); //THIS IS THE FILE THAT CONTAINS THE STOPWORDS
		StringBuffer str = new StringBuffer();
		String nextLine = "";
		while ((nextLine = in.readLine()) != null)
			str.append(nextLine+"\n");
		in.close();
		//save it to a bin tree.
		StringTokenizer st = new StringTokenizer(str.toString());//create a string
		
		LogEntry entry = new LogEntry();
		boolean dateFound = false;
		String fullDate = "";
		
		while(st.hasMoreTokens()){
			nextLine = st.nextToken();
			if (nextLine.matches("[0-9]*"))
			{

				entry = new LogEntry();
				entry.setId(Integer.valueOf(nextLine));
			}
			/*
			 * Stopword removal. While processing a user query Q, any leading stopwords of Q are excluded
				from consideration, whereas non-leading stopwords should be retained and considered for query
				suggestions. For example, “A” in the query “A workshop” is ignored and only “workshop” will
				be considered in making suggestions. (See the Stopword List of Project 1.)
			 */
			else if(nextLine.matches("[a-zA-Z'.]*"))
			{
				if (!stops.contains(nextLine))
				entry.addWordToQuery(nextLine);
				
			}
			else
			{
				fullDate = fullDate.concat(nextLine);
				if (dateFound)
				{
					try {
						entry.setTime(df.parse(fullDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					fullDate ="";
					dateFound = false;
					//contents.add(entry);
					SeachFacade.getInstance().addToTrie(entry.getQuery());
					
					
				}
				else
				{
					fullDate = fullDate.concat(" ");
					dateFound = true;
				}
				
			}
				//validWords.add(nextLine.trim());
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return contents;}

}
