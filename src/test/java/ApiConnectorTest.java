import model.*;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import static com.google.common.collect.Lists.newArrayList;
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
        URI uri = new URI(Constants.API_URL.comments);
        PagedList<Comment> results = apiConnector.getPagedData(uri);
        // all comments must be associated with a type
        MatcherAssert.assertThat(results.getWrapped().get(0).getType(), notNullValue());
    }

    @Test
    public void testGetHeatmap() throws URISyntaxException {
        URI uri = new URI(Constants.API_URL.heatmaps);
        PagedList<Heatpoint> results = apiConnector.getPagedData(uri);
        // we make we have a heatmap point, and that it has a valid latitude
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), notNullValue());
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), not(0f));
    }

    @Test
    public void testGetPins() throws URISyntaxException {
        URI uri = new URI(Constants.API_URL.pins);
        PagedList<Pin> results = apiConnector.getPagedData(uri);
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), notNullValue());
        MatcherAssert.assertThat(results.getWrapped().get(0).getLatDegrees(), not(0f));
    }

    @Test
    public void postComments() throws DataFormatException, IOException {
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(new Comment(Constants.COMMENT_TYPE.ADMIN, "test", "2014-01-01 11:11:11", "1", 1l));
        ApiResponse response1 = apiConnector.postData(comments);
        MatcherAssert.assertThat(response1.getMessage(), is("Succesfully submited new comment"));
    }

    @Test
    public void postPins() throws DataFormatException, IOException {
        List<Pin> pins = new ArrayList<Pin>();
        Pin p = new Pin();
        p.setLatDegrees(75.00f);
        p.setLonDegrees(-45.00f);
        p.setMessage("test");
        p.setType(Constants.COMMENT_TYPE.ADMIN);
        p.setAddressed(false);
        pins.add(p);
        ApiResponse response = apiConnector.postData(pins);
        MatcherAssert.assertThat(response.getMessage(), is("Successful submit"));
    }

    @Test
    public void deletePins() throws DataFormatException, IOException, URISyntaxException {
        PagedList<Pin> pins = apiConnector.getPagedData(new URI(Constants.API_URL.pins));
        List<Pin> wrapped = pins.getWrapped();
        boolean response = false;
        if(wrapped != null && !wrapped.isEmpty()){
            String pinId = String.valueOf(wrapped.get(0).getId());
            response = apiConnector.deletePin(pinId);
        }

        MatcherAssert.assertThat(response, is(true));
    }
}
