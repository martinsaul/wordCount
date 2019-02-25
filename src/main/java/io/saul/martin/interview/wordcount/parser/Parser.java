package io.saul.martin.interview.wordcount.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Parser class will handle streaming the file and loading words and their respective count into the TreeMap.
 * The reason I'm using TreeMap is because it not only behaves like a HashMap and has similar performance but also because it also stores values in an already sorted state.
 * Later when outputting the result we just need to traverse the keySet and retrieve the counts.
 */
public final class Parser {
	private final File resultFile;
	private final TreeMap<String, Integer> map;
	private final Scanner scanner;

	/**
	 * This constructor will instantiate the necessary classes to parse the file and validate input.
	 *
	 * @param filePath File path to be accessed and read.
	 * @throws IOException Exception that is thrown in an instance where the file does not exist, or could not be accessed by the Scanner.
	 */
	public Parser(String filePath) throws IOException {
		File file = getFile(filePath);

		this.resultFile = new File(file.getParent(), MessageFormat.format("{0}_result.csv", file.getName()));

		this.scanner = new Scanner(file);
		this.map = new TreeMap<>();
	}

	/**
	 * @param filePath the path to a file to be processed.
	 * @return returns a validated file that is ready to be processed.
	 * @throws IOException throws exception if the file is invalid, not a file or non existing.
	 */
	private File getFile(String filePath) throws IOException {
		if(filePath == null)
			throw new IOException("Invalid null file passed.");

		File file = new File(filePath).getAbsoluteFile();

		if(!file.exists())
			throw new IOException(MessageFormat.format("Could not parse file: {0}. File does not exist.", filePath));
		else if(!file.isFile())
			throw new IOException(MessageFormat.format("{0} is not a file.", filePath));
		return file;
	}

	/**
	 * Streams the file and stores into the map the count of words present in the file.
	 *
	 * @return returns the current instance of the parser.
	 */
	public Parser loadFile() {
		while(scanner.hasNext()){
			String word = scanner.next().toLowerCase();

			if(word.isEmpty())
				continue;

			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		return this;
	}


	/**
	 * Navigates through the map keySet and generates a CSV file with the word count for the specified file.
	 *
	 * @throws IOException Throws IOException in case any issue arises when reading the file.
	 */
	public void writeResult() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));

		writer.write("Word, Count\n");

		for(String word: map.keySet()){
			writer.write(MessageFormat.format("{0}, {1}\n", word, map.get(word)));
		}

		writer.close();
	}


	/**
	 * This method is exclusively used by tests to ensure files are parsed successfully.
	 *
	 * @return Returns current word count map.
	 */
	TreeMap<String, Integer> getMap() {
		return map;
	}
}
