package edu.brown.cs.student.main.APIHandlers;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonEncodingException;
import edu.brown.cs.student.main.Utils.Food;
import edu.brown.cs.student.main.Utils.Review;
import edu.brown.cs.student.main.Utils.inputtedReview;
import edu.brown.cs.student.main.Utils.reviewController;
import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;
import okio.BufferedSource;
import spark.Request;
import spark.Response;
import spark.Route;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class AddReview implements Route {

    private reviewController controller;

    public AddReview (reviewController controller) {this.controller = controller;}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Buffer neededBuff = new Buffer();
        neededBuff.writeString(request.body(), Charset.defaultCharset());
        //TODO: better err handling
        try {
            inputtedReview.SummaryReview sumRev = MapSerializer.fromJsonGeneric(neededBuff, inputtedReview.SummaryReview.class);
            for (Food.FoodItem item : sumRev.Ratings()) {
                Review.foodReview someRev = new Review.foodReview(sumRev.UserID(), sumRev.Date(), item, sumRev.comment());
                controller.insertReview(someRev);
            }
            System.out.println(controller);
            controller.addToStorage();
            return MapSerializer.simpleSuccessResponse("message", controller.toString());
        } catch (JsonDataException dataException) {
            System.out.println(dataException);
        } catch (JsonEncodingException encodingException) {
            System.out.println(encodingException);
        }

        return request.body();
    }
}
