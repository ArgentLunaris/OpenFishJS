import { useEffect, useState } from "react";
import styles from "./FishingMiniGame.module.css"
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import axios from "axios";

export default function FishingMiniGame({ active, distance }) {

    const [pointerProg, setPointerProg] = useState(-6);
    const [isActive, setIsActive] = useState(false);

    const [greenPercent, setGreenPercent] = useState(10);

    const [fish, setFish] = useState({}); 

    useEffect(() => {

        axios.post("/api/fish/getFishByDistance", { distance: distance }, {headers:{Authorization:"Bearer " + localStorage.getItem("token")}})
              .then((response) => setFish(response.data))
              .catch((error) => console.error(error));
        
        switch (fish.rarity) {
            case "common":
                setGreenPercent(40);
                break;
            case "rare":
                setGreenPercent(20);
                break;
            case "epic":
                setGreenPercent(10);
                break;
        
            default:
                setGreenPercent(100);
                console.log(fish.rarity);
                
                break;
        }

        setIsActive(active)


        setPointerProg(9000);

        if (!active) {
            if (pointerProg + 6 >= 100 - greenPercent) {
                
                axios.post("api/caughtfish/addupdate", { 
                    userId: localStorage.getItem("id"),
                    fishId: fish.id,
                    amount: 1
                 }, {headers:{Authorization:"Bearer " + localStorage.getItem("token")}})
                 .then((response) => console.log(response))
                 .catch((error)=> console.error(error))

            } else {
                console.log("FAIL");

            }
        }



    }, [active, distance])

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