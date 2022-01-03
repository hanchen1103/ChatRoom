import React from "react";
import styles from "./profile.module.css";
import { useParams } from "react-router-dom";
import Ava from "../Assets/pictures/robot-man.png";

const Profile = () => {
  const params = useParams();
  console.log(params.id);
  const msgAll = JSON.parse(localStorage.getItem("messageHistory"));
  let tempItem = {};
  msgAll.map((item) => {
    if (item.message.fromId == params.id) {
      tempItem = item;
    }
  });
  console.log(tempItem);
  return (
    <div className={styles.container}>
      <div className={styles.mainContent}>
        <img className={styles.avatar} src={Ava} alt={params.id} />
        <div>
          <h3 className={styles.name}>Name: {tempItem.name}</h3>
          <h4 className={styles.descri}>
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique
            saepe ex eos non. Libero odit aliquid repellendus magnam asperiores
            non necessitatibus ea tempora doloremque quia illo minus, dolorem
            autem fugit!
          </h4>
        </div>
      </div>
    </div>
  );
};

export default Profile;
