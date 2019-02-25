package io.saul.martin.interview.wordcount.parser;

import org.junit.Test;

import java.io.IOException;
import java.util.TreeMap;

import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.junit.Assert.*;

public class ParserTest {

	@Test(expected = IOException.class)
	public void testNonExistingFileCausesException() throws IOException {
		new Parser("InvalidFile.DoesNotExist");
	}

	@Test(expected = IOException.class)
	public void testUsingADirectoryCausesException() throws IOException {
		new Parser("src");
	}

	@Test
	public void testValidFileDoesNotThrowException() throws IOException {
		new Parser("test.case");
	}

	@Test
	public void testLoadingAValidFileDoesNotThrowException() throws IOException {
		new Parser("test.case").loadFile();
	}

	@Test
	public void testWordCountMapOfValidFile() throws IOException {
		TreeMap<String, Integer> result = new Parser("test.case").loadFile().getMap();
		assertThat(result.get("abacate"), comparesEqualTo(5));
		assertThat(result.size(), comparesEqualTo(14323));
	}
}