import React from "react";
import img4 from "../Assets/pictures/pexels-ash-modernafflatus-3662579.jpg";
import { useNavigate } from "react-router";
const Error = () => {
    const navigate = useNavigate();
  return (
    <div style={{justifyContent:'center',alignItems:'center',display:'flex',height:'100vh',flexDirection:'column'}}>
      <img
        src={img4}
        alt="*"
        style={{
          minWidth: "100vw",
          minHeight: "100vh",
          maxHeight: "100vh",
          maxWidth: "100vw",
          position: "absolute",
          left: 0,
          top: 0,
          zIndex: "-1",
          overflow: "hidden",
        }}
      />
      <h1 style={{color:'#fff',textAlign:'center',letterSpacing:'0.5rem',textShadow:'3px 3px 5px #000'}}>Error 404...</h1>
         <button className="errBtn" onClick={()=>navigate("/")} style={{margin:'5vh 0',padding:'1rem 2rem',color:'#fff',backgroundColor:'red',boxShadow:'2px 2px 5px #000,-2px -2px 5px #000'}}>Go Back </button>                                                       
    </div>
  );
};

export default Error;
