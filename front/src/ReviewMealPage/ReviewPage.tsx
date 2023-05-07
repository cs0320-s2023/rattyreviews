import { NavBar } from "./../NavBar/NavBar";
import { ReviewDropDown } from "./ReviewDropDown";
import "../styles/ReviewPage.css";
import { Dispatch, SetStateAction, useEffect, useState } from "react";
import { googleLogout, useGoogleLogin } from "@react-oauth/google";
import { FoodItem, FullMenuResponse } from "../MenuResponse/ResponseHandling";

interface reviewProps {
  menu: FullMenuResponse;
}

function ReviewPage(props: reviewProps) {
  const [userID, setUserID] = useState("");
  console.log(userID);
  const [clickedItem, setClickedItem] = useState<FoodItem>();
  const [ratingVal, setRatingVal] = useState(1);
  const [inputBoxValue, setInputBoxValue] = useState("");
  const [isClicked1, setIsClicked1] = useState<boolean>(false);
  const [isClicked2, setIsClicked2] = useState<boolean>(false);
  const [isClicked3, setIsClicked3] = useState<boolean>(false);
  const [isClicked4, setIsClicked4] = useState<boolean>(false);
  const [isClicked5, setIsClicked5] = useState<boolean>(false);

  //TODO: simplify this

  function checkStatus(id: number) {
    if (id === 1) {
      setIsClicked1(!isClicked1);
      setIsClicked2(false);
      setIsClicked3(false);
      setIsClicked4(false);
      setIsClicked5(false);
    } else if (id === 2) {
      setIsClicked1(false);
      setIsClicked2(!isClicked2);
      setIsClicked3(false);
      setIsClicked4(false);
      setIsClicked5(false);
    } else if (id === 3) {
      setIsClicked1(false);
      setIsClicked2(false);
      setIsClicked3(!isClicked3);
      setIsClicked4(false);
      setIsClicked5(false);
    } else if (id === 4) {
      setIsClicked1(false);
      setIsClicked2(false);
      setIsClicked3(false);
      setIsClicked4(!isClicked4);
      setIsClicked5(false);
    } else if (id === 5) {
      setIsClicked1(false);
      setIsClicked2(false);
      setIsClicked3(false);
      setIsClicked4(false);
      setIsClicked5(!isClicked5);
    }
  }

  const [reviewAvailable, setReviewAvailable] = useState<boolean>(false);
  const [displayeditems, setDisplayedItems] = useState<Array<FoodItem>>([]);

  function filterItems(req: string) {
    switch (req) {
      case "breakfast": {
        setDisplayedItems(props.menu.breakfast);
        break;
      }
      case "lunch": {
        setDisplayedItems(props.menu.lunch);
        break;
      }
      case "dinner": {
        setDisplayedItems(props.menu.dinner);
        break;
      }
      default: {
        console.log("unexpected menu request!");
      }
    }
  }

  // function getRatingScale() {
  //   console.log("clicked!");
  //   setReviewScale(
  //     <div>
  //       <button>1</button>
  //       <button>2</button>
  //       <button>3</button>
  //       <button>4</button>
  //       <button>5</button>
  //     </div>
  //   )
  // }

  return (
    <div id="Review-Page">
      <NavBar />
      <LoginPage loginSwitch={setReviewAvailable} setUserID={setUserID} />
      {reviewAvailable ? (
        //TODO: css all of this up
        <div>
          <div className="reviewTitle">Review Page</div>
          <div>
            <button
              onClick={() => {
                filterItems("breakfast");
              }}
            >
              Breakfast
            </button>
            <button
              onClick={() => {
                filterItems("lunch");
              }}
            >
              Lunch
            </button>
            <button
              onClick={() => {
                filterItems("dinner");
              }}
            >
              Dinner
            </button>
          </div>
          <br />

          <div>
            {displayeditems.map((item) => {
              return (
                <RatingComp
                  item={item}
                  ratingVal={ratingVal}
                  setClickedItem={setClickedItem}
                  setRatingVal={setRatingVal}
                  clickedItem={clickedItem}
                />
              );
            })}
          </div>

          <div className="question3">
            Please provide any additional comments on the dish
            <div>
              <input
                className="inputButton"
                value={inputBoxValue}
                onChange={(event) => setInputBoxValue(event.target.value)}
              ></input>
            </div>
          </div>
          <div>
            <button
              onClick={() => {
                if (clickedItem != undefined) {
                  setInputBoxValue("");
                  let formData = {
                    UserID: userID,
                    Date: new Date().toISOString().split("T")[0],
                    Ratings: [
                      new FoodItem(
                        clickedItem.title,
                        clickedItem.description,
                        ratingVal,
                        clickedItem.foodRestrictions
                      ),
                    ],
                  };
                  fetch("http://localhost:3232/addReview", {
                    method: "POST",
                    body: JSON.stringify(formData), // body data type must match "Content-Type" header
                  }).then(() => console.log("Success"));
                }
              }}
            >
              submit
            </button>
          </div>
        </div>
      ) : (
        <div>nothing!</div>
      )}
    </div>
  );
}

interface rcProps {
  item: FoodItem;
  setRatingVal: Dispatch<SetStateAction<number>>;
  setClickedItem: Dispatch<SetStateAction<FoodItem | undefined>>;
  clickedItem: FoodItem | undefined;
  ratingVal: number;
}

function RatingComp(props: rcProps) {
  const [reviewScale, setReviewScale] = useState<JSX.Element>(<></>);

  useEffect(() => {
    setReviewScale(
      <div>
        {props.clickedItem != undefined &&
        props.clickedItem.title == props.item.title ? (
          <div className="rater">
            <input
              type="range"
              id="Rating"
              name="Rating"
              min="0"
              max="5"
              step="0.5"
              value={props.ratingVal}
              onChange={(ev) => {
                props.setRatingVal(parseFloat(ev.target.value));
                console.log(ev.target.value);
              }}
              list="markers"
            ></input>
            <datalist id="markers">
              <option value="0" label=">:("></option>
              <option value="1" label=":("></option>
              <option value="2" label=":/"></option>
              <option value="3" label=":|"></option>
              <option value="4" label=":)"></option>
              <option value="5" label=":D"></option>
            </datalist>
            <label htmlFor="Rating">Rating: {props.ratingVal}</label>
          </div>
        ) : (
          <></>
        )}
      </div>
    );
  }, [props.ratingVal, props.clickedItem]);

  return (
    <div>
      <button onClick={() => props.setClickedItem(props.item)}>
        {props.item.title}
      </button>
      {reviewScale}
    </div>
  );
}

interface loginProps {
  loginSwitch: Dispatch<SetStateAction<boolean>>;
  setUserID: Dispatch<SetStateAction<string>>;
}

function LoginPage(props: loginProps) {
  //BORROWED FROM https://blog.logrocket.com/guide-adding-google-login-react-app/
  // the documentation that google provides is deeply confusing & not built for react

  const [user, setUser] = useState<any>(null);
  const [profile, setProfile] = useState<any>(null);

  const login = useGoogleLogin({
    //TODO: switch to auth flow at some point. implicit allows for bodging while for easy auth work.
    flow: "implicit",
    onSuccess: (codeResponse) => {
      setUser(codeResponse);
      console.log(codeResponse);
      props.loginSwitch(true);
    },
    onError: (error) => console.log("Login Failed:", error),
    hosted_domain: "brown.edu",
  });

  useEffect(() => {
    if (user) {
      const requestHeaders: HeadersInit = new Headers();
      requestHeaders.set("Authorization", `Bearer ${user.access_token}`);
      requestHeaders.set("Accept", "application/json");
      //TODO: implement this to allow for more stable pulls of user info endpoints
      // const id_url = fetch('https://accounts.google.com/.well-known/openid-configuration')
      // .then((res: any) => {
      //     return res.json()
      // })
      // .then((resJSON: any) => {
      //     return resJSON["userinfo_endpoint"];
      // })
      // .catch((err: any) => console.log(err));

      fetch(`https://openidconnect.googleapis.com/v1/userinfo`, {
        method: "GET",
        headers: requestHeaders,
      })
        .then((res: any) => {
          return res.json();
        })
        .then((res: any) => {
          setProfile(res);
          console.log(res);
        })
        .catch((err: any) => console.log(err));
    }
  }, [user]);

  // log out function to log the user out of google and set the profile array to null
  const logOut = () => {
    googleLogout();
    setProfile(null);
    props.loginSwitch(false);
  };
  if (profile != null) props.setUserID(profile.email);
  return (
    <div>
      <script
        src="https://accounts.google.com/gsi/client"
        onLoad={() => console.log("TODO: add onload function")}
      ></script>
      {profile ? (
        <div>
          <p>Logged in as: {profile.email}</p>
          <button onClick={logOut}>Log out</button>
        </div>
      ) : (
        //TODO: set up more callback triggers with sign-in
        <button onClick={() => login()}>
          Sign in with Google to add review 🐀🚀{" "}
        </button>
      )}
    </div>
  );
}

export default ReviewPage;
