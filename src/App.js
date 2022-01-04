import React from "react";
import {
  Home,
  Error,
  Chat,
  LoginDetail,
  Chatroom,
  ChatroomDetail,
  Profile,
} from "./Pages/index";
import Login from "./Routes/login.jsx";
import Loged from "./Routes/Loged.jsx";
import Check from "./Routes/Check.jsx";
import { Initial } from "./Components/index";
import { QueryClient, QueryClientProvider } from "react-query";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
function App() {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        staleTime: Infinity,
      },
    },
  });
  return (
    <Router>
      <QueryClientProvider client={queryClient}>
        <Initial />
        <Routes>
          <Route path="/loged" element={<Loged />}>
            <Route path="homepage" element={<Home />}>
              <Route path="1" element={<Chatroom />}>
                <Route path=":id" element={<ChatroomDetail />} />
              </Route>
            </Route>
            <Route path="user/:id" element={<Profile />} />
            <Route path="chat" element={<Chat />} />
            <Route path="*" element={<Error />} />
          </Route>
          <Route path="log" element={<Login />}>
            <Route path="*" element={<Error />} />
            <Route path="login" element={<LoginDetail />} />
          </Route>
          <Route path="/" element={<Check />} />
          <Route path="*" element={<Error />} />
        </Routes>
      </QueryClientProvider>
    </Router>
  );
}

export default App;
