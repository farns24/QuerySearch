package querylogs;

import java.io.File;

public interface ILogParser {

	public LogContents gatherLogs(String logFile);
}
