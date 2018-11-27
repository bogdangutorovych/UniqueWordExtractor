import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class LingvoApiImpl implements LingvoApi {

    private static final String SERVICE_URL = "https://developers.lingvolive.com";
    private static final String AUTH_URL = "/api/v1/authenticate";
    private static final String MINICARD_URL = "/api/v1/Minicard";
    private static final String TRANSLATION_URL = "/api/v1/Translation";
    private static final String API_KEY = "MjZjYzNlMDYtZDM1OS00Njk2LTlhNDItZWVjNDM4NzJlYmYzOjI3NDE3ODRjNDE2YjQ1OGFiOTQ4MmE3ZjE4ODlmYjM5";
    private static String TOKEN = "ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxlSEFpT2pFMU5ETXpNakEyTlRVc0lrMXZaR1ZzSWpwN0lrTm9ZWEpoWTNSbGNuTlFaWEpFWVhraU9qVXdNREF3TENKVmMyVnlTV1FpT2pFNU1EVXNJbFZ1YVhGMVpVbGtJam9pTWpaall6TmxNRFl0WkRNMU9TMDBOamsyTFRsaE5ESXRaV1ZqTkRNNE56SmxZbVl6SW4xOS5KX0JNakZyWkNwSU8xWVFWaGItdUVLYXZtUmdPNE9sQkZfazdNSlZwQUtn";
    private static final String SRC_LANG = "1033";
    private static final String DST_LANG = "1049";

    @Override
    public String auth() throws IOException, URISyntaxException {
        return resultOfPostRequest(SERVICE_URL + AUTH_URL);
    }

    @Override
    public String minicard(String text) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(SERVICE_URL + MINICARD_URL);
        setupLanguageParams(builder).setParameter("text", text);
        return resultOfGetRequest(String.valueOf(builder));
    }

    @Override
    public String translation(String text) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(SERVICE_URL + TRANSLATION_URL);
        setupLanguageParams(builder).setParameter("text", text);
        return resultOfGetRequest(String.valueOf(builder));
    }

    public String resultOfGetRequest(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        String authHeader = "Bearer " + TOKEN;
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        String responseContent = EntityUtils.toString(
                response.getEntity(), StandardCharsets.UTF_8.name());
        return responseContent;
    }

    public String resultOfPostRequest(String url) throws IOException {
        HttpPost request = new HttpPost(url);
        String authHeader = "Basic " + API_KEY;
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        String responseContent = EntityUtils.toString(
                response.getEntity(), StandardCharsets.UTF_8.name());
        return responseContent;
    }

    private URIBuilder setupLanguageParams(URIBuilder builder) {
        return builder.setParameter("srcLang", SRC_LANG)
                .setParameter("dstLang", DST_LANG);
    }
}
