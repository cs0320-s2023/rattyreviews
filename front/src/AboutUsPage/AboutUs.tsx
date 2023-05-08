import { NavBar } from "./../NavBar/NavBar";
// import headshots from "./public"
import React from "react";

interface TeamMember {
  name: string;
  year: string;
  concentration: string;
  hometown: string;
  favratty: string;
  picture: string;
}

const team: TeamMember[] = [
  {
    name: "Connor Flick",
    year: "2026",
    concentration: "Computational Biology",
    hometown: "Cincinnati, OH",
    favratty: "Chicken Tinga",
    picture: "https://i.imgur.com/o8wR0um.jpg",
  },
  {
    name: "Akshay Mehta",
    year: "2026",
    concentration: "Computer Science",
    hometown: "Los Angeles, NB",
    favratty: "Avocado Toast",
    picture: "https://i.imgur.com/2ARly61.png",
  },
  {
    name: "Yonas Amha",
    year: "2025",
    concentration: "Computer Science",
    hometown: "Annandale, VA",
    favratty: "Eggs with onions and tomatoes",
    picture: "https://i.imgur.com/3BzaSfc.png",
  },
  {
    name: "Shouri Akarapu",
    year: "2024",
    concentration: "Apma-CS, Econ",
    hometown: "Norwalk, CT",
    favratty: "Buffalo Mac N Cheese",
    picture: "https://i.imgur.com/2rjhj3t.jpg",
  },
];

function AboutUs() {
  return (
    <div id="About-Us">
      <NavBar />
      <h1>Meet Our Team</h1>
      <div className="card-flex">
        {team.map((member) => (
        <div className="card" key={member.name}>
          <img className="headshot" src={member.picture} alt={member.name} />
          <h2>{member.name}</h2>
          <p>Year: {member.year}</p>
          <p>Concentration: {member.concentration}</p>
          <p>Hometown: {member.hometown}</p>
          <p>Favorite Ratty Meal: {member.favratty}</p>
        </div>
        ))}
      </div>
    </div>
  );
}

export default AboutUs;
