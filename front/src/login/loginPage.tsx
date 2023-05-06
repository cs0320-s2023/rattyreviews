import { NavBar } from "./../NavBar/NavBar";
import "../styles/ReviewPage.css"
import { useEffect, useState } from "react";
import googClientID from "../private/googClientID";
import { useGoogleLogin, GoogleLogin } from '@react-oauth/google';
import { googleLogout } from '@react-oauth/google';





function LoginPage() {

    //BORROWED FROM https://blog.logrocket.com/guide-adding-google-login-react-app/
    // the documentation that google provides is deeply confusing & not built for react

    const [ user, setUser ] = useState<any>(null);
    const [ profile, setProfile ] = useState<any>(null);

    const login = useGoogleLogin({
        onSuccess: (codeResponse) => {setUser(codeResponse); console.log(codeResponse);},
        onError: (error) => console.log('Login Failed:', error)
    });

    useEffect(
        () => {
            if (user) {
                console.log(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`);

                const requestHeaders: HeadersInit = new Headers();
                requestHeaders.set('Authorization', `Bearer ${user.access_token}`);
                requestHeaders.set('Accept', 'application/json');

                fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`, {
                    method: 'GET',
                    headers: requestHeaders
                  })
                .then((res: any) => {
                    return res.json();
                    
                })
                .then((res: any) => {
                    setProfile(res);
                    console.log(res["name"]);
                })
                .catch((err: any) => console.log(err));
            }
        },
        [user]
    );

    // log out function to log the user out of google and set the profile array to null
    const logOut = () => {
        googleLogout();
        setProfile(null);
    };

    return (
        <div>
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


   
// const login = useGoogleLogin({
//     onSuccess: codeResponse => console.log(codeResponse),
//     flow: 'auth-code',
//   });

//   return (
    
//     <div id="Review-Page">
//       <NavBar />

      

//       {/* <button onClick={() => login()}>
//         {}
//         Sign in with Google 🚀{' '}
//     </button> */}

//         {/* <GoogleOAuthProvider clientId={googClientID}>
//                 <GoogleLogin
//             onSuccess={credentialResponse => {
//                 console.log(credentialResponse);
//             }}
//             onError={() => {
//                 console.log('Login Failed');
//             }}
//             />
//         </GoogleOAuthProvider> */}

    



//       {/* <NavBar />
//       <meta name="referrer" content="no-referrer-when-downgrade" />
//       <script src="https://accounts.google.com/gsi/client" async defer></script>
//       <div id="g_id_onload"
//             data-client_id="102338677234-big7ro0m6h2vt024g1hho71bu3vmbbs1.apps.googleusercontent.com"
//             data-context="signin"
//             data-ux_mode="popup"
//             data-login_uri="http://localhost:5173/login"
//             data-nonce=""
//             data-auto_prompt="false">
//         </div>

//         <div class="g_id_signin"
//             data-type="standard"
//             data-shape="rectangular"
//             data-theme="outline"
//             data-text="signin_with"
//             data-size="large"
//             data-logo_alignment="left">
//         </div> */}
//     </div>
//   );
}

export default LoginPage;


