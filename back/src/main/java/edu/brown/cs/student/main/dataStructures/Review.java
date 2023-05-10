package edu.brown.cs.student.main.dataStructures;

import java.util.List;

public class Review {

  public record listOfFoodReview(List<foodReview> reviews) {};

  public record foodReview(String UserID, String time, Food.FoodItem item, String comment){}
  //would REALLY like to have a separate, relational db for comments
  // also need to adjust times to not just be a string
}
