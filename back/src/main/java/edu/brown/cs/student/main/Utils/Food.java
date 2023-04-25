package edu.brown.cs.student.main.Utils;

public class Food {

  private String food, description;
  private boolean isVegan, isVegetarian, isHalal, isKosher, isLactoseFree;
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

  public String toString() {
    return "Food: " + food + "\n Description: " + description;
  }
}
