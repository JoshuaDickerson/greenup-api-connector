package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.lang.reflect.Type;

/**
 * Created by josh on 5/26/14.
 */
public class ResponseMapper {

    public static PagedList<Comment> mapResponseToCommentList(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<PagedComments<Comment>>(){}.getType());
    }

    private static PagedList<Comment> mapResponseToHeatpointList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<PagedHeatpoints<Heatpoint>>(){}.getType());
    }

    private static PagedList<Pin> mapResponseToPinList(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<PagedPins<Pin>>(){}.getType());
    }

    public static PagedList mapResponseToPagedList(String responseBody) {
        if(responseBody.toLowerCase().contains("comments")){
            return mapResponseToCommentList(responseBody);
        }else if(responseBody.toLowerCase().contains("grid")){
            return mapResponseToHeatpointList(responseBody);
        }else if(responseBody.toLowerCase().contains("pins")){
            return mapResponseToPinList(responseBody);
        }
        return null;
    }


}
