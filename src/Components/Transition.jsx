import React, { useRef, useEffect } from "react";
import "./Transition.css";
import { Power4 } from "gsap";
const Transition = ({ timeline }) => {
  const trans = useRef(null);
  const loader = useRef(null);
  useEffect(() => {
    let tl = timeline.to(loader.current, {
      opacity: 0,
      duration: 1,
    });
    tl.to(trans.current, {
      duration: 0.5,
      opacity: 0,
      ease: Power4.easeOut,
    });
    tl.to(trans.current,{
        duration:0.1,
        x:4000,
        ease:Power4.easeOut,
    })
  });
  return (
    <div className="transitionContent" ref={trans}>
      <div className="loader" ref={loader}>
        <div className="loader-inner">
          <div className="loader-line-wrap">
            <div className="loader-line"></div>
          </div>
          <div className="loader-line-wrap">
            <div className="loader-line"></div>
          </div>
          <div className="loader-line-wrap">
            <div className="loader-line"></div>
          </div>
          <div className="loader-line-wrap">
            <div className="loader-line"></div>
          </div>
          <div className="loader-line-wrap">
            <div className="loader-line"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Transition;
