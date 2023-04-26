package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.server.MapSerializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

import static edu.brown.cs.student.main.webscraping.ScrapeDiningMenu.getAllMenus;
import java.util.List;
import edu.brown.cs.student.main.Utils.Food;


public class ProvideMenu implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String, Food.Menu> menuCollection = getAllMenus();
        return(MapSerializer.toJsonGeneric(menuCollection, Food.Menu.class));
    }
}
