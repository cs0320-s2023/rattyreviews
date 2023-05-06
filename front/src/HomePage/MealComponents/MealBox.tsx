import { Dispatch, SetStateAction } from "react";
import { FullMenuResponse } from "../../MenuResponse/ResponseHandling";
import "./../../styles/MealBox.css";
import Meal from "./Meal";
import MealDropDown from "./MealDropDown";
import { MdArrowDropDown } from "react-icons/Md";

interface MealBoxProps {
  date: Date;
  parser: (date: String) => String;
  menu: FullMenuResponse;
  setDate: Dispatch<SetStateAction<Date>>;
}

function MealBox(props: MealBoxProps) {
  return (
    <div className="meal-control-container">
      <div className="left-arrow-container">
        <MdArrowDropDown
          className="left-arrow"
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
              <p className="date-text">
                {props.parser(props.date.toISOString().split("T")[0])}
              </p>
            </div>
            <div className="info-icon">
              <p>ℹ️</p>
            </div>
          </div>
          <div className="dropdown-container">
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Breakfast}
                score={3}
                meals={props.menu.breakfast}
              />
            </div>
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Lunch}
                score={4}
                meals={props.menu.lunch}
              />
            </div>
            <div className="meals-container">
              <MealDropDown
                meal={Meal.Dinner}
                score={5}
                meals={props.menu.dinner}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="right-arrow-container">
        <MdArrowDropDown
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
