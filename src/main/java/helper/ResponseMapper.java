package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Comment;
import model.PagedComments;
import model.PagedList;

import java.lang.reflect.Type;

/**
 * Created by josh on 5/26/14.
 */
public class ResponseMapper {

    public static PagedList<Comment> mapResponseToCommentList(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<PagedComments<Comment>>(){}.getType());
    }

    public static PagedList mapResponseToPagedList(String responseBody) {
        if(responseBody.toLowerCase().contains("comments")){
            return mapResponseToCommentList(responseBody);
        }
        return null;
    }
}
