package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.Utils.Food;
import edu.brown.cs.student.main.Utils.Review;
import edu.brown.cs.student.main.Utils.reviewController;
import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetReviewsFor implements Route {

    private reviewController controller;

    public GetReviewsFor (reviewController controller) {this.controller = controller;}


    @Override
    public Object handle(Request request, Response response) throws Exception {
        /**must be in format YYYY-MM-DD*/
        LocalDate asked = LocalDate.parse(request.queryParamOrDefault("date", LocalDate.now(ZoneId.of("UTC-04:00")).toString()));
        String menuStr = new ProvideMenu().GetMenuForDate(asked);
        Buffer helperBuff = new Buffer().writeString(menuStr, Charset.defaultCharset());
        Food.FullMenuResponse fullMenu = MapSerializer.fromJsonGeneric(helperBuff, Food.FullMenuResponse.class);
        List<Review.foodReview> allReviews = controller.getStoredListOfReviews().reviews();
        List<Review.foodReview> accReviews = new ArrayList<>();

        for (Food.Menu menu: fullMenu.menus().values()) {
            for (List<Food.FoodItem> station : menu.menu().values()) {
                for (Food.FoodItem fooIt: station) {
                    List<Review.foodReview> revMatches = allReviews.stream().filter(rev -> rev.item().equals(fooIt)).toList();
                    accReviews.addAll(revMatches);
                }
            }
        }
        Map<String, Object> finalMap = new HashMap<>();
        finalMap.put("result", "success");
        finalMap.put("reviews", accReviews);
        return MapSerializer.toJson(finalMap);
        //return MapSerializer.toJsonTotalGeneric(new Review.listOfFoodReview(accReviews), Review.listOfFoodReview.class);
    }
}
