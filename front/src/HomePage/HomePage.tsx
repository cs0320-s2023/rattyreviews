import { NavBar } from "./../NavBar/NavBar";
import DateParser from "./Parsers/DateParser";
import MealBox from "./MealComponents/MealBox";
import { FullMenuResponse } from "../MenuResponse/ResponseHandling";
import { Dispatch, SetStateAction } from "react";

interface HomePageProps {
  date: Date;
  setDate: Dispatch<SetStateAction<Date>>;
  menu: FullMenuResponse;
}

function HomePage(props: HomePageProps) {
  return (
    <div id="Home">
      <NavBar />
      <MealBox
        setDate={props.setDate}
        date={props.date}
        parser={DateParser}
        menu={props.menu}
      />
    </div>
  );
}

export default HomePage;
