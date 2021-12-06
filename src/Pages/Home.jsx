import React, { useEffect } from "react";
import { Outlet } from "react-router-dom";
import "../Components/mainContent.css";
import { Tabbar, Header, Transition } from "../Components";
import gsap from "gsap";
const Home = () => {
  const home = gsap.timeline();
  useEffect(()=>{

  })
  return (
    <div className="darkMain">
      <Tabbar />
      <Transition timeline={home} />
      <Header />
      <div className="mainContentHome">
        <Outlet />
      </div>
    </div>
  );
};

export default Home;
