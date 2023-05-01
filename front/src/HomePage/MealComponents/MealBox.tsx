import "./../../styles/MealBox.css";
import Meal from "./Meal";
import MealDropDown from "./MealDropDown";

interface MealBoxProps {
  dateString: String;
  parser: (date: String) => String;
}

function MealBox(props: MealBoxProps) {
  return (
    <div className="meal-box-container">
      <div id="meal-box">
        <div className="title-line-container">
          <div className="date-text-container">
            <p className="date-text">{props.parser(props.dateString)}</p>
          </div>
          <div className="info-icon">
            <p>ℹ️</p>
          </div>
        </div>
        <div className="dropdown-container">
          <div className="breakfast-container">
            <MealDropDown meal={Meal.Breakfast} />
          </div>
          <div className="lunch-container">
            <MealDropDown meal={Meal.Lunch} />
          </div>
          <div className="dinner-container">
            <MealDropDown meal={Meal.Dinner} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MealBox;
