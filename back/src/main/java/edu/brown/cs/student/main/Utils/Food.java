package edu.brown.cs.student.main.Utils;

import java.util.List;
import java.util.Map;

public class Food {

  //using records to keep things flexible / keep me from pulling hair out
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

  private String food, description;
  private boolean isVegan, isVegetarian, isHalal, isKosher, isLactoseFree;

  //TODO: Make rating optional
  private double rating;

  public Food(String food, String description, double rating) {
    this.food = food;
    this.description = description;
    this.rating = rating;
  }

  public Food(String food, double rating) {
    this.food = food;
    this.rating = rating;
  }

  public Food(
      String food,
      String description,
      boolean isVegan,
      boolean isVegetarian,
      boolean isHalal,
      boolean isKosher,
      boolean isLactoseFree,
      double rating) {

    this.food = food;
    this.description = description;
    this.isVegan = isVegan;
    this.isVegetarian = isVegetarian;
    this.isHalal = isHalal;
    this.isKosher = isKosher;
    this.isLactoseFree = isLactoseFree;
    this.rating = rating;
  }

  public Food(String food, String description) {
    this.food = food;
    this.description = description;
    this.rating = -1;
  }

  public String toString() {
    return "Food: " + food + "\t Description: " + description;
  }
}
