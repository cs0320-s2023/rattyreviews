import { Review } from "../../../MenuResponse/ResponseHandling";

function WilsonScore(reviews: Array<Review>, meal: String) {
  let scores: Array<number> = [];
  reviews.map((review: Review) => {
    if (meal == review.item.title) {
      scores.push(review.item.rating);
    }
  });
  let neg = 0;
  let pos = 0;
  scores.map((num: number) => {
    if (num >= 0 && num < 1) {
      neg += 1;
    } else if (num >= 1 && num < 2) {
      pos += 0.25;
      neg += 0.75;
    } else if (num >= 2 && num < 3) {
      pos += 0.5;
      neg += 0.5;
    } else if (num >= 3 && num < 4) {
      pos += 0.75;
      neg += 0.25;
    } else if (num >= 4 && num <= 5) {
      pos++;
    }
  });
  if (pos + neg == 0) {
    return -1;
  }
  return (((pos / (pos + neg)) * 4 + 1) * 2) / 2;
}

export { WilsonScore };
