import { configureStore } from "@reduxjs/toolkit";
import accountReducer from "./reducers/accountReducer";
import socketReducer from "./reducers/socketReducer";
import socketActionReducer from "./reducers/socketActionReducer";
import userInfoReducer from "./reducers/userInfo";
const store = configureStore({
  reducer: {
      account: accountReducer,
      socket: socketReducer,
      userInfo: userInfoReducer,
      socketAction: socketActionReducer,
  },
});

export default store;
