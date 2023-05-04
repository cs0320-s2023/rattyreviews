import "./../../styles/MealBox.css";
import Meal from "./Meal";
import MealDropDown from "./MealDropDown";

interface MealBoxProps {
  dateString: String;
  parser: (date: String) => String;
  menus: Map<String, Object>;
}

function MealBox(props: MealBoxProps) {
  let finalmenu: Map<String, Object>;
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
          <div className="meals-container">
            <MealDropDown meal={Meal.Breakfast} score={3} />
          </div>
          <div className="meals-container">
            <MealDropDown meal={Meal.Lunch} score={4} />
          </div>
          <div className="meals-container">
            <MealDropDown meal={Meal.Dinner} score={5} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MealBox;
