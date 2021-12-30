import { createSlice } from "@reduxjs/toolkit";
const initialValue = -1;
const chatListReducer = createSlice({
  name: "chatListId",
  initialState: { value: initialValue },
  reducers: {
    SetChatListId : (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { SetChatListId } = chatListReducer.actions;
export default chatListReducer.reducer;
