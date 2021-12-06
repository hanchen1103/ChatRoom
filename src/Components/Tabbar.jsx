import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHome,
  faUser,
  faUsersCog,
  faAd,
  faCommentAlt,
  faCog,
} from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router";
import Logo from "../Assets/pictures/log.png";

const Tabbar = () => {
  sessionStorage.setItem("menuHide", false);
  let iconList = [
    {
      id: 0,
      icon: faHome,
      choosed: true,
    },
    {
      id: 1,
      icon: faUser,
      choosed: false,
    },
    { id: 2, icon: faUsersCog, choosed: false },
    { id: 3, icon: faAd, choosed: false },
    {
      id: 4,
      icon: faCommentAlt,
      choosed: false,
    },
  ];
  const navigate = useNavigate();
  const [menuHide, setMenuHide] = useState(false);
  const [update, setUpdate] = useState(true);
  const [lsit, setlist] = useState(iconList);
  const [index, setIndex] = useState(0);
  const [hisIndex, setHisIndex] = useState(0);
  useEffect(() => {
    if (index === 0) navigate("1");
    else if (index === 1) navigate("2");
    else if (index === 2) navigate("3");
    else if (index === 3) navigate("4");
    else if (index === 4) navigate("5");
  }, [index, navigate]);
  useEffect(() => {
    iconList.map((item) => {
      item.choosed = false;
      return null;
    });
    iconList[index].choosed = true;
    setlist(iconList);
  }, [update]);
  const tabbarItemChoosed = (e) => {
    console.log("start", e, index, hisIndex);
    if (index === e) {
      return;
    } else {
      setUpdate((prev) => !prev);
      setHisIndex(index);
      setIndex(e);
    }
    console.log("end", e, index, hisIndex);
  };
  return (
    <div
      className={"tabbarMain"}
      style={{
        height: menuHide ? "5rem" : "100vh",
        overflowY: "hidden",
      }}
    >
      <div
        style={{
          width: "4rem",
          height: "4rem",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        {/* <FontAwesomeIcon
          onClick={() => setMenuHide((prev) => {
            sessionStorage.setItem('menuHide',!prev);
          return !prev
          })}
          style={{
            width: "2rem",
            height: "2rem",
            color: menuHide ? "#fff" : "#7b7c86",
            alignSelf: "center",
            cursor: "pointer",
          }}
          icon={faBars}
        /> */}
        <img style={{width:'5rem',height:'5rem',objectFit:'cover'}} src={Logo} alt="LOGO" />
      </div>
      <div
        className={"tabbarItems"}
        style={{
          height: menuHide ? 0 : "50vh",
        }}
      >
        {lsit.map((item, index) => {
          return (
            <div
              key={item.id}
              className="tabbarItem"
              style={{
                backgroundColor: item.choosed ? "red " : "transparent",
                boxShadow: item.choosed
                  ? "inset 2px 2px 15px rgba(0,0,0,.3)"
                  : "none",
                height: menuHide ? "0" : "2.2rem",
                overflow: "hidden",
              }}
              id={"tabbarItem" + index}
              onClick={() => tabbarItemChoosed(index)}
            >
              <FontAwesomeIcon
                className="homeicons"
                style={{
                  width: item.choosed ? "2.2rem" : "2rem",
                  height: item.choosed ? "2.2rem" : "2rem",
                  color: item.choosed ? "#fff" : "#7b7c86",
                  backgroundColor: "transparent",
                }}
                icon={item.icon}
              />
            </div>
          );
        })}
      </div>

      <FontAwesomeIcon
        className="homeicons"
        style={{
          width: "2rem",
          height: menuHide ? 0 : "2rem",
          color: "#7b7c86",
          alignSelf: "center",
          position: "fixed",
          bottom: "2rem",
          cursor: "pointer",
        }}
        icon={faCog}
      />
    </div>
  );
};

export default Tabbar;
