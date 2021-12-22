import React, { useState, useEffect } from "react";
import Avatar from "../Assets/pictures/robot-man.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { useSelector } from "react-redux";

const ChatList = () => {
  const [finalList, setFinalList] = useState([]);
  const [selectedId, setSelectedId] = useState(-1);
  const [list, setList] = useState([]);
  const [initial, setInitial] = useState(true);
  const data = useSelector((state) => state.socket.value);

  //Initial, get Data from localStorage.
  useEffect(() => {
    if (
      window.localStorage.getItem("messageHistory") &&
      window.localStorage.getItem("messageHistory").length > 0
    ) {
      console.log("Initial: exist messageHistory!");
      let tempData = JSON.parse(window.localStorage.getItem("messageHistory"));
      setList(tempData);
    } else {
      console.log("Initial: no messageHistory");
      // fetch("", {
      //   method: "GET",
      //   mode: "cors",
      // })
      //   .then((res) => res.json())
      //   .then((json) => {
      //     console.log(json);
      //   });
      let tempData = [
        {
          head_url: "lorem",
          name: "lorem",
          message: {
            fromId: 14,
            content: "lorem",
            createDate: new Date(2014, 10, 16).getTime(),
          },
        },
      ];
      setList(tempData);
    }
  }, [initial]);

  // handle new message.
  useEffect(() => {
    if (data[0].name) {
      if (
        window.localStorage.getItem("messageHistory") &&
        window.localStorage.getItem("messageHistory").length > 0
      ) {
        let tempData = JSON.parse(
          window.localStorage.getItem("messageHistory")
        );
        tempData = tempData.concat(data);
        setList(tempData);
        console.log("onMessage: localStorage exist: ", tempData);
      } else if (
        !window.localStorage.getItem("messageHistory") &&
        !window.localStorage.getItem("messageHistory").length > 0
      ) {
        setList(data);
        console.log("onMessage: localStorage not exist: ", data);
      }
      console.log("listFirstTimeHandle: ", list);
    }
  }, [data]);

  // handle message list changes.
  useEffect(() => {
    if (list.length > 0) {
      window.localStorage.setItem("messageHistory", JSON.stringify(list));
      let tempList = [];
      let dataList = list;
      dataList.sort((a, b) => {
        return b.message.createDate - a.message.createDate;
      });
      console.log("sorted List:", dataList);
      dataList.map((item) => {
        tempList.push(item.message.fromId);
      });
      let tempSet = new Set(tempList);
      let tempFinalList = [];
      tempSet.forEach((item) => {
        for (let i = 0; i < dataList.length; i++) {
          if (item === dataList[i].message.fromId) {
            tempFinalList.push(dataList[i]);
            break;
          }
        }
      });
      console.log("finalList", tempFinalList);
      setFinalList(tempFinalList);
    }
  }, [list, setFinalList]);

  useEffect(() => {
    if (finalList.length > 0) {
      let tempList = finalList;
      tempList.map((item) => {
        item.selected = false;
      });
      console.log(tempList);
    }
  }, [finalList]);

  useEffect(() => {
    if (selectedId >= 0) {
      let tempList = finalList;
      tempList[selectedId].selected = true;
      setFinalList(tempList);
      setInitial((prev) => !prev);
    }
  }, [selectedId, finalList]);

  const updateSelectedList = (index) => {
    setSelectedId(index);
    console.log(index);
  };

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
        {finalList.length === 0 && (
          <h4
            style={{
              color: "rgba(255,255,255,.5)",
              position: "relative",
              zIndex: 1,
            }}
          >
            Renctly List Is Empty!
          </h4>
        )}
        {finalList.map((item, index) => {
          return (
            <div
              className="chatListItem"
              key={item.message.fromId}
              style={{
                backgroundColor: item.selected
                  ? "rgba(0,0,0,1)"
                  : "rgba(0,0,0,.4)",
                padding: ".3rem 0rem ",
                color: item.selected ? "#fff" : "rgba(255,255,255,.7)",
              }}
              onClick={() => updateSelectedList(index)}
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
                src={Avatar}
                alt={item.name}
                title={item.name}
              />
              <div className="chatListCard">
                <span className="chatListCardName">{item.name}</span>
                <div className="chatListCardDetail">
                  <span className="chatListCardDetailMsg">
                    {item.message.content}
                  </span>
                  <span className="chatListCardDetailDate">
                    {new Date(item.message.createDate).getFullYear() +
                      "/" +
                      new Date(item.message.createDate).getMonth() +
                      "/" +
                      new Date(item.message.createDate).getDate() +
                      " " +
                      new Date(item.message.createDate).getHours() +
                      ":" +
                      new Date(item.message.createDate).getMinutes()}
                  </span>
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
