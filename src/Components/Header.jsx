import React from "react";
import { Link } from "react-router-dom";
import profile from "../Assets/pictures/robot-man.png";
import { useSelector } from "react-redux";

const Header = () => {
  const userInfo = useSelector((state)=>state.userInfo.value);
  console.log('userInfo: ',userInfo);
  return (
    <div className="headerMain">
      <div className="headerRight">
        <img
          className="headerAvatar"
          src={profile}
          alt="Avatar"
          title="Avatar"
        />
        <h3
          style={{
            margin: "0 2rem",
            textShadow: "5px 5px 10px #000",
            color: "#fff",
          }}
        >
          {userInfo.account}
        </h3>
      </div>
      <Link to={'../homepage/1'}>
        <h3
          style={{
            alignSelf: "center",
            justifySelf: "flex-start",
            color: "#fff",
            textShadow: "2px 5px 10px #000",
          }}
        >
          LOGO
        </h3>
      </Link>
    </div>
  );
};

export default Header;
