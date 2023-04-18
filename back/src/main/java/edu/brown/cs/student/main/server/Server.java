package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.APIHandlers.RLData.RLDataHandler;
import edu.brown.cs.student.main.APIHandlers.annotations.AnnotationHandler;
import edu.brown.cs.student.main.APIHandlers.annotations.ClearAnnotationsHandler;
import edu.brown.cs.student.main.APIHandlers.annotations.GetAnnotationsHandler;
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
    System.out.println("Server started.");
  }
}
