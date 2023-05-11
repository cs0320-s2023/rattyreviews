import { useState } from "react";
import dailyMenus from "../data/dailyFoodMenuItems.txt"
import React from "react";


const mockArray = ['Veggie Sausage Patty',
    'Potato Puffs',
    'Saute Spinach with Cherry Tomatoes',
    'Brown Rice',
    'Steel Cut Oats',
    'Steak & Egg Frittata',
    'Turkey Breakfast Sausage',
    'Blueberry Pancakes',
    'Plain Yogurt',
    'Strawberry Yogurt',
    'Vanilla Yogurt',
    'Cottage Cheese',
    'Fruit Bar',
    'Coconut Yogurt',
    'Jelly Donut',
    'Apple Coffee Cake',
    'Veggie Pizza',
    'Cheese Pizza',
    'Pepperoni Pizza',
    'Cajun Pasta'];

function turnToArray(){
    let array: string[] = []; 
    const reader = new FileReader();
    return array;
}

const getFilteredItems = (query: string, items: string[]) => {
    if(!query){
        return items;
    }
    return items.filter(food => food.includes(query))
}

function ReviewDropDown(){
    const [query, setQuery] = useState("");
    const filteredItems = getFilteredItems(query, mockArray)
    return (
<div className="searchBar">
    <label>Search</label>
    <input type="text" onChange={e => setQuery(e.target.value)}></input>
    <div className="results">
        {filteredItems.map(value => <button>{value}</button>)}
    </div>
</div>
    );
}

export {ReviewDropDown};