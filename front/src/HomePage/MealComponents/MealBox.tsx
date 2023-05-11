import { Dispatch, SetStateAction, useEffect, useState } from "react";
import {
  FoodItem,
  FullMenuResponse,
  Review,
} from "../../MenuResponse/ResponseHandling";
import "./../../styles/MealBox.css";
import Meal from "./Meal";
import MealDropDown from "./MealDropDown";
import { MdArrowDropDown, MdArrowLeft, MdArrowRight } from "react-icons/Md";
import { AverageScore } from "./ScoreCalculators/AverageScore";

interface MealBoxProps {
  date: Date;
  parser: (date: String) => String;
  menu: FullMenuResponse;
  setDate: Dispatch<SetStateAction<Date>>;
  reviews: Array<Review>;
}

function MealBox(props: MealBoxProps) {
  const [filterKey, setFilterKey] = useState("none");
  let dietRestrictions: Array<String> = [
    "none",
    "vegetarian",
    "vegan",
    "without gluten",
    "halal",
  ];
  return (
    <div className="meal-control-container">
      <div className="left-arrow-container">
        <MdArrowLeft
          className="left-arrow"
          aria-label="Backwards in date by one Button"
          onClick={() => {
            //TODO: CLEAN THIS UP
            let lowerDateBound = new Date(
              new Date().setDate(new Date().getDate() - 1)
            ).getTime();
            if (props.date.getTime() >= lowerDateBound) {
              props.setDate(
                new Date(props.date.setDate(props.date.getDate() - 1))
              );
            }
          }}
        />
      </div>
      <div className="meal-box-container">
        <div id="meal-box">
          <div className="title-line-container">
            <div className="date-text-container">
              <div className="date-text">
                {props.parser(props.date.toISOString().split("T")[0])}
              </div>
            </div>
            <div className="info-icon">
              <button
                style={{ all: "unset" }}
                onClick={() =>
                  (location.href = "../../../public/Algorithms_Used.pdf")
                }
              >
                ℹ️
              </button>
            </div>
          </div>
          <div className="filter-container">
            <select
              name="filter"
              onChange={(event) => setFilterKey(event.target.value)}
            >
              {dietRestrictions.map((restriction: String) => (
                <option>{restriction}</option>
              ))}
            </select>
          </div>
          <div className="dropdown-container">
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Breakfast}
                score={
                  AverageScore(props.reviews, props.menu.breakfast) < 0
                    ? "N/A"
                    : AverageScore(props.reviews, props.menu.breakfast).toFixed(
                        1
                      )
                }
                meals={
                  filterKey == "none"
                    ? props.menu.breakfast
                    : props.menu.breakfast.filter((item: FoodItem) =>
                        item.foodRestrictions.includes(filterKey)
                      )
                }
                reviews={props.reviews}
              />
            </div>
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Lunch}
                score={
                  AverageScore(props.reviews, props.menu.lunch) < 0
                    ? "N/A"
                    : AverageScore(props.reviews, props.menu.lunch).toFixed(1)
                }
                meals={
                  filterKey == "none"
                    ? props.menu.lunch
                    : props.menu.lunch.filter((item: FoodItem) =>
                        item.foodRestrictions.includes(filterKey)
                      )
                }
                reviews={props.reviews}
              />
            </div>
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Dinner}
                score={
                  AverageScore(props.reviews, props.menu.dinner) < 0
                    ? "N/A"
                    : AverageScore(props.reviews, props.menu.dinner).toFixed(1)
                }
                meals={
                  filterKey == "none"
                    ? props.menu.dinner
                    : props.menu.dinner.filter((item: FoodItem) =>
                        item.foodRestrictions.includes(filterKey)
                      )
                }
                reviews={props.reviews}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="right-arrow-container">
        <MdArrowRight
          aria-label="Forwards in date by one Button"
          className="right-arrow" //TODO: CLEAN THIS UP
          onClick={() => {
            let upperDateBound = new Date(
              new Date().setDate(new Date().getDate() + 6)
            ).getTime();
            if (props.date.getTime() <= upperDateBound) {
              props.setDate(
                new Date(props.date.setDate(props.date.getDate() + 1))
              );
            }
          }}
        />
      </div>
    </div>
  );
}

export default MealBox;
