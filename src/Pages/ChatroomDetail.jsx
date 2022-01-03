import React, { useEffect, useState, useCallback } from "react";
import Ava from "../Assets/pictures/robot-man.png";
import Data from "../Assets/chatData";
import { SendMessage } from "../state/reducers/socketActionReducer";
import { useDispatch, useSelector } from "react-redux";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router";

const ChatroomDetail = () => {
  const dispatch = useDispatch();
  const fromId = sessionStorage.getItem("userId") || 17;
  const [input, setInput] = useState("");
  const [curUser, setCurUser] = useState({});
  const chatListId = useSelector((state) => state.chatListId.value);
  const navigate = useNavigate();
  const msgAll = JSON.parse(localStorage.getItem("messageHistory"));
  useEffect(() => {

  },[msgAll])
  useEffect(() => {
    console.log(chatListId);
    if (chatListId !== -1) GetMsgData(chatListId);
  }, [chatListId]);

  const GetMsgData = async (e) => {
    console.log(msgAll);
    let tempItem = {};
    msgAll.map((item) => {
      if (item.message.fromId == chatListId) {
        tempItem = item;
      }
    });
    setCurUser(tempItem);
    console.log(tempItem);
  };

  useEffect(() => {
    let ele = document.querySelector(".ChatroomMsgList");
    if (ele) ele.scroll(0, 5000);
  }, [chatListId]);

  const postData = useCallback(() => {
    if (input.length > 0) {
      let tempData = {
        toId: chatListId,
        fromId: fromId,
        content: input,
      };
      let tempData2 = {
        name: curUser.name,
        head_url: "lorem",
        message: {
          content: input,
          fromId: fromId,
          toId: chatListId,
          createDate: new Date(),
          id: chatListId + 1,
        },
      };
      console.log(tempData2);
      dispatch(SendMessage(tempData));
      msgAll.push(tempData2);
      localStorage.setItem("messageHistory", JSON.stringify(msgAll));
    }
    setInput("");
  },[msgAll]);

  const moveToProfile=(item)=>{
    console.log(item);
    navigate("../../user/"+ chatListId);
  }

  return (
    <>
      {chatListId === -1 && (
        <h2 className="ChatroomMsg">
          No chatroom choosed! <br /> Please choose one in left List
        </h2>
      )}
      {chatListId !== -1 && (
        <div className="ChatroomDetailContainer">
          <div className="ChatroomDetailHeader">
            <img
              className="ChatroomDetailHeaderAvatar"
              alt={curUser.name}
              title={curUser.name}
              src={Ava}
              onClick={() =>moveToProfile(curUser)}
            />
            <h2>{curUser.name}</h2>
          </div>
          <div className="ChatroomDetailMain">
            <ul className="ChatroomMsgList">
              {msgAll.map((item, index) => {
                if (
                  item.message.fromId === chatListId &&
                  item.message.toId === fromId
                )
                  return (
                    <li className="targetUserMsg" key={item.message.createDate}>
                      <div className="targetUserMsgContainer">
                        <img
                          className="chatroomAvatar"
                          src={Ava}
                          alt={item.name}
                          onClick={() =>moveToProfile(item)}
                        />
                        <div className="chatroomLeftArrow"></div>
                        <div className="targetUserMsgContent">
                          <p>{item.message.content}</p>
                        </div>
                        <span
                          style={{
                            marginLeft: "2rem",
                            fontSize: "12px",
                            color: "gray",
                          }}
                        >
                          {new Date(item.message.createDate).getFullYear() +
                            "/" +
                            new Date(item.message.createDate).getMonth() +1+
                            "/" +
                            new Date(item.message.createDate).getDate()}
                        </span>
                      </div>
                    </li>
                  );
                else if (
                  item.message.fromId === fromId &&
                  item.message.toId === chatListId
                )
                  return (
                    <li className="curentUserMsg" key={item.message.createDate}>
                      <div className="currentUserMsgContainer">
                        <img
                          className="chatroomAvatar"
                          src={Ava}
                          alt={item.name}
                          onClick={() =>moveToProfile(item)}
                          title={item.name}
                        />
                        <div className="chatroomRightArrow"></div>
                        <div className="currentUserMsgContent">
                          <p>{item.message.content}</p>
                        </div>
                        <span
                          style={{
                            marginRight: "2rem",
                            fontSize: "12px",
                            color: "gray",
                          }}
                        >
                          {new Date(item.message.createDate).getFullYear() +
                            "/" +
                            new Date(item.message.createDate).getMonth() +1+
                            "/" +
                            new Date(item.message.createDate).getDate()}
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
              className="ChatRoomDetailFooterIcon"
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
              value={input}
              className="chatroomDetailFooterInput"
            />
            <button
              onClick={() => postData()}
              className="ChatroomDetailFooterBtn"
            >
              SEND
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default ChatroomDetail;
