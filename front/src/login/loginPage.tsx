import { NavBar } from "./../NavBar/NavBar";
import "../styles/ReviewPage.css";
import { useEffect, useState } from "react";
import googClientID from "../private/googClientID";
import { useGoogleLogin, GoogleLogin } from "@react-oauth/google";
import { googleLogout } from "@react-oauth/google";
import { google } from "googleapis";

function LoginPage() {
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
  };

  return (
    <div>
      <script
        src="https://accounts.google.com/gsi/client"
        onLoad={() => console.log("TODO: add onload function")}
      ></script>
      <h2>React Google Login</h2>
      <br />
      <br />
      {profile ? (
        <div>
          <img src={profile.picture} alt="user image" />
          <h3>User Logged in</h3>
          <p>Name: {profile.name}</p>
          <p>Email Address: {profile.email}</p>
          <br />
          <br />
          <button onClick={logOut}>Log out</button>
        </div>
      ) : (
        <button onClick={() => login()}>Sign in with Google 🚀 </button>
      )}
    </div>
  );
}

export default LoginPage;
