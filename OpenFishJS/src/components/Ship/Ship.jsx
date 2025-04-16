import { useEffect, useState } from "react";
import placeholder_ship from "../../../src/assets/placeholder_ship.png";
import styles from "./Ship.module.css";

export default function Ship({ direction }) {

    const [animation, setAnimation] = useState(styles.idle)

    useEffect(() => {

        if (direction === 1) {
            setAnimation(styles.move_forwards)

        } else if (direction === -1) {
            setAnimation(styles.move_backwards)
        }
        else {
            setAnimation(styles.idle)
        }
    }, [direction, animation])


    return <>
        <img src={placeholder_ship} className={`${styles.ship} ${animation}`} />
    </>
}