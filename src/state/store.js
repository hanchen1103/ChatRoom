import { configureStore } from "@reduxjs/toolkit";
import accountReducer from "./reducers/accountReducer";
import socketReducer from "./reducers/socketReducer";
import socketActionReducer from "./reducers/socketActionReducer";
import userInfoReducer from "./reducers/userInfo";
import chatListIdReducer from "./reducers/chatListReducer";

const store = configureStore({
  reducer: {
      account: accountReducer,
      socket: socketReducer,
      userInfo: userInfoReducer,
      socketAction: socketActionReducer,
      chatListId: chatListIdReducer
  },
});

export default store;
