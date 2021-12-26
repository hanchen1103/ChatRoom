import React, { useState, useEffect } from "react";
import imgLog from "../Assets/pictures/pexels-steve-johnson-1145720.jpg";
import { useSelector, useDispatch, batch } from "react-redux";
import { login } from "../state/reducers/accountReducer";
import { addUserRecord } from "../state/reducers/userInfo";
import { Background } from "../Components";
import { useNavigate } from "react-router";
import styles from './login.module.css';
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
    <div className={styles.loginPage}>
      <div className={styles.loginContent}>
        <h1>Login</h1>
        <input placeholder="Username" />
        <input placeholder="Password" />
        <span>Register</span>
        <button >Login</button>
      </div>     
     </div>
  );
};

export default LoginDetail;
