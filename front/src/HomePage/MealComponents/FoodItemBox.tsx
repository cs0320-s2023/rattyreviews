import { useState } from "react";
import { FoodItem } from "../../MenuResponse/ResponseHandling";
import "../../styles/MealBox.css";

interface FoodItemBoxProps {
  item: FoodItem;
  visibility: Boolean;
  score: number;
}

function FoodItemBox(props: FoodItemBoxProps) {
  let rating: String = "";
  if (props.score != -1) {
    for (let i = 0; i < Math.round(props.score); i++) {
      rating += "⭐️";
    }
  }
  let lastIndex = props.item.foodRestrictions.length - 1;
  return (
    <div className="flex-container">
      <div
        className="food-container"
        style={{ display: props.visibility ? "none" : "flex" }}
      >
        <div className="title-rating-score">
          <div className="indiv-meal-title">{props.item.title}</div>
          <div className="rating-score">
            <div className="rating">{rating}</div>
            <div className="score">{props.score < 0 ? ("N/A") : (props.score.toFixed(1))}</div>
          </div>
        </div>
        <div className="description">
          {props.item.description}
          <br />
          {"Dietary Restrictions: "}
          {props.item.foodRestrictions
            .slice(0, lastIndex)
            .map((name: String) => name + ", ")}
          {props.item.foodRestrictions[lastIndex]}
        </div>
      </div>
    </div>
  );
}

export { FoodItemBox };
