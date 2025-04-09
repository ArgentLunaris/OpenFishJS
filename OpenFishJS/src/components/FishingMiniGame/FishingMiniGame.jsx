import { useEffect, useState } from "react";
import styles from "./FishingMiniGame.module.css"
import PlayArrowIcon from '@mui/icons-material/PlayArrow';

export default function FishingMiniGame({active}){

    const [pointerProg, setPointerProg] = useState(-6);
    const [isActive, setIsActive] = useState(false);

    useEffect(()=>{
        console.log(pointerProg);
        
        setIsActive(active)
        

        setPointerProg(9000);

        
        
        
    }, [active])

    useEffect(()=>{
        let looper = setInterval(()=>{

            setPointerProg(pointerProg+1)
            if(pointerProg >= 96){
                setPointerProg(-6)
            }

        }, 5)
        return () => clearInterval(looper);
    },[pointerProg])

    if (!isActive) return null;

    return <div className={styles.container}  sx={{visibility: `${(isActive)? "visible": "hidden"}`}}>
        <p>Hi</p>
        <div className={styles.barContainer}>
            <div className={styles.bar}>
                <div className={styles.green}></div>
            </div>
            <PlayArrowIcon sx={{rotate: "-90deg", left: `${pointerProg}%`}} className={styles.pointer}></PlayArrowIcon>
        </div>
        

    </div>
}