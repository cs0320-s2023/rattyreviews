import "@testing-library/jest-dom";
import { render, screen } from "@testing-library/react";
import AboutUs from "../src/AboutUsPage/AboutUs";
import React from "react";
import '@testing-library/jest-dom';
import { BrowserRouter } from 'react-router-dom';


describe('About Us Page', () => {
  beforeEach(() => {
    render(
      <BrowserRouter>
        <AboutUs />
      </BrowserRouter>
    );
  });

  test('renders team member cards', () => {
    const cards = screen.getAllByTestId('team-member-card');
    expect(cards.length).toBe(4);
  });
});

//   test('renders NavBar component', () => {
//     const navElement = screen.getByRole('navigation');
//     expect(navElement).toBeInTheDocument();
//   });

//   test('renders "Meet Our Team" heading', () => {
//     const headingElement = screen.getByRole('heading', { name: /Meet Our Team/i });
//     expect(headingElement).toBeInTheDocument();
//   });

//   test('renders team member cards', () => {
//     const cards = screen.getAllByRole('listitem');
//     expect(cards.length).toBe(4);
//   });

//   test('renders each team member card with correct information', () => {
//     const teamMembers = [
//       {
//         name: 'Connor Flick',
//         year: '2026',
//         concentration: 'Computational Biology',
//         hometown: 'Cincinnati, OH',
//         favratty: 'Chicken Tinga',
//         picture: 'https://i.imgur.com/o8wR0um.jpg',
//       },
//       {
//         name: 'Akshay Mehta',
//         year: '2026',
//         concentration: 'Computer Science',
//         hometown: 'Los Angeles, NB',
//         favratty: 'Avocado Toast',
//         picture: 'https://i.imgur.com/2ARly61.png',
//       },
//       {
//         name: 'Yonas Amha',
//         year: '2025',
//         concentration: 'Computer Science',
//         hometown: 'Annandale, VA',
//         favratty: 'Eggs with onions and tomatoes',
//         picture: 'https://i.imgur.com/3BzaSfc.png',
//       },
//       {
//         name: 'Shouri Akarapu',
//         year: '2024',
//         concentration: 'Apma-CS, Econ',
//         hometown: 'Norwalk, CT',
//         favratty: 'Buffalo Mac N Cheese',
//         picture: 'https://i.imgur.com/2rjhj3t.jpg',
//       },
//     ];

//     teamMembers.forEach((member) => {
//       const nameElement = screen.getByText(member.name);
//       expect(nameElement).toBeInTheDocument();

//       const yearElement = screen.getByText(`Year: ${member.year}`);
//       expect(yearElement).toBeInTheDocument();

//       const concentrationElement = screen.getByText(`Concentration: ${member.concentration}`);
//       expect(concentrationElement).toBeInTheDocument();

//       const hometownElement = screen.getByText(`Hometown: ${member.hometown}`);
//       expect(hometownElement).toBeInTheDocument();

//       const favrattyElement = screen.getByText(`Favorite Ratty Meal: ${member.favratty}`);
//       expect(favrattyElement).toBeInTheDocument();

//       const imageElement = screen.getByAltText(member.name);
//       expect(imageElement).toHaveAttribute('src', member.picture);
//     });
//   });
// });
