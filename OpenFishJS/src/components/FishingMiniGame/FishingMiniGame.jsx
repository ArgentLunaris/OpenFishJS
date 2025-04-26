import { useContext, useEffect, useRef, useState } from "react";
import styles from "./FishingMiniGame.module.css"
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import axios from "axios";
import { KnownFishContext, CaughtFishContext } from "../Contexts/Contexts";

export default function FishingMiniGame({ active, distance, setPoints, done }) {

    const [pointerProg, setPointerProg] = useState(-6);
    const [isActive, setIsActive] = useState(false);

    const [greenPercent, setGreenPercent] = useState(10);

    const [fish, setFish] = useState({});

    const [knownFish, setKnownFish] = useContext(KnownFishContext);
    const [caughtFish, setCaughtFish] = useContext(CaughtFishContext);

    const [name, setName] = useState("");

    const [isNewFish, setIsNewFish] = useState(false);
    const [isCaught, setIsCaught] = useState(false);
    const [imDone, setImDone] = useState(false);

    const egyValami = useRef(distance);

    useEffect(() => {

        

        //this shit right here is why i'll never become a web developer even if my life depends on it
        //i swear to god this is the most unintuitive language i ever had the displeasure of working with, and i had classes for dart + flutter
        //i'm actually starting to prefer its parenthesis and comma hell compared to whatever in the name of all that is holy this is
        //to whoever has to read and grade all this, i am sorry you have to read this abomination of a useEffect, i tried to make this look normal, but the React devs looked down at my efforts and decided to spit in my face

        const shit = async () => {

            if (!done) {
                setImDone(false);




                if (localStorage.getItem("token") != null) {
                    if (active) {

                        let fuckThisFuckingLanguage = {}
                        await axios.post("/api/fish/getFishByDistance", { distance: egyValami.current }, { headers: { Authorization: "Bearer " + localStorage.getItem("token") } })
                            .then((response) => {
                                setFish(response.data)
                                fuckThisFuckingLanguage = response.data


                                if (knownFish.includes(fuckThisFuckingLanguage.id)) {
                                    setName(fuckThisFuckingLanguage.species);
                                } else {
                                    setName("???")
                                }
                                //console.log(response);

                            })
                            .catch((error) => console.error(error));

                        // console.log(fuckThisFuckingLanguage);



                        switch (fuckThisFuckingLanguage.rarity) {
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
                                console.log(fuckThisFuckingLanguage.rarity);

                                break;
                        }
                    }

                }

            } else if (active) {

                if (pointerProg + 6 >= 100 - greenPercent) {

                    axios.post("api/caughtfish/addupdate", {
                        userId: localStorage.getItem("id"),
                        fishId: fish.id,
                        weight: fish.weight
                    }, { headers: { Authorization: "Bearer " + localStorage.getItem("token") } })
                        .then((response) => {
                            setPoints()
                            setImDone(true);
                            setIsCaught(true)
                            let daaataaa = response.data;
                            let index = caughtFish.findIndex((fish) => daaataaa.fishId === fish.fishId)
                            
                            if (index === -1) {
                                
                                setCaughtFish(caughtFish.concat(daaataaa))
                                
                            } else {
                                
                                let locallyCaughtFish = caughtFish.slice();
                                let existingData = {...locallyCaughtFish[index]};
                                existingData = {
                                    ...existingData,
                                    record: daaataaa.record,
                                    amount: daaataaa.amount
                                }
                                locallyCaughtFish[index] = existingData;
                                setCaughtFish(locallyCaughtFish)

                            }


                        })
                        .catch((error) => console.error(error))


                    if (!knownFish.includes(fish.id)) {
                        setKnownFish(knownFish.concat(fish.id));
                        setIsNewFish(true)
                    } else {
                        setIsNewFish(false)
                    }

                } else {
                    setImDone(true);
                    setIsCaught(false);
                }
            }

            setIsActive(active)


            setPointerProg(9000);


        }

        shit();

    }, [active, done])


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

    return <div className={styles.container}>

        {imDone
            ? isCaught
                ?
                <>
                    <h2>Caught!</h2>
                    <h5>{isNewFish ? "New!" : ""}</h5>
                    <h4>{fish.species} ({fish.weight} kg)</h4>
                    <p>{Math.floor(fish.weight * 10)} points</p>
                </>
                : <>
                    <h3>Aw dang it!</h3>
                    <h3>It got away!</h3>
                </>
            : <>
                <p>{name}</p>
                <div className={styles.barContainer}>
                    <div className={styles.bar}>
                        <div className={styles.green} style={{ width: `${greenPercent}%` }}></div>
                    </div>
                    <PlayArrowIcon sx={{ rotate: "-90deg", left: `${pointerProg}%` }} className={styles.pointer}></PlayArrowIcon>
                </div>
            </>
        }




    </div >
}