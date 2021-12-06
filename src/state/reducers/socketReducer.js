import { createSlice } from "@reduxjs/toolkit";
const initialValue = [{}];
const socketReducer = createSlice({
  name: "socket",
  initialState: { value: initialValue },
  reducers: {
    OnMessage: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { OnMessage } = socketReducer.actions;
export default socketReducer.reducer;
