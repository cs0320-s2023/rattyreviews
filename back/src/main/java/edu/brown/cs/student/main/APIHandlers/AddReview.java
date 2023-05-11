package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.dataStructures.Food;
import edu.brown.cs.student.main.dataStructures.Review;
import edu.brown.cs.student.main.dataStructures.inputtedReview;
import edu.brown.cs.student.main.dataStructures.reviewController;
import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.nio.charset.Charset;

public class AddReview implements Route {

    private reviewController controller;

    public AddReview (reviewController controller) {this.controller = controller;}

    /**
     *
     * @param request the request sent to the server
     * @param response the response that server sends back
     * @return the serialized object
     * @throws Exception if the userID is not found
     */
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
