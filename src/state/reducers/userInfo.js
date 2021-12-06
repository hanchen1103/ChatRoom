import { createSlice } from "@reduxjs/toolkit";
const initialValue=[{}]
const userInfoReducer = createSlice({
    name:"socket",
    initialState:{value:initialValue},
    reducers:{
        addUserRecord:(state,action)=>{
            state.value = action.payload;
        },
        removeUserRecord:(state)=>{
            state.value = null;
        }
    }
});

export const {addUserRecord,removeUserRecord} = userInfoReducer.actions;
export default userInfoReducer.reducer;