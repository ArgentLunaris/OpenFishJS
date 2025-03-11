
import waves from "../../../src/assets/waves.svg";
import styles from "./Water.module.css";
import { useEffect, useState } from "react";

export default function Water({animSpeed}) {


    return <div className={styles.container} style={{animationDuration: `${animSpeed}s`}}>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>
        <img src={waves}></img>

    </div>;
}