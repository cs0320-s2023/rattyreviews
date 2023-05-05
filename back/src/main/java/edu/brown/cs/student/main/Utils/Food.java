package edu.brown.cs.student.main.Utils;

import java.util.List;
import java.util.Map;

public class Food {

  public record FullMenuResponse(String result, String expire, Map<String, Menu> menus) {}

  public record Menu(
          Map<String, List<FoodItem>> menu
  ) {}

  public record FoodItem(
          String title, String description, double rating, List<String> foodRestrictions
  ) {
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      FoodItem target = (FoodItem) o;
      return (target.title.equals(this.title) && target.description.equals(this.description)
              && target.foodRestrictions.containsAll(this.foodRestrictions) && this.foodRestrictions.containsAll(target.foodRestrictions));
      //goal here is to be insensitive to rating. title, desc, and restrictions are indicative of what this item actually IS, so we want that to stay consistent.
    }

  }
}
