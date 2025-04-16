import { useEffect, useState } from "react";
import styles from "./FishingMiniGame.module.css"
import PlayArrowIcon from '@mui/icons-material/PlayArrow';

export default function FishingMiniGame({ active }) {

    const [pointerProg, setPointerProg] = useState(-6);
    const [isActive, setIsActive] = useState(false);

    const [greenPercent, setGreenPercent] = useState(10);

    useEffect(() => {
        setIsActive(active)


        setPointerProg(9000);

        if (!active) {
            if (pointerProg + 6 >= 100 - greenPercent) {
                console.log("SUCESS");

            } else {
                console.log("FAIL");

            }
        }



    }, [active])

    useEffect(() => {
        let looper = setInterval(() => {

            setPointerProg(pointerProg + 1)
            if (pointerProg >= 96) {
                setPointerProg(-6)
            }

        }, 5)
        return () => clearInterval(looper);
    }, [pointerProg])

    if (!isActive) return null;

    return <div className={styles.container} sx={{ visibility: `${(isActive) ? "visible" : "hidden"}` }}>
        <p>Hi</p>
        <div className={styles.barContainer}>
            <div className={styles.bar}>
                <div className={styles.green} style={{ width: `${greenPercent}%` }}></div>
            </div>
            <PlayArrowIcon sx={{ rotate: "-90deg", left: `${pointerProg}%` }} className={styles.pointer}></PlayArrowIcon>
        </div>


    </div>
}