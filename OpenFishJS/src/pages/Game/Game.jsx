import  './Game.module.css';
import { Box } from '@mui/material';
import { useState, useEffect } from 'react';
import Water from '../../components/Water/Water';
import NavBar from '../../components/NavBar/NavBar';
import FishPedia from '../../components/FishPedia/FishPedia';
import FishingMiniGame from '../../components/FishingMiniGame/FishingMiniGame';
import Login from '../../components/UserManagement/Login/Login';
import Register from '../../components/UserManagement/Register/Register';
import KnownFishContext from '../../components/Contexts/KnownFishContext';
import axios from 'axios';

function Game() {

  const viewportHeightDivision = 1.5;

  const baseWaterSpeed = 10;
  const waterSpeedDifference = 5;

  const [screenSize, setScreenSize] = useState(visualViewport.height / viewportHeightDivision);
  const [waterSpeed, setWaterSpeed] = useState(10);

  const [shoreDistance, setShoreDistance] = useState(0);
  const [moveSpeed, setMoveSpeed] = useState(0);

  let moveTimer;

  const [isPediaOpen, setIsPediaOpen] = useState(false);

  const [isMinigameActive, setIsMinigameActive] = useState(false);

  const [canStartMinigame, setCanStartMinigame] = useState(true);


  //axios.defaults.headers.common.Authorization = token;

  const [properOS, setProperOS] = useState(true);

  const [isLoginOpen, setIsLoginOpen] = useState(false)
  const [isRegisterOpen, setIsRegisterOpen] = useState(false)

  const [knownFish, setKnownFish] = useState([]);

  useEffect(() => {



    if (localStorage.getItem("token") == null) {
      setIsRegisterOpen(true)
    } else {
      axios.post("api/checkToken", Number(localStorage.getItem("id")), { headers: { Authorization: "Bearer " + localStorage.getItem("token"), "Content-Type": "application/json" } })
        .then((response) => {

          if (!response.data) {
            localStorage.clear();
            window.location.reload(false)
          }

        })
        .catch((error) => console.error(error))
    }


    if (window.navigator.appVersion.indexOf("iPhone") !== -1 || window.navigator.appVersion.indexOf("And") !== -1) {
      setProperOS(false);
    }
  }, [])

  useEffect(() => {
    visualViewport.addEventListener("resize", () => {
      setScreenSize(visualViewport.height / viewportHeightDivision)
    })
  }, [])


  useEffect(() => {

    moveTimer = setInterval(() => {
      if (shoreDistance > 0 || moveSpeed > 0) {
        setShoreDistance(shoreDistance + moveSpeed)
      }


    }, 333)

    const inputHandler = (event) => {
      if (!isLoginOpen && !isRegisterOpen) {
        if (isMinigameActive == false) {
          if (event.key == "ArrowRight") {
            setWaterSpeed(baseWaterSpeed - waterSpeedDifference);
            setMoveSpeed(1)
          }
          if (event.key == "ArrowLeft") {
            if (shoreDistance > 0) {
              setWaterSpeed(baseWaterSpeed + waterSpeedDifference)
              setMoveSpeed(-1)
            } else {
              setWaterSpeed(baseWaterSpeed);
              setMoveSpeed(0);
            }

          }
        }
      }
    }

    const resetWaterSpeed = (event) => {
      if (!isLoginOpen && !isRegisterOpen) {


        if (event.key == "ArrowRight" || event.key == "ArrowLeft") {
          setWaterSpeed(baseWaterSpeed);
          setMoveSpeed(0);

        }

        if (event.key == " " && (!isLoginOpen && !isRegisterOpen)) {
          if (canStartMinigame && !isMinigameActive) {

            setIsMinigameActive(true)



          } else if (isMinigameActive) {
            setIsMinigameActive(false)
            setCanStartMinigame(false)
            let looper = setInterval(() => {
              setCanStartMinigame(true)
              clearInterval(looper)
            }, 2000)
          }

        }
      }
    }

    document.addEventListener("keydown", inputHandler)

    document.addEventListener("keyup", resetWaterSpeed)
    return () => {
      document.removeEventListener("keydown", inputHandler)
      document.removeEventListener("keyup", resetWaterSpeed)
      clearInterval(moveTimer)
    };
  }, [shoreDistance, moveSpeed, isMinigameActive, canStartMinigame, isLoginOpen, isRegisterOpen])

  const togglePedia = () => {
    setIsPediaOpen(!isPediaOpen);
  }

  const switchLR = () => {
    setIsLoginOpen(!isLoginOpen)
    setIsRegisterOpen(!isRegisterOpen)
  }

  const knownFishInit = () => {
    axios.post("api/caughtfish/getAllForUser", { userId: localStorage.getItem("id") }, { headers: { Authorization: "Bearer " + localStorage.getItem("token") } })
      .then((response) => {


        let temp = [];
        response.data.map((f) => {
          temp.push(f.fishId)
        })
        setKnownFish(temp);


      })
      .catch((error) => console.error(error));
  }

  useEffect(() => {
    if (localStorage.getItem("token") != null) {
      knownFishInit()
    }

  }, [])

  useEffect(()=>{
    document.body.classList.add("game-body")
    return ()=>{document.body.classList.remove("game-body")}
  },[])

  if (!properOS) {
    return (
      <p style={{ textAlign: "center" }}>This app was not made for mobile devices. Please switch to a computer.</p>

    )
  }

  return (
    <>

      <Login isOpen={isLoginOpen} setIsOpen={setIsLoginOpen} switchLR={switchLR}></Login>
      <Register isOpen={isRegisterOpen} setIsOpen={setIsRegisterOpen} switchLR={switchLR}></Register>

      <NavBar distance={shoreDistance} togglePedia={togglePedia}></NavBar>

      <KnownFishContext.Provider value={[knownFish, setKnownFish]}>
        <FishPedia open={isPediaOpen}></FishPedia>
        <FishingMiniGame active={isMinigameActive} distance={shoreDistance}></FishingMiniGame>
      </KnownFishContext.Provider>
      {/*<p>Fish Installed WIndos XP</p>*/}
      <Box flexGrow={1} flexDirection={"column"} height={screenSize} visibility={"hidden"}>


      </Box>

      <Water animSpeed={waterSpeed} direction={moveSpeed}>

      </Water>


    </>
  )

}

export default Game
