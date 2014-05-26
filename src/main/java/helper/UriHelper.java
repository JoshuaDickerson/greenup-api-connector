package helper;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by josh on 5/26/14.
 */
public class UriHelper {
    public static URI getUriForString(String url) throws URISyntaxException {
        return new URI(url);
    }
}
