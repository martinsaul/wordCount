package io.saul.martin.interview.wordcount;

import io.saul.martin.interview.wordcount.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Entry point class.
 */
public class Launcher {
	/**
	 * Entry point method that allows users to specify a list of files to be processed.
	 *
	 * @param fileList List of paths to files to be processed.
	 */
	public static void main(String[] fileList){
		Logger logger = LoggerFactory.getLogger(Launcher.class);

		if(fileList.length == 0){
			logger.warn("Warning: No input files provided. Please list paths to files you'd like to process as parameters.");
		} else {
			for (String file : fileList) {
				try {
					logger.info(MessageFormat.format("Parsing file: {0}", file));
					Parser parser = new Parser(file);
					parser.loadFile().writeResult();
					logger.info(MessageFormat.format("Successfully parsed and generated report on file: {0}", file));
				} catch (IOException e) {
					logger.error(MessageFormat.format("Failed to parse file: %s%n", file), e);
				}
			}
		}
	}
}
