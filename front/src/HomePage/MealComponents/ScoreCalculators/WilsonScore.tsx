import { Review } from "../../../MenuResponse/ResponseHandling";

function WilsonScore(reviews: Array<Review>, meal: String) {
  let scores: Array<number> = [];
  reviews.map((review: Review) => {
    if (meal == review.item.title) {
      scores.push(review.item.rating);
    }
  });
  let totalReviews = scores.length;
  let pos = 0;
  scores.map((num: number) => {
    if (num >= 2.5) pos++;
  });
  if (totalReviews == 0) {
    return -1;
  }
  let phat = (1.0 * pos) / totalReviews;
  let z = 1.96;
  return (
    (phat +
      (z * z) / (2 * totalReviews) -
      z *
        Math.sqrt(
          (phat * (1 - phat) + (z * z) / (4 * totalReviews)) / totalReviews
        )) /
    (1 + (z * z) / totalReviews)
  );
}

export { WilsonScore };
