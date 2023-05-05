import { NavBar } from "./../NavBar/NavBar";
import DateParser from "./Parsers/DateParser";
import MealBox from "./MealComponents/MealBox";
import { FullMenuResponse } from "../MenuResponse/ResponseHandling";

interface HomePageProps {
  menu: FullMenuResponse;
}

function HomePage(props: HomePageProps) {
  return (
    <div id="Home">
      <NavBar />
      <MealBox
        dateString={"2023-05-01"}
        parser={DateParser}
        menu={props.menu}
      />
    </div>
  );
}

export default HomePage;
