import { createSlice } from "@reduxjs/toolkit";
const initialValue = [{}];
const socketActionReducer = createSlice({
  name: "socket",
  initialState: { value: initialValue },
  reducers: {
    SendMessage: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { SendMessage } = socketActionReducer.actions;
export default socketActionReducer.reducer;
