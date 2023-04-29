package edu.brown.cs.student.main.Utils;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Map;

public class Food {

  //using records to keep things flexible / keep me from pulling hair out
  public record Menu(
          Map<String, List<FoodItem>> menu
  ) {}

  public record FoodItem(
          String food, String description, double rating, List<String> foodRestrictions
  ) {}



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
