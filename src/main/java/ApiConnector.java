import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.ResponseMapper;
import model.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by josh on 5/26/14.
 */
public class ApiConnector {
    private HttpContext context;
    private Gson gson;

    public ApiConnector(){
        HttpContext defaultContext = new BasicHttpContext();
        this.context = HttpClientContext.adapt(defaultContext);
        this.gson = new Gson();

    }

    // here's a generic class for getting all of our paged data (heatmaps, pins, comments)
    public PagedList getPagedData(URI uri){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet get = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(get, context);
            String responseBody = getBodyFromEntity(response.getEntity());
            return ResponseMapper.mapResponseToPagedList(responseBody);
        } catch (Exception e) {
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

    // class to handle all POSTS
    public ApiResponse postData(List dataItems) throws DataFormatException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            if (dataItems != null && !dataItems.isEmpty()) {
                // here we're going to use reflection to determine the type (or subtype) of the objects
                // we passed in the list, and call the appropriate JSON endpoint
                Object ob = dataItems.get(0);
                HttpPost post = new HttpPost(getUriForObject(ob));
                String json = gson.toJson(dataItems);
                post.setEntity(new StringEntity(json));
                CloseableHttpResponse response = httpClient.execute(post, context);
                String responseBody = getBodyFromEntity(response.getEntity());
                return gson.fromJson(responseBody, ApiResponse.class);
            } else {
                throw new DataFormatException("Empty or NULL list was passed");
            }
        }finally {
            httpClient.close();
        }
    }

    // class to handle pin puts
    public ApiResponse putPin(String pinId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPut put = new HttpPut(Constants.API_URL.pins + "?id=" + pinId);
            Map<String, String> map = newHashMap();
            map.put("addressed", "true");
            String body = gson.toJson(map);
            put.setEntity(new StringEntity(body));
            CloseableHttpResponse response = httpClient.execute(put, context);
            String responseBody = getBodyFromEntity(response.getEntity());
            return gson.fromJson(responseBody, ApiResponse.class);
        }finally {
            httpClient.close();
        }
    }

    public boolean deletePin(String pinId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete delete = new HttpDelete(Constants.API_URL.pins + "?id=" + pinId);
            CloseableHttpResponse response = httpClient.execute(delete, context);
            return response.getStatusLine().getStatusCode() < 210;
        }finally {
            httpClient.close();
        }
    }

    private String getUriForObject(Object ob) throws DataFormatException {
        if (ob.getClass().isAssignableFrom(Heatpoint.class)) {
            return Constants.API_URL.heatmaps;
        } else if (ob.getClass().isAssignableFrom(Pin.class)) {
            return Constants.API_URL.pins;
        } else if (ob.getClass().isAssignableFrom(Comment.class)) {
            return Constants.API_URL.comments;
        } else {
            // todo: log handling
            throw new DataFormatException("model must be either heatmap, pin, or comment");
        }
    }


    // utility method to get the string body
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
