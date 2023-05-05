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

import java.io.IOException;
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
        try {
            inputtedReview.SummaryReview sumRev = MapSerializer.fromJsonGeneric(neededBuff, inputtedReview.SummaryReview.class);
            if (sumRev.UserID() == null || sumRev.Date() == null) {
                throw new IOException();
            }
            for (Food.FoodItem item : sumRev.Ratings()) {
                Review.foodReview someRev = new Review.foodReview(sumRev.UserID(), sumRev.Date(), item, sumRev.comment());
                controller.insertReview(someRev);
            }
            System.out.println(controller);
            return MapSerializer.simpleSuccessResponse("message", controller.toString());
        } catch (IOException ioe) {
            return MapSerializer.simpleFailureResponse("error_bad_body", "Request body was malformed or unreadable. Please alert developers. IO Error was " + ioe.toString());
        } catch (Exception e) {
            return MapSerializer.exceptionalFailureResponse(e);
        }
    }
}
