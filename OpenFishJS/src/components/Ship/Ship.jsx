import placeholder_ship from "../../../src/assets/placeholder_ship.png";
import styles from "./Ship.module.css";

export default function Ship(){
    return<>
        <img src={placeholder_ship} className={styles.ship} />
    </>
}