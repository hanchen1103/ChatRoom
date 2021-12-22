import React, { useState, useEffect } from "react";
import imgLog from "../Assets/pictures/pexels-steve-johnson-1145720.jpg";
import { useSelector, useDispatch, batch } from "react-redux";
import { login } from "../state/reducers/accountReducer";
import { addUserRecord } from "../state/reducers/userInfo";
import { Background } from "../Components";
import { useNavigate } from "react-router";
import logo from "../Assets/pictures/log.png";

const LoginDetail = () => {
  const loginWea = useSelector((state) => state.account.value);
  const userInfo = useSelector((state) => state.userInfo.value);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [loginstatus, setLoginStatus] = useState(true);
  const [username, setUsername] = useState("");
  const [pwd, setPwd] = useState("");
  const [conPwd, setConPwd] = useState("");
  const [tip, setTips] = useState(false);

  useEffect(() => {
    if (loginWea) navigate("../../");
  }, [loginWea]);

  const pwdCheck = (e) => {
    setConPwd(e.target.value);
    if (e.target.value !== pwd) setTips(true);
    else setTips(false);
  };

  const goLogin = async () => {
    let url = "";
    if (loginstatus)
      url = "http://42.192.54.187:8080/newproject-0.0.1-SNAPSHOT/login";
    else url = "http://42.192.54.187:8080/newproject-0.0.1-SNAPSHOT/register";
    let data = {
      account: username,
      password: pwd,
    };
    await fetch(url, {
      mode: "cors",
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => {
        return res.json();
      })
      .then((json) => {
        if (json.code === 200) {
          batch(() => {
            dispatch(login());
            dispatch(addUserRecord(json.user));
          });
          sessionStorage.setItem("userId", json.user.id);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="loginPage">
      <Background fixed={true} imgIndex={3} />
      <div
        style={{
          backgroundColor: "rgba(25,25,33,.9)",
          width: "80vw",
          height: "80vh",
          margin: "10vh 10vw",
          borderRadius: "15px",
          backdropFilter: "blur(10px)",
          boxShadow: "5px 20px 20px #000,-2px -3px 15px #000 ",
          border: "1px solid #000",
          display: "flex",
          flexDirection: "row",
          overflow: "hidden",
        }}
      >
        <div
          style={{
            width: !loginstatus ? 0 : "40%",
            height: "100%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            padding: !loginstatus ? 0 : "1rem",
            overflow: "hidden",
          }}
        >
          <img
            style={{
              position: "absolute",
              left: 0,
              top: 0,
              opacity: !loginstatus ? 0 : 1,
            }}
            src={logo}
            alt="logo"
          />
          <h1 style={{ textShadow: "2px 2px 5px #000", color: "#fff" }}>
            LOGIN
          </h1>
          <h1>{} </h1>
          <h3 className="loginH3">Username</h3>
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="loginInput"
          />
          <h3 className="loginH3">Password</h3>
          <input
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
            type="password"
            className="loginInput"
          />
          <button onClick={() => goLogin()} className="loginButton">
            Confirm
          </button>
          <h4
            style={{
              alignSelf: "flex-end",
              justifyItems: "flex-end",
              position: "absolute",
              bottom: 0,
            }}
          >
            Press To{" "}
            <span
              className="loginToReg"
              style={{ cursor: "pointer" }}
              onClick={() => setLoginStatus((prev) => !prev)}
            >
              SIGN UP
            </span>
          </h4>
        </div>
        <div
          style={{
            width: "60%",
            height: "100%",
            borderLeft: "1px solid #000",
            borderRight: "1px solid #000",
          }}
        >
          <img
            src={imgLog}
            style={{
              width: "100%",
              height: "100%",
              objectFit: "cover",
              borderRadius: "15px 0 0 0",
            }}
            alt=""
          />
          <h1
            style={{
              position: "absolute",
              bottom: "6rem",
              zIndex: 1,
              right: loginstatus ? "5%" : "auto",
              left: loginstatus ? "auto" : "5%",
              color: "red",
              textShadow: "2px 5px 10px #000",
            }}
          >
            THREAD
          </h1>
          <h2
            style={{
              position: "absolute",
              bottom: 0,
              zIndex: 1,
              right: loginstatus ? "5%" : "auto",
              left: loginstatus ? "auto" : "5%",
              color: "gray",
              textShadow: "5px 5px 10px #000",
              textAlign: loginstatus ? "right" : "left",
            }}
          >
            Welcome to Thread Pool!
            <br />
            This is a chatroom Website for
            <br /> users with different intetests!
          </h2>
        </div>
        <div
          style={{
            width: loginstatus ? 0 : "40%",
            height: "100%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            padding: loginstatus ? 0 : "1rem",
            overflow: "hidden",
          }}
        >
          <img
            style={{
              position: "absolute",
              right: 0,
              top: 0,
              opacity: loginstatus ? 0 : 1,
            }}
            src={logo}
            alt="logo"
          />
          <h1 style={{ textShadow: "2px 2px 5px #000", color: "#fff" }}>
            REGISTER
          </h1>
          <h3 className="loginH3">Username</h3>
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="loginInput"
          />
          <h3 className="loginH3">Password</h3>
          <input
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
            type="password"
            className="loginInput"
          />
          <h3 className="loginH3">Confirm Your Password</h3>

          <input
            value={conPwd}
            onChange={(e) => {
              pwdCheck(e);
            }}
            type="password"
            className="loginInput"
          />
          <span
            style={{
              opacity: tip ? 1 : 0,
              color: "gray",
              marginLeft: "20%",
              alignSelf: "flex-start",
            }}
          >
            Incorrect
          </span>
          <button onClick={() => goLogin()} className="loginButton">
            Confirm
          </button>
          <h4
            style={{
              alignSelf: "flex-start",
              justifyItems: "flex-start",
              position: "absolute",
              bottom: 0,
            }}
          >
            Press To{" "}
            <span
              className="loginToReg"
              style={{ cursor: "pointer" }}
              onClick={() => setLoginStatus((prev) => !prev)}
            >
              SIGN IN
            </span>
          </h4>
        </div>
      </div>
    </div>
  );
};

export default LoginDetail;
