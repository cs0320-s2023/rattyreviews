import { NavBar } from "./../NavBar/NavBar";
import DateParser from "./Parsers/DateParser";
import MealBox from "./MealComponents/MealBox";

function HomePage() {
  return (
    <div id="Home">
      <NavBar />
      <MealBox dateString={"2023-05-01"} parser={DateParser} />
    </div>
  );
}

export default HomePage;
