// import "@testing-library/jest-dom";
// import { render, screen } from "@testing-library/react";
// import AboutUs from "../src/AboutUsPage/AboutUs";

// describe("About Us Page", () => {
//   test("renders About Us page with Meet Our Team header", () => {
//     render(<AboutUs />);
//     expect(screen.getByText(/Meet Our Team/)).toBeInTheDocument();
//   });

//   test("displays team members' information", () => {
//     render(<AboutUs />);
//     const teamMembers = [
//       {a
//         name: "Connor Flick",
//         year: "2025",
//         concentration: "Computer Science",
//         hometown: "New York, NY",
//         favratty: "Chicken Parmesan",
//       },
//       {
//         name: "Akshay Mehta",
//         year: "2026",
//         concentration: "Computer Science",
//         hometown: "Los Angeles, CA",
//         favratty: "Avocado Toast",
//       },
//       {
//         name: "Yonas Amha",
//         year: "2026",
//         concentration: "Business",
//         hometown: "Chicago, IL",
//         favratty: "Deep Dish Pizza",
//       },
//       {
//         name: "Shouri Akarapu",
//         year: "2024",
//         concentration: "Apma-CS, Econ",
//         hometown: "Norwalk, CT",
//         favratty: "Buffalo Mac N Cheese",
//       },
//     ];

//     teamMembers.forEach((member) => {
//       expect(screen.getByText(new RegExp(member.name))).toBeInTheDocument();
//       expect(screen.getByText(new RegExp(`Year: ${member.year}`))).toBeInTheDocument();
//       expect(
//         screen.getByText(new RegExp(`Concentration: ${member.concentration}`))
//       ).toBeInTheDocument();
//       expect(screen.getByText(new RegExp(`Hometown: ${member.hometown}`))).toBeInTheDocument();
//       expect(
//         screen.getByText(new RegExp(`Favorite Ratty Meal: ${member.favratty}`))
//       ).toBeInTheDocument();
//     });
//   });
// });
