package telran.util.words;
import java.util.*;
public class AutoCompletionImpl implements AutoCompletion {
	private TreeSet<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
 public AutoCompletionImpl() {
		
	}
 public AutoCompletionImpl(Collection<String> collection) {
		words.addAll(collection);
	}
 
	@Override
	public boolean addWord(String word) {
		
		return words.add(word);
	}

	@Override
	public boolean removeWord(String word) {
		
		return words.remove(word);
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		
		return words.subSet(prefix, AutoCompletion.getPrefixLimit(prefix));
	}
	

}
