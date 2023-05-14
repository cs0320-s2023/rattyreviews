import { NavBar } from "./../NavBar/NavBar";
import "../styles/ReviewPage.css";
import { Dispatch, SetStateAction, useEffect, useState } from "react";
import { googleLogout, useGoogleLogin } from "@react-oauth/google";
import {
  FoodItem,
  FullMenuResponse,
  isMenuResponse,
  parseMeal,
} from "../MenuResponse/ResponseHandling";

interface reviewProps {
  menu: FullMenuResponse;
}
//TODO: prevent multi review garbage (only one per meal!)

function ReviewPage(props: reviewProps) {
  const [userID, setUserID] = useState("");
  const [openItems, setOpenItems] = useState<Array<FoodItem>>();
  const [justSwitchedMenu, setJustSwitchedMenu] = useState<boolean>(false);
  const [ratingVal, setRatingVal] = useState(1);
  const [inputBoxValue, setInputBoxValue] = useState("");

  const [reviewAvailable, setReviewAvailable] = useState<boolean>(false);
  const [displayeditems, setDisplayedItems] = useState<Array<FoodItem>>([]);
  const [submittedReview, setSubmittedReview] = useState<boolean>(false);

  const [todaysMenu, setTodaysMenu] = useState<FullMenuResponse>(
    new FullMenuResponse([], [], [])
  );

  function todaysMenuSetup() {
    console.log("setting up!");
    let dateComp = new Date()
      .toLocaleDateString("en-GB", { timeZone: "EST" })
      .split("/");
    let dateString = dateComp[2] + "-" + dateComp[1] + "-" + dateComp[0];
    fetch("http://localhost:3232/menus?date=" + dateString)
      .then((res) => res.json())
      .then((data) => {
        if (isMenuResponse(data)) {
          //TODO: NEED NEED NEED VALIDATION HERE
          let breakfast: Array<FoodItem> = parseMeal(data, "Breakfast");
          let lunch: Array<FoodItem> = parseMeal(data, "Lunch");
          let dinner: Array<FoodItem> = parseMeal(data, "Dinner");
          setTodaysMenu(new FullMenuResponse(breakfast, lunch, dinner));
        } else {
          console.log("ERROR"); //BETTER HANDLING NEEDED
        }
      })
      .catch((err) => {
        console.log(err.message);
      });
  }

  function filterItems(req: string) {
    setOpenItems([]);
    setJustSwitchedMenu(true);
    switch (req) {
      case "breakfast": {
        setDisplayedItems(todaysMenu.breakfast);
        break;
      }
      case "lunch": {
        setDisplayedItems(todaysMenu.lunch);
        break;
      }
      case "dinner": {
        setDisplayedItems(todaysMenu.dinner);
        break;
      }
      default: {
        setDisplayedItems([]);
        console.log("unexpected menu request!");
      }
    }
  }

  function loadSubmitReviewPage() {
    return (
      <div className="submitted-rev-page">
        Review has been submitted at {new Date().toLocaleString()}.
        <button onClick={() => {setReviewAvailable(true); setSubmittedReview(false)}}>Submit New Review 🐀</button>
      </div>
    )
  }

  return (
    <div id="Review-Page">
      <NavBar />
      <div className="dynamic-page">
        <LoginPage
          loginSwitch={setReviewAvailable}
          setUserID={setUserID}
          loginCallback={() => todaysMenuSetup()}
        />
        {reviewAvailable ? (
          <div className="review-page">
            <div className="review-container">
              {<div className="review-title" aria-label="Review a Meal">Review A Meal</div>}
              <div className="dropdown-meals">
                <select
                  onChange={(e) => {
                    filterItems(e.target.value.toLowerCase());
                  }}
                >
                  <option>Select a Meal</option>
                  <option>Breakfast</option>
                  <option>Lunch</option>
                  <option>Dinner</option>
                </select>
              </div>
              <br />

              <div className="item-container">
                {displayeditems.map((item) => {
                  return (
                    <div className="individual-item">
                      <RatingComp
                        item={item}
                        ratingVal={ratingVal}
                        setOpenItems={setOpenItems}
                        setRatingVal={setRatingVal}
                        openItems={openItems}
                        justSwitchedMenu={justSwitchedMenu}
                        setJustSwitchedMenu={setJustSwitchedMenu}
                      />
                    </div>
                  );
                })}
              </div>

              <div className="question3">
                <div>
                  <textarea
                    placeholder="Describe your experience with this meal"
                    className="commentBox"
                    value={inputBoxValue}
                    onChange={(event) => setInputBoxValue(event.target.value)}
                  ></textarea>
                </div>
              </div>
              <div>
                <button
                  onClick={() => {
                    if (openItems != undefined && openItems.length > 0) {
                      setInputBoxValue("");
                      let formData = {
                        UserID: userID,
                        Date: new Date().toString(),
                        Ratings: openItems,
                        comment: inputBoxValue,
                      };
                      fetch("http://localhost:3232/addReview", {
                        method: "POST",
                        body: JSON.stringify(formData), // body data type must match "Content-Type" header
                      }).then(() => console.log("Success"));
                      setSubmittedReview(true)
                      setReviewAvailable(false);
                    }
                  }}
                >
                  submit
                </button>
              </div>
            </div>
          </div>
        ) : (
          <div></div>
        )}
        <div>
          {submittedReview ? (loadSubmitReviewPage()) : (<></>)}
          
        </div>
      </div>
    </div>
  );
}

interface rcProps {
  item: FoodItem;
  setRatingVal: Dispatch<SetStateAction<number>>;
  setOpenItems: Dispatch<SetStateAction<FoodItem[] | undefined>>;
  openItems: FoodItem[] | undefined;
  ratingVal: number;
  justSwitchedMenu: boolean;
  setJustSwitchedMenu: Dispatch<boolean>;
}

function RatingComp(props: rcProps) {
  const [reviewScale, setReviewScale] = useState<JSX.Element>(<></>);
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [itemRate, setItemRate] = useState<number>(0);

  useEffect(() => {
    if (props.justSwitchedMenu) {
      console.log(props.item.title);
      setIsOpen(false);
      props.setJustSwitchedMenu(false);
    }
    setReviewScale(
      <div className="item-rating">
        {props.openItems != undefined && isOpen ? (
          <div className="rater">
            <input
              type="range"
              id="Rating"
              name="Rating"
              min="0"
              max="5"
              step="0.5"
              value={props.item.rating < 0 ? 0 : props.item.rating}
              onChange={(ev) => {
                //props.setRatingVal(parseFloat(ev.target.value));
                //console.log(ev.target.value);
                props.item.rating = parseFloat(ev.target.value);
                console.log(
                  props.item.title + " now has a rating of " + props.item.rating
                );
                setItemRate(props.item.rating);
                console.log(props.openItems?.length);
                console.log(props.openItems);
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
            <label htmlFor="Rating">Rating: {itemRate} ⭐ </label>
          </div>
        ) : (
          <>{}</>
        )}
      </div>
    );
  }, [itemRate, isOpen, props.justSwitchedMenu]);

  return (
    <div className="item-rating">
      <button
        className="item-button"
        onClick={() => {
          setIsOpen(!isOpen);
          props.setJustSwitchedMenu(false);
          if (props.openItems != undefined) {
            let fooItems: FoodItem[] = structuredClone(props.openItems);
            if (!isOpen) {
              fooItems.push(props.item);
              props.setOpenItems(fooItems);
              props.item.rating = 0;
              setItemRate(0);
            } else {
              let goodItems: FoodItem[] = fooItems.filter((item) => {
                return !FoodItem.equals(props.item, item);
              });
              props.setOpenItems(goodItems);
              console.log("dropped item!");
            }
          }
        }}
      >
        {props.item.title}
      </button>
      {reviewScale}
    </div>
  );
}

interface loginProps {
  loginSwitch: Dispatch<SetStateAction<boolean>>;
  setUserID: Dispatch<SetStateAction<string>>;
  loginCallback: () => any;
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
      props.loginCallback();
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
  if (profile != null) props.setUserID(profile.sub);
  return (
    <div>
      <script
        src="https://accounts.google.com/gsi/client"
        onLoad={() => console.log("TODO: add onload function")}
      ></script>
      {profile ? (
        <div className="loggedInArea">
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
