import java.util.Map;

public class ConsoleWriter {

    public void print(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
        System.out.println("Total size is " + map.size());

    }
}
