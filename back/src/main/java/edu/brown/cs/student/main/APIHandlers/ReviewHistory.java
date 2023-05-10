package edu.brown.cs.student.main.APIHandlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.main.dataStructures.reviewController;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ReviewHistory implements Route {

    private reviewController controller;
    private Map<String, Object> response;
    private final String exampleRevPATH = "src\\main\\java\\edu\\brown\\cs\\student\\main\\reviewData\\SampleReviews.json";
    public ReviewHistory(reviewController controller){
        this.controller = controller;
    }

    /**
     * Provides the history of all the past reviews given to menus at the Ratty and it automatically updates each time
     * someone adds a review
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        if(this.controller.getReviewDictionary() == null){
            Map<String, Object> someThing = new HashMap<>();
            someThing.put("result", "error_data_unavailable");
            someThing.put("error_data_unavailable", "Reviews are empty!");
            String defensiveCopy = new ViewFailureResponse().serialize(someThing);
            return defensiveCopy;
        }

        Map<String, Object> someThing = new HashMap<>();
        someThing.put("result", "success");
        someThing.put("reviews", this.controller.getStoredListOfReviews().reviews());
        String defensiveCopy = new ViewSuccessResponse().serialize(someThing);
        return defensiveCopy;
    }

    /**
     * This is the ViewSuccessResponse class that handles the success case of viewing where
     * we serialize our map
     */
    public record ViewSuccessResponse(String message) {
        public ViewSuccessResponse() {
            this("Successfully viewed the csv: ");
        }
        /**
         * @return this response, serialized as Json
         */
        String serialize(Map<String, Object> map) {
            try{
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<Map> adapter = moshi.adapter(Map.class);
                String defensiveCopy =  adapter.toJson(map);
                return defensiveCopy;
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }


    public record ViewFailureResponse(String error_type){
        public ViewFailureResponse(){this("Server ran into an error");}
        /**
         *
         * @param map the parameter map
         * @return defensiveCopy serialized as a JSON
         */
        String serialize(Map<String, Object> map){
            Moshi moshi = new Moshi.Builder().build();
            String defensiveCopy = moshi.adapter(Map.class).toJson(map);
            return defensiveCopy;
        }

    }
}
