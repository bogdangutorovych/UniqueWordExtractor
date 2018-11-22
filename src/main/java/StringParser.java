public class StringParser {

    public String removeUnnecessarySymbols(String line) {
        return line.replaceAll("[^\\w\\s\\-]", "");
    }

    public String[] getWordsFromLine(String line) {
        return line.toLowerCase().split("\\s+");
    }
}
