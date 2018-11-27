import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LingvoApi {

    public String auth() throws IOException, URISyntaxException;

    public String minicard(String text) throws IOException, URISyntaxException;

    public String translation(String text) throws IOException, URISyntaxException;

}
