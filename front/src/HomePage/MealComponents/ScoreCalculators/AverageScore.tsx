import { FoodItem, Review } from "../../../MenuResponse/ResponseHandling";

function AverageScore(reviews: Array<Review>, meals: Array<FoodItem>) {
  let score = 0;
  let counter = 0;
  reviews.map((review: Review) => {
    meals.map((item: FoodItem) => {
      if (review.item.title == item.title) {
        score += review.item.rating;
        counter++;
      }
    });
  });
  if (counter == 0) {
    return -1;
  }
  return score / counter;
}

export { AverageScore };
