import { useState } from "react";
import { FoodItem } from "../../MenuResponse/ResponseHandling";
import "../../styles/MealBox.css";
import Meal from "./Meal";
import { MdArrowDropDown } from "react-icons/Md";

interface MealDropDownProps {
  meal: Meal;
  score: Number;
  meals: Array<FoodItem>;
}

function MealDropDown(props: MealDropDownProps) {
  const [visibility, setVisibility] = useState(true);
  let mealString: String = "Invalid";
  switch (props.meal) {
    case Meal.Breakfast:
      mealString = "Breakfast Score";
      break;
    case Meal.Lunch:
      mealString = "Lunch Score";
      break;
    case Meal.Dinner:
      mealString = "Dinner Score";
      break;
  }
  return (
    <div className="all-meals-container">
      <div className="meal-container">
        <div className="meal-title meal-text">
          <p>{mealString}</p>
        </div>
        <div className="button-score-container">
          <div className="button-score">
            <div className="meal-score meal-text">
              <p>{props.score.toString()}</p>
            </div>
            <div className="dropdown-button-container">
              <button
                className="dropdown-button"
                onClick={() => setVisibility(!visibility)}
                style={{
                  transform: visibility ? "rotate(-90deg)" : "rotate(0)",
                }}
              >
                <MdArrowDropDown className="dropdown-icon" />
              </button>
            </div>
          </div>
        </div>
      </div>
      {props.meals.map((item: FoodItem) => {
        return (
          <div className="flex-container">
            <div
              className="food-container"
              style={{ display: visibility ? "none" : "flex" }}
            >
              <div className="title-rating-score">
                <div className="indiv-meal-title">{item.title}</div>
                <div className="rating-score">
                  <div className="rating">⭐️⭐️⭐️⭐️⭐️</div>
                  <div className="score">{item.rating.toString()}</div>
                </div>
              </div>
              <div className="description">{item.description}</div>
            </div>
          </div>
        );
      })}
    </div>
  );
}
export default MealDropDown;
