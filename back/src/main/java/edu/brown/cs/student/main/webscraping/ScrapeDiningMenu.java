package edu.brown.cs.student.main.webscraping;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import edu.brown.cs.student.main.Utils.Food;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

//TODO: use data from https://mymeal.brown.edu/NetNutrition/ to determine allergens
//TODO: have this be triggered once a day @ midnight for caching into a db

public class ScrapeDiningMenu {

  public static Map<String, Food.Menu> getAllMenus() {
    WebClient webClient = new WebClient(BrowserVersion.CHROME);
    // v Quiets all the exceptions that we don't care about, especially related to ads.
    webClient.getOptions().setCssEnabled(false);
    // webClient.getOptions().setJavaScriptEnabled(false);
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.getOptions().setPrintContentOnFailingStatusCode(false);

    try {
      /*This might fail due to SSL errors, since the standard installation of Java does not include the InCommon certificate
       * that dining.brown.edu uses. This is normal! You need to install the InCommon cert for your version of Java. This
       * StackOverflow post gives a good guide on how to achieve this, ask Connor if you need help: */
      // https://stackoverflow.com/questions/9619030/resolving-javax-net-ssl-sslhandshakeexception-sun-security-validator-validatore


//      System.out.println(Year.now());
//
//      Calendar rightNow = Calendar.getInstance();
//      System.out.println(rightNow.get(Calendar.YEAR));
//      System.out.println(rightNow.get(Calendar.MONTH));
//      System.out.println(rightNow.get(Calendar.DAY_OF_MONTH));
//      System.out.println(rightNow);

      //TODO: have date be a passed in param,
          //currently relying on LocalDate.now() since all team members are in EST + its very likely dining.brown.edu uses that as well
      HtmlPage page =
          webClient.getPage("http://dining.brown.edu/cafe/sharpe-refectory/" + LocalDate.now());
      webClient.getCurrentWindow().getJobManager().removeAllJobs();
      webClient.close();

      String title = page.getTitleText();
      System.out.println("Page Title: " + title);

      List<HtmlElement> allDayParts =
          page.getByXPath(
              "//div[@class='c-tab__content-inner site-panel__daypart-tab-content-inner']");
      System.out.println(allDayParts.size());

      List<HtmlElement> breakfastMenu = allDayParts.get(0).getByXPath("*");
      List<HtmlElement> lunchMenu = allDayParts.get(1).getByXPath("*");
      List<HtmlElement> dinnerMenu = allDayParts.get(2).getByXPath("*");

      // might want to make this immutable to make this more defensive lmao
      Food.Menu rattyBreakfastFoods = buildMenu(breakfastMenu);
      Food.Menu rattyLunchFoods = buildMenu(lunchMenu);
      Food.Menu rattyDinnerFoods = buildMenu(dinnerMenu);

      System.out.println(rattyBreakfastFoods);
      System.out.println(rattyLunchFoods);
      System.out.println(rattyDinnerFoods);

      Map<String, Food.Menu> finalMenus = new HashMap<>();
      finalMenus.put("Breakfast", rattyBreakfastFoods);
      finalMenus.put("Lunch", rattyLunchFoods);
      finalMenus.put("Dinner", rattyDinnerFoods);
      return finalMenus;

    } catch (IOException e) {
      System.out.println("An error occurred: " + e);
    }
    return null;
  }


  private static Food.Menu buildMenu(List<HtmlElement> htmlMenu) {
    Map<String, List<Food.FoodItem>> accMenu = new HashMap<>();
    String lastStation = "";

    for (HtmlElement item : htmlMenu) {
      if (item.getFirstChild().getNodeName().equals("h3")) {
        List<HtmlElement> sectionHeaderItems =
                item.getByXPath(".//div[@class='site-panel__daypart-item']");
        List<Food.FoodItem> firstFoodItems = new ArrayList<>();
        for (HtmlElement targElem : sectionHeaderItems) {
          Food.FoodItem newFood = extractHtmlFood(targElem);
          firstFoodItems.add(newFood);
        }
        lastStation = item.getFirstChild().getVisibleText();
        accMenu.put(lastStation, firstFoodItems);

      } else {
        // defensive copy
        List<Food.FoodItem> updateList = new ArrayList<Food.FoodItem>(accMenu.get(lastStation));
        Food.FoodItem newFood = extractHtmlFood(item);
        updateList.add(newFood);
        accMenu.put(lastStation, updateList);
      }
    }

    return new Food.Menu(accMenu);
  }

  private static Food.FoodItem extractHtmlFood(HtmlElement item) {
    HtmlElement htmlTitle = item.getFirstByXPath(".//button[@class='h4 site-panel__daypart-item-title']");
    String foodTitle = htmlTitle.getVisibleText();

    HtmlElement htmlDesc = item.getFirstByXPath(".//div[@class='site-panel__daypart-item-description']");
    String foodDesc = "";
    if(htmlDesc != null) {
      foodDesc = htmlDesc.getVisibleText();
    }

    //TODO: add dietary restrictions

    //TODO: pull rating so it's not sentinal val.
    return new Food.FoodItem(foodTitle, foodDesc, -1, new HashMap<>());
  }

}



