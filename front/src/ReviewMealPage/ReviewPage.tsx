import { NavBar } from "./../NavBar/NavBar";
import {ReviewDropDown} from "./ReviewDropDown"
import "../styles/ReviewPage.css"
import { useState } from "react";




function ReviewPage() {

  const [isClicked1, setIsClicked1] = useState<boolean>(false); 
  const [isClicked2, setIsClicked2] = useState<boolean>(false); 
  const [isClicked3, setIsClicked3] = useState<boolean>(false); 
  const [isClicked4, setIsClicked4] = useState<boolean>(false); 
  const [isClicked5, setIsClicked5] = useState<boolean>(false); 

  function checkStatus(id: number){
    if(id === 1){
      setIsClicked1(!isClicked1)
      setIsClicked2(false)
      setIsClicked3(false)
      setIsClicked4(false)
      setIsClicked5(false)
    }
    else if(id === 2){
      setIsClicked1(false)
      setIsClicked2(!isClicked2)
      setIsClicked3(false)
      setIsClicked4(false)
      setIsClicked5(false)

    }
    else if(id === 3){
      setIsClicked1(false)
      setIsClicked2(false)
      setIsClicked3(!isClicked3)
      setIsClicked4(false)
      setIsClicked5(false)
    }
    else if(id === 4){
      setIsClicked1(false)
      setIsClicked2(false)
      setIsClicked3(false)
      setIsClicked4(!isClicked4)
      setIsClicked5(false)
    }
    else if(id === 5){
      setIsClicked1(false)
      setIsClicked2(false)
      setIsClicked3(false)
      setIsClicked4(false)
      setIsClicked5(!isClicked5)
    }
  }

  return (
    <div id="Review-Page">
      <NavBar />
        <div className="reviewTitle">
            Review Page
        </div>
      <div className="question1">
      What food item are you reviewing today? 
        <div className="searchBar">
          <ReviewDropDown />
        </div>
      </div>
      <div className= "question2">
        What rating would you give this food?
        <div>
          <button className= {isClicked1 ? "clickedButton": "button"} onClick={() => checkStatus(1)}>1</button>
          <button className= {isClicked2 ? "clickedButton": "button"} onClick={() => checkStatus(2)}>2</button>
          <button className= {isClicked3 ? "clickedButton": "button"} onClick={() => checkStatus(3)}>3</button>
          <button className= {isClicked4 ? "clickedButton": "button"} onClick={() => checkStatus(4)}>4</button>
          <button className= {isClicked5 ? "clickedButton": "button"} onClick={() => checkStatus(5)}>5</button>
        </div>
      </div>
      <div className= "question3">
        Please provide any additional comments on the dish
          <div>
            <input className="inputButton"></input>
          </div>
      </div>
    </div>
  );
}

export default ReviewPage;
