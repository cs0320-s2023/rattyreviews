package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.Utils.reviewController;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddReview implements Route {

    private reviewController controller;

    public AddReview (reviewController controller) {this.controller = controller;}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        //TODO: dont die! confirm the structure of the post and then convert to summaryReview
        System.out.println(request.body());
        request.body();

        return null;
    }
}
