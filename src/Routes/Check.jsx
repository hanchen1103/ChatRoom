import { useEffect } from "react";
import { useNavigate } from "react-router";
const Check = () => {
  const navigate = useNavigate();
  useEffect(() => {
    if (sessionStorage.getItem("userId") || localStorage.getItem("userId"))
      navigate("./loged/homepage");
    else navigate("/log/login");
  });
  return null;
};

export default Check;
