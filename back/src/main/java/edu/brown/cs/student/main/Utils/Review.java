package edu.brown.cs.student.main.Utils;

public class Review {
  private String UserID;
  private String comment;
  private double star_Rating;

  //TODO: refactor to disambig names
  public Review(String name_of_reviewer, String comment, double star_Rating) {
    this.UserID = name_of_reviewer;
    this.comment = comment;
    this.star_Rating = star_Rating;
  }

  public record foodReview(String UserID, String time, Food.FoodItem item, String comment){}
  //would REALLY like to have a separate, relational db for comments
  // also need to adjust times to not just be a string

  public String getUserID() {
    return this.UserID;
  }

  public String getComment() {
    return this.comment;
  }

  public double getStar_Rating() {
    return this.star_Rating;
  }

  public String toString() {
    return "Reviewer:"
        + this.UserID
        + "\n comment: "
        + this.comment
        + "\n starRating: "
        + this.star_Rating;
  }
}
