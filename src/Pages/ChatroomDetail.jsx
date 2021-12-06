import React, { useEffect, useState, useCallback } from "react";
import Ava from "../Assets/pictures/robot-man.png";
import Data from "../Assets/chatData";
import { SendMessage } from "../state/reducers/socketActionReducer";
import { useDispatch, useSelector } from "react-redux";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";

const ChatroomDetail = () => {
  const dispatch = useDispatch();
  const fromId = sessionStorage.getItem("userId");
  const [input, setInput] = useState("");
  //const [msgList, setMsgList] = useState([]);
  //const [initial, setInitia] = useState(true);

  useEffect(() => {
    let ele = document.querySelector(".ChatroomMsgList");
    ele.scroll(0, 5000);
  });

  const postData = useCallback(() => {
    let tempData = {
      toId: 2,
      fromId: fromId,
      content: input,
    };
    //dispatch(SendMessage(JSON.stringify(tempData)))
  }, []);

  return (
    <div className="ChatroomDetailContainer">
      <div className="ChatroomDetailHeader">
        <img className="ChatroomDetailHeaderAvatar" alt="Apple" src={Ava} />
        <h2>Robot</h2>
      </div>
      <div className="ChatroomDetailMain">
        <ul className="ChatroomMsgList">
          {Data.map((item, index) => {
            if (item.fromId === 1)
              return (
                <li className="targetUserMsg" key={item.id}>
                  <div className="targetUserMsgContainer">
                    <img
                      className="chatroomAvatar"
                      src={Ava}
                      alt={item.tarName}
                    />
                    <div className="targetUserMsgContent">
                      <p>{item.msg}</p>
                    </div>
                    <span
                      style={{
                        marginLeft: "2rem",
                        fontSize: "12px",
                        color: "gray",
                      }}
                    >
                      {item.date}
                    </span>
                  </div>
                </li>
              );
            else if (item.fromId === 2)
              return (
                <li className="curentUserMsg" key={item.id}>
                  <div className="currentUserMsgContainer">
                    <img
                      className="chatroomAvatar"
                      src={Ava}
                      alt={item.tarName}
                    />
                    <div className="currentUserMsgContent">
                      <p>
                        {item.msg} Lorem ipsum, dolor sit amet consectetur
                        adipisicing elit. Vel recusandae nulla ea veritatis quod
                        eos aspernatur! Excepturi id beatae possimus ea
                        asperiores libero odio! Neque debitis aliquam veritatis
                        mollitia vel.
                      </p>
                    </div>
                    <span
                      style={{
                        marginRight: "2rem",
                        fontSize: "12px",
                        color: "gray",
                      }}
                    >
                      {item.date}
                    </span>
                  </div>
                </li>
              );
            else return null;
          })}
        </ul>
      </div>
      <div className="ChatroomDetailFooter">
        <FontAwesomeIcon
          style={{
            width: "2rem",
            height: "2rem",
            color: "#ff5566",
            margin: "0 5%",
          }}
          icon={faPlusCircle}
        />
        <input
          onChange={(value) => setInput(value.target.value)}
          placeholder="Input Something"
          className="chatroomDetailFooterInput"
        />
        <button onClick={() => postData()} className="ChatroomDetailFooterBtn">
          SEND
        </button>
      </div>
    </div>
  );
};

export default ChatroomDetail;
