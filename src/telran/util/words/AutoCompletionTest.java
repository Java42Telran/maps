package telran.util.words;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoCompletionTest {

	String words[] = { "abcdef", "ab123", "aaa", "ab", "ablmn", "abbbb", "a", "ABd", "bbb", "B12" };
	String wordsStartB[] = { "B12", "bbb" };
	String wordsStartAB[] = { "ab", "ab123", "abbbb", "abcdef", "ABd", "ablmn" };
	String wordsStartABC[] = { "abcdef" };
	AutoCompletion autoCompletion;

	@BeforeEach
	void setUp() throws Exception {
		autoCompletion = new AutoCompletionMapImpl();
		//autoCompletion = new AutoCompletionImpl();
		for (String word : words) {
			autoCompletion.addWord(word);
		}

	}

	@Test
	void testCompletionOptions() {
		assertIterableEquals(Arrays.asList(wordsStartABC), autoCompletion.getCompletionOptions("abc"));
		assertIterableEquals(Arrays.asList(wordsStartB), autoCompletion.getCompletionOptions("B"));
		assertIterableEquals(Arrays.asList(wordsStartAB), autoCompletion.getCompletionOptions("ab"));

	}

	@Test
	void testRemoveIf() {
		if (autoCompletion instanceof AutoCompletionMapImpl) {
			int countExp = 2;
			int countActual = ((AutoCompletionMapImpl) autoCompletion).removeIf(w -> w.length() <= 2);
			assertEquals(countExp, countActual);
			String wordsStartABAfterRemoving[] = { "ab123", "abbbb", "abcdef", "ABd", "ablmn" };
			assertIterableEquals(Arrays.asList(wordsStartABAfterRemoving), autoCompletion.getCompletionOptions("ab"));
			
			
		}
	}

}
