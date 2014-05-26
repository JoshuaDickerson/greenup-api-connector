import model.Comment;
import model.PagedList;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by josh on 5/26/14.
 */
public class ApiConnectorTest {

    ApiConnector apiConnector = new ApiConnector();

    @Test
    public void testGetComments() throws URISyntaxException {
        URI uri = new URI("http://greenup.xenonapps.com:31337/api/comments");
        PagedList<Comment> results = apiConnector.getPagedComments(uri);
    }
}
