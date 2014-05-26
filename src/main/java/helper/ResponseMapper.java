package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Comment;
import model.PagedList;

/**
 * Created by josh on 5/26/14.
 */
public class ResponseMapper {

    public static PagedList<Comment> mapResponseToCommentList(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<PagedList<Comment>>(){}.getType());
    }
}
