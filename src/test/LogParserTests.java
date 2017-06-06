package test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import facade.SeachFacade;
import querylogs.LogParser;

public class LogParserTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testload() {
		LogParser parser = new LogParser();
		//parser.gatherLogs("/data/TestFile.txt");
		parser.gatherLogs("/data/Clean-Data-01.txt");
		parser.gatherLogs("/data/Clean-Data-02.txt");
		parser.gatherLogs("/data/Clean-Data-03.txt");
		parser.gatherLogs("/data/Clean-Data-04.txt");
		parser.gatherLogs("/data/Clean-Data-05.txt");
		System.out.println("Loaded everything");
		Collection<String> suggestions = SeachFacade.getInstance().getSuggestions("car");
		for(String sugest: suggestions)
		{
			System.out.println(sugest);
		}
	}

}
