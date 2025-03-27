
import waves from "../../assets/waves.svg";
import styles from "./Water.module.css";
import { useEffect, useState } from "react";
import Ship from "../Ship/Ship";

export default function Water({animSpeed, direction}) {

    let looper;

    const [waveProgress, setWaveProgress] = useState(0);

    useEffect(()=>{
        looper = setInterval(()=>{
            if(direction >= 0){
                setWaveProgress(waveProgress+1)
                if(waveProgress >= 520){
                    setWaveProgress(0)
                }
            }else{
                setWaveProgress(waveProgress-1)
                if(waveProgress <= 0){
                    setWaveProgress(520)
                }
            }
            
        }, animSpeed)
        return ()=> clearInterval(looper)
    },[waveProgress])

    return <div className={styles.bigContainer}>
    <div className={styles.wavesContainer}>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>
        <img src={waves} style={{left: -waveProgress}}></img>

    </div>;
    <Ship direction={direction} ></Ship>
    
    </div>
}