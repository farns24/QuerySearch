package querylogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
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
		{
			 if (nextLine.equals("AnonID\tQuery\tQueryTime"))
					 continue;
			 //LogEntry entry = new LogEntry();
			 String[] tokens = nextLine.split("\t");
			 
			 SeachFacade.getInstance().addToTrie(tokens[1]);
//				
			//nextLine = st.nextToken();
//			if (nextLine.matches("[0-9]*"))
//			{
//
//				entry = new LogEntry();
//				entry.setId(Integer.valueOf(nextLine));
//			}
//			/*
//			 * Stopword removal. While processing a user query Q, any leading stopwords of Q are excluded
//				from consideration, whereas non-leading stopwords should be retained and considered for query
//				suggestions. For example, �A� in the query �A workshop� is ignored and only �workshop� will
//				be considered in making suggestions. (See the Stopword List of Project 1.)
//			 */
//			else if(nextLine.matches("[a-zA-Z'.]*"))
//			{
//				if (!stops.contains(nextLine))
//				entry.addWordToQuery(nextLine);
//				
//			}
//			else
//			{
//				fullDate = fullDate.concat(nextLine);
//				if (dateFound)
//				{
//					try {
//						entry.setTime(df.parse(fullDate));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					fullDate ="";
//					dateFound = false;
//					//contents.add(entry);
//					SeachFacade.getInstance().addToTrie(entry.getQuery());
//					
//					
//				}
//				else
//				{
//					fullDate = fullDate.concat(" ");
//					dateFound = true;
//				}
				
			}
				//validWords.add(nextLine.trim());
		
//		}
			//str.append(nextLine+"\n");
		in.close();
		//save it to a bin tree.
//		StringTokenizer st = new StringTokenizer(str.toString());//create a string
//		
//		LogEntry entry = new LogEntry();
//		boolean dateFound = false;
//		String fullDate = "";
//		
//		while(st.hasMoreTokens()){}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return contents;}

	public Map<String, Integer> getMods(String query, String logFile) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		
		StopWords stops = new StopWords();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String filePath = new File("").getAbsolutePath();
		// Read the unorder file in
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(filePath.concat(logFile)));
		 
		StringBuffer str = new StringBuffer();
		String nextLine = "";
		boolean queryFound = false;
		String session ="";
		Date validRange = null;
		while ((nextLine = in.readLine()) != null)
		{
			 if (nextLine.equals("AnonID\tQuery\tQueryTime"))
					 continue;
			 //LogEntry entry = new LogEntry();
			 String[] tokens = nextLine.split("\t");
			 
			 String q = tokens[1];
			 
			 
			 if (q.equals(query))
			 {
				 queryFound = true;
				session = tokens[0];
				try {
					validRange = df.parse(tokens[2]);
					
					Calendar limit = new GregorianCalendar();
					limit.setTime(validRange);
					limit.add(Calendar.MINUTE, 10);
					validRange = limit.getTime();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }
			 else if (queryFound)
			 {
				try {
					Date impTime =  df.parse(tokens[2]);
					
					if(impTime.before(validRange) && tokens[0].equals(session))
					{
						addToMap(result,tokens[1]);
					}
					else
					{
						queryFound = false;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
//				
			//nextLine = st.nextToken();
//			if (nextLine.matches("[0-9]*"))
//			{
//
//				entry = new LogEntry();
//				entry.setId(Integer.valueOf(nextLine));
//			}
//			/*
//			 * Stopword removal. While processing a user query Q, any leading stopwords of Q are excluded
//				from consideration, whereas non-leading stopwords should be retained and considered for query
//				suggestions. For example, �A� in the query �A workshop� is ignored and only �workshop� will
//				be considered in making suggestions. (See the Stopword List of Project 1.)
//			 */
//			else if(nextLine.matches("[a-zA-Z'.]*"))
//			{
//				if (!stops.contains(nextLine))
//				entry.addWordToQuery(nextLine);
//				
//			}
//			else
//			{
//				fullDate = fullDate.concat(nextLine);
//				if (dateFound)
//				{
//					try {
//						entry.setTime(df.parse(fullDate));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					fullDate ="";
//					dateFound = false;
//					//contents.add(entry);
//					SeachFacade.getInstance().addToTrie(entry.getQuery());
//					
//					
//				}
//				else
//				{
//					fullDate = fullDate.concat(" ");
//					dateFound = true;
//				}
				
			}
				//validWords.add(nextLine.trim());
		
//		}
			//str.append(nextLine+"\n");
		in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //THIS IS THE FILE THAT CONTAINS
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//save it to a bin tree.
//		StringTokenizer st = new StringTokenizer(str.toString());//create a string
//		
//		LogEntry entry = new LogEntry();
//		boolean dateFound = false;
//		String fullDate = "";
//		
//		while(st.hasMoreTokens()){}
		return result;
	}

	private void addToMap(Map<String, Integer> result, String string) {
		if (!result.containsKey(string))
		{
			result.put(string, 0);
		}
		
		result.put(string, result.get(string)+1);
		
	}

}
