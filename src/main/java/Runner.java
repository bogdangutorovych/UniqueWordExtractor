import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        Result result = new Result();
        StringParser stringParser = new StringParser();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/hp_all.txt"))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {

                if (!currentLine.isEmpty()) {
                    currentLine = stringParser.removeUnnecessarySymbols(currentLine);
                    String[] words = stringParser.getWordsFromLine(currentLine);
                    Arrays.stream(words).forEach(result::addWord);
                }
            }

            result.narrowResult(3, 9);
//            result.sortByValue(false);
            result.sortByKey();
            consoleWriter.print(result.getWords());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

