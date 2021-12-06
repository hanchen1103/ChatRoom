import React, { useState, useEffect } from "react";
import Avatar from "../Assets/pictures/robot-man.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
const ChatList = () => {
  const [update, setUpdate] = useState(false);
  const [list, setList] = useState([
    {
      id: 0,
      img: Avatar,
      name: "robot",
      msg: "lorem adad gfrzc",
      selected: true,
      date: "2021/11/1",
    },
    {
      id: 1,
      img: Avatar,
      name: "robot",
      msg: "lorem adad gfrzc",
      selected: false,
      date: "2021/11/1",
    },
  ]);
  useEffect(() => {}, [update]);
  return (
    <div className="chatListContainer">
      <div className="chatListSearch">
        <h3 style={{ color: "rgba(255,255,255,.7)" }}>Conversation</h3>
        <div className="searchBar">
          <FontAwesomeIcon icon={faSearch} className="searchIcon" />
          <input placeholder="Search" className="chatListSearchInput" />
        </div>
      </div>
      <div className="chatListContent">
        {list.map((item, index) => {
          return (
            <div
              className="chatListItem"
              key={item.id}
              style={{
                backgroundColor: item.selected
                  ? "rgba(0,0,0,1)"
                  : "rgba(0,0,0,.4)",
                padding: ".3rem 0rem ",
                color: item.selected ? "#fff" : "rgba(255,255,255,.7)",
              }}
            >
              <img
                style={{
                  width: "3rem",
                  height: "3rem",
                  objectFit: "contain",
                  border: "1px solid #000",
                  borderRadius: "50%",
                  backgroundColor: "#cb9c44",
                  margin: "0 1rem",
                }}
                src={item.img}
                alt={item.name}
                title={item.name}
              />
              <div className="chatListCard">
                <span className="chatListCardName">{item.name}</span>
                <div className="chatListCardDetail">
                  <span className="chatListCardDetailMsg">{item.msg}</span>
                  <span className="chatListCardDetailDate">{item.date}</span>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ChatList;
