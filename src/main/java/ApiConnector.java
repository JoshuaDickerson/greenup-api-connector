import helper.ResponseMapper;
import model.Comment;
import model.PagedList;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by josh on 5/26/14.
 */
public class ApiConnector {
    public PagedList<Comment> getPagedComments(URI uri){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpContext defaultContext = new BasicHttpContext();
            HttpClientContext context = HttpClientContext.adapt(defaultContext);
            HttpGet get = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(get, context);
            String responseBody = getBodyFromEntity(response.getEntity());
            return ResponseMapper.mapResponseToCommentList(responseBody);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // todo: log error
            }
        }
    }

    private String getBodyFromEntity(HttpEntity entity){
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(entity.getContent(), writer);
            String theString = writer.toString();
            return theString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
