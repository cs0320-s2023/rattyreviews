import { NavBar } from "./../NavBar/NavBar";
import DateParser from "./Parsers/DateParser";
import MealBox from "./MealComponents/MealBox";
import { FullMenuResponse, Review } from "../MenuResponse/ResponseHandling";
import { Dispatch, SetStateAction } from "react";

interface HomePageProps {
  date: Date;
  setDate: Dispatch<SetStateAction<Date>>;
  menu: FullMenuResponse;
  reviews: Array<Review>;
}

function HomePage(props: HomePageProps) {
  return (
    <div id="Home">
      <NavBar />
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          height: "77vh",
        }}
      >
        <MealBox
          setDate={props.setDate}
          date={props.date}
          parser={DateParser}
          menu={props.menu}
          reviews={props.reviews}
        />
      </div>
    </div>
  );
}

export default HomePage;
