import React,{useState,useEffect} from "react";
import { ChatroomDetail } from ".";
import { ChatList } from "../Components";
import Socket from "../socket/socket";
const Chatroom = () => {
  const [chatroomId, setChatroomId] = useState(-1);
  useEffect(() => {
    setChatroomId(chatroomId);
  });
  return (
    <div className="ChatroomMain">
      <Socket />
      <ChatList ChatroomId={chatroomId} />
      <ChatroomDetail />
    </div>
  );
};

export default Chatroom;
