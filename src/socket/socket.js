import React, { useState, useEffect, useCallback } from "react";
import useWebSocket, { ReadyState } from "react-use-websocket";
import CheckIcon from "@mui/icons-material/Check";
import RefreshIcon from "@mui/icons-material/Refresh";
import { useDispatch, useSelector } from "react-redux";
import { OnMessage } from "../state/reducers/socketReducer";
import Snackbar from "@mui/material/Snackbar";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import SentimentVeryDissatisfiedIcon from "@mui/icons-material/SentimentVeryDissatisfied";
import DoDisturbOffIcon from "@mui/icons-material/DoDisturbOff";
import Slide from "@mui/material/Slide";

const Socket = () => {
  const [msg, setMsg] = useState("");
  const [open, setOpen] = useState(false);
  const dispatch = useDispatch();
  const msgList = useSelector((state) => state.socket.value);
  const sendAction = useSelector((state) => state.socketAction.value);
  const [messageHistory, setMessageHistory] = useState(new Array());
  const userId = sessionStorage.getItem("userId");
  const socketUrl =
    "ws://42.192.54.187:8080/newproject-0.0.1-SNAPSHOT/websocket/" + userId;

  const { sendMessage, lastMessage, readyState } = useWebSocket(socketUrl);

  useEffect(() => {
    console.log("what's send: ", sendAction);
    if (sendAction.toId) {
      try {
        sendMessage(JSON.stringify(sendAction));
      } finally {
        console.log("send new msg");
      }
    }
  }, [sendAction]);

  useEffect(() => {
    if (lastMessage !== null) {
      let tempData = JSON.parse(lastMessage.data);
      if (tempData.msg) {
        if (tempData.msg.length > 0)
          setMessageHistory((prev) => prev.concat(tempData.msg));
      }
    }
  }, [lastMessage, setMessageHistory]);

  useEffect(() => {
    if (messageHistory[0]) {
      console.log(messageHistory[0]);
      dispatch(OnMessage(messageHistory));
      console.log("msgHistory: ", messageHistory);
    }
  }, [messageHistory]);

  const connectionStatus = {
    [ReadyState.CONNECTING]: "Connecting",
    [ReadyState.OPEN]: "Open",
    [ReadyState.CLOSING]: "Closing",
    [ReadyState.CLOSED]: "Closed",
    [ReadyState.UNINSTANTIATED]: "Uninstantiated",
  }[readyState];

  useEffect(() => {
    console.log("readyState: ", connectionStatus);
    if (readyState === 0) {
      setOpen(true);
      setMsg("Connecting!");
    } else if (readyState === 1) {
      setOpen(true);
      setMsg("Connect success!");
    } else if (readyState === 3) {
      setOpen(true);
      setMsg("Connect closed!");
    } else if (readyState === 2) {
      setOpen(true);
      setMsg("Connect closing!");
    }
  }, [readyState]);

  function TransitionLeft(props) {
    return <Slide {...props} direction="left" />;
  }

  return (
    <Snackbar
      key={msg ? msg : undefined}
      open={open}
      TransitionComponent={TransitionLeft}
      autoHideDuration={6000}
      onClose={() => setOpen(false)}
      message={msg}
      action={
        <React.Fragment>
          {/* <Button color="primary" size="small" onClick={() => setOpen(false)}>
            UNDO
          </Button> */}
          {readyState == 0 && <RefreshIcon color="primary" />}
          {readyState == 1 && <CheckIcon color="success" />}
          {readyState == 2 && <SentimentVeryDissatisfiedIcon color="action" />}
          {readyState == 3 && <DoDisturbOffIcon sx={{ color: "red" }} />}
          <IconButton
            aria-label="close"
            color="inherit"
            size="small"
            sx={{ p: 0.5, fontSize: 0.1 }}
            onClick={() => setOpen(false)}
          >
            <CloseIcon />
          </IconButton>
        </React.Fragment>
      }
    ></Snackbar>
  );
};

export default Socket;
