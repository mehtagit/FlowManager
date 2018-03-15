package nuance.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import nuance.base.Transaction;

public class FileWriter {

	protected static Logger logger = LogManager.getLogger(FileWriter.class);

	public static void write(String cdr) {
		logger.info(cdr);
	}
}
