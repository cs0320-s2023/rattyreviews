package edu.brown.cs.student.main.server;

import static edu.brown.cs.student.main.webscraping.ScrapeDiningMenu.getAllMenus;
import static spark.Spark.after;

import edu.brown.cs.student.main.APIHandlers.AddReview;
import edu.brown.cs.student.main.APIHandlers.ProvideMenu;
import edu.brown.cs.student.main.APIHandlers.ReviewHistory;
import edu.brown.cs.student.main.Utils.reviewController;
import spark.Spark;

public class Server {
  public static void main(String[] args) {
    Spark.port(3232);

    /*
       Setting cross-origin requests from any source for now, since this server only exists in a very localized format
    */
    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "GET");
        });

    Spark.init();
    Spark.awaitInitialization();
    //would love to add caching to this, so we don't need to call on dining.brown.edu every time someone loads our page LMAO
      //like it takes a full 6 seconds on a good connection to rerun the scraping. not bad if it's in the background but
      //VERY bad if it does that on every load


  // Once the frontend is further along we can decide how we want to populate the review dictionary
    reviewController reviewController = new reviewController();
    Spark.get("menus", new ProvideMenu());
    Spark.get("reviewHistory", new ReviewHistory(reviewController));
    Spark.post("addReview", new AddReview(reviewController));
    System.out.println("Server started.");
  }
}
