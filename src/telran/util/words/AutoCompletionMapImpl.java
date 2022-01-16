package telran.util.words;

import java.util.function.Predicate;
import java.util.*;
public class AutoCompletionMapImpl implements AutoCompletion {
HashMap<Character, TreeSet<String>> words = new HashMap<>(); //key - first character of a word;
// value - collection (TreeSet) of words beginning with the given first character case insensitive
	@Override
	/**
	 * adds word 
	 * with applying the method computeIfAbsent
	 */
	public boolean addWord(String word) {
		
		
		return words.computeIfAbsent(Character.toLowerCase(word.charAt(0)),
				k -> new TreeSet<String>(String.CASE_INSENSITIVE_ORDER)).add(word);
	}

	@Override
	public boolean removeWord(String word) {
		TreeSet<String> wordsFirstLetter = words.get(Character.toLowerCase(word.charAt(0)));
		
		return wordsFirstLetter != null && wordsFirstLetter.remove(word);
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		
		char firstLetter = Character.toLowerCase(prefix.charAt(0));
		TreeSet<String> wordsFirstLetter = words.get(firstLetter);
		
		return wordsFirstLetter == null ? Collections.emptyList():
			wordsFirstLetter.subSet(prefix, AutoCompletion.getPrefixLimit(prefix));
	}
	/**
	 * removes words matching a given predicate
	 * @param predicate
	 * @return count of the removed words
	 */
	public int removeIf(Predicate<String> predicate) {
		int count = 0;
		for (Collection<String> wordsFirstLetter: words.values()) {
			int oldSize = wordsFirstLetter.size();
			wordsFirstLetter.removeIf(predicate);
			count = count + (oldSize - wordsFirstLetter.size());
		}
		return count;
	}
	

}
