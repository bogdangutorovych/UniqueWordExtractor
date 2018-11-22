import java.util.*;
import java.util.stream.Collectors;

public class Result {
    private Map<String,Integer> words;

    public void setWords(Map<String, Integer> words) {
        this.words = words;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public Result() {
        this.words = new LinkedHashMap<>();
    }

    public void addWord(String word) {
        if (!words.containsKey(word)) {
            words.put(word, 1);
        } else {
            int newValue = words.get(word) + 1;
            words.put(word, newValue);
        }
    }

    public void narrowResult(int wordLength, int wordNumber) {
        words.entrySet().removeIf(
                e -> e.getKey().length() < wordLength || e.getValue() <= wordNumber);
    }

    public void sortByValueDesc() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<String, Integer> sortedMap = list.stream().collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (v1,v2)->v1, LinkedHashMap::new));

        setWords(sortedMap);
    }

}
