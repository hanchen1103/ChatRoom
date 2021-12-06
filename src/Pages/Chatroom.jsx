import React from "react";
import { ChatroomDetail } from ".";
import { ChatList } from "../Components";
import  Socket from "../socket/socket";
const Chatroom = () => {
  return (
    <div className="ChatroomMain">
      <Socket />
      <ChatList />
      <ChatroomDetail />
    </div>
  );
};

export default Chatroom;
