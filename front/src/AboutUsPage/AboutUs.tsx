import { NavBar } from "./../NavBar/NavBar";
// import headshots from "./public"
import React from "react";

// Defines TeamMember interface which specifies properties of the team members
interface TeamMember {
  name: string;
  year: string;
  concentration: string;
  hometown: string;
  favratty: string;
  picture: string;
}

// team constant is an array of objects that fill the "TeamMember" interface
const team: TeamMember[] = [
  {
    name: "Connor Flick",
    year: "2026",
    concentration: "Computational Biology",
    hometown: "Cincinnati, OH",
    favratty: "Chicken Tinga",
    picture: "https://i.imgur.com/prDrI6W.png",
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
// React component that returns the JSX that describes the "About Us" page.
// has the NavBar component, a heading, and a "card-flex" div that contains the team members' info

// card-flex div maps over the "team" array using the "map" method.
// For each team member, a new "card" div is created that displays the member's info
// The "key" attribute is set to the member's name to improve rendering performance.
function AboutUs() {
  return (
    <div id="About-Us" aria-label="About Us">
      <NavBar />
      <h1>Meet Our Team</h1>
      <div className="card-flex">
        {team.map((member) => (
        <div className="card" data-testid="team-member-card" key={member.name} >
          <img className="headshot" src={member.picture} alt={member.name} />
          <h2>{member.name}</h2>
          <p >Year: {member.year}</p>
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
