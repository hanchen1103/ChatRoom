import React, { useState, useEffect } from "react";
import useWebSocket, { ReadyState } from "react-use-websocket";
import { useDispatch, useSelector } from "react-redux";
import { OnMessage } from "../state/reducers/socketReducer";
const Socket = () => {
  const dispatch = useDispatch();
  const msgList = useSelector((state) => state.socket.value);
  const sendAction = useSelector((state) => state.socketAction.value);


  // useEffect(() => {
  //   sendMessage(sendAction);
  // }, [sendAction]);

  const userId = sessionStorage.getItem("userId");
  const [socketUrl, setSocketUrl] = useState(
    "ws://42.192.54.187:8080/newproject-0.0.1-SNAPSHOT/websocket/" + userId
  );

  const [messageHistory, setMessageHistory] = useState([]);
  const { sendMessage, lastMessage, readyState } = useWebSocket(socketUrl);
   useEffect(() => {
    if (lastMessage !== null) {
      setMessageHistory((prev) => prev.concat(lastMessage.data));
      dispatch(OnMessage(messageHistory));
      console.log('msg:', messageHistory);
    }
  }, [lastMessage, setMessageHistory]);

  const connectionStatus = {
    [ReadyState.CONNECTING]: "Connecting",
    [ReadyState.OPEN]: "Open",
    [ReadyState.CLOSING]: "Closing",
    [ReadyState.CLOSED]: "Closed",
    [ReadyState.UNINSTANTIATED]: "Uninstantiated",
  }[readyState];

  useEffect(()=>{
    console.log('readyState: ',connectionStatus);
  },[readyState])
  return null;
};

export default Socket;
