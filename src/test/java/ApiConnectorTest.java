import model.Comment;
import model.Heatpoint;
import model.PagedList;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by josh on 5/26/14.
 */
public class ApiConnectorTest {

    ApiConnector apiConnector = new ApiConnector();

    @Test
    public void testGetComments() throws URISyntaxException {
        URI uri = new URI("http://greenup.xenonapps.com:31337/api/comments");
        PagedList<Comment> results = apiConnector.getPagedData(uri);
        // all comments must be associated with a type
        MatcherAssert.assertThat(results.getWrapped().get(0).getType(), notNullValue());
    }

    @Test
    public void testGetHeatmap() throws URISyntaxException {
        URI uri = new URI("http://greenup.xenonapps.com:31337/api/heatmap");
        PagedList<Heatpoint> results = apiConnector.getPagedData(uri);
        // we make we have a heatmap point, and that it has a valid latitude
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), notNullValue());
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), not(0f));
    }
}
