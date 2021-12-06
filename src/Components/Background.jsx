import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import img1 from "../Assets/pictures/pexels-freestocksorg-285173.jpg";
import img2 from "../Assets/pictures/pexels-dom-j-310452.jpg";
import img3 from "../Assets/pictures/pexels-sharon-mccutcheon-1174952.jpg";
import img4 from "../Assets/pictures/pexels-steve-johnson-1145720.jpg";

const Background = (props) => {
  const { pathname } = useLocation();
  const imgList = [img1, img2, img3, img4];
  const [Img, setImg] = useState(img1);

  useEffect(() => {
    if (props.fixed) {
      setImg(imgList[props.imgIndex]);
      return;
    }
    if (!sessionStorage.getItem("backgroundImgId")) {
      sessionStorage.setItem("backgroundImgId", 0);
    } else {
      let tempBackId = Number(sessionStorage.getItem("backgroundImgId"));
      sessionStorage.removeItem("backgroundImgId");
      ++tempBackId;
      if (tempBackId > 3) tempBackId = 0;
      setImg(imgList[tempBackId]);
      sessionStorage.setItem("backgroundImgId", tempBackId);
    }
  }, [pathname,imgList,props.fixed,props.imgIndex]);
  return (
    <img
      style={{
        minWidth: "100vw",
        minHeight: "100vh",
        position: "absolute",
        left: 0,
        top: 0,
        right: 0,
        bottom: 0,
        zIndex: "-1",
        maxWidth: "100vw",
        overflow: "hidden",
        height: "100vh",
        objectFit: "cover",
      }}
      id="backgroundLogin"
      src={Img}
      alt=""
    />
  );
};

export default Background;
