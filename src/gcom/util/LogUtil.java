package gcom.util;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogUtil {


	public static Logger inicializarLogger(String nomeArquivoLog, Class classe) {

		BasicConfigurator.configure();
		Appender fileAppender = null;
		Logger logger = Logger.getLogger(classe);
		try {

			String pattern = "%d{ISO8601} %n";
			pattern += "Location of log event: %l %n";
			pattern += "Message: %m %n %n";

			fileAppender = new DailyRollingFileAppender(new PatternLayout(
					pattern), nomeArquivoLog + ".log", "'.'dd-MMMMMMMMM-yyyy");
			
			
			logger.setLevel(Level.DEBUG);
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		logger.addAppender(fileAppender);
		return logger;
	}

}
