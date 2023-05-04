import { NavBar } from "./../NavBar/NavBar";
import DateParser from "./Parsers/DateParser";
import MealBox from "./MealComponents/MealBox";

interface HomePageProps {
  menus: Map<String, Object>;
}

function HomePage(props: HomePageProps) {
  console.log(props.menus);
  return (
    <div id="Home">
      <NavBar />
      <MealBox
        dateString={"2023-05-01"}
        parser={DateParser}
        menus={props.menus}
      />
    </div>
  );
}

export default HomePage;
