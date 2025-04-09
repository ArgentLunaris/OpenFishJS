import './App.css';
import {Box} from '@mui/material';
import { useState, useEffect } from 'react';
import Water from './components/Water/Water';
import NavBar from './components/NavBar/NavBar';
import FishPedia from './components/FishPedia/FishPedia';
import FishingMiniGame from './components/FishingMiniGame/FishingMiniGame';

function App() {

  const viewportHeightDivision = 1.5;

  const baseWaterSpeed = 10;
  const waterSpeedDifference = 5;

  const [screenSize, setScreenSize] = useState(visualViewport.height/viewportHeightDivision);
  const [waterSpeed, setWaterSpeed] = useState(10);

  const [shoreDistance, setShoreDistance] = useState(0);
  const [moveSpeed, setMoveSpeed] = useState(0);

  let moveTimer;

  const [isPediaOpen, setIsPediaOpen] = useState(false);

  const [isMinigameActive, setIsMinigameActive] = useState(false);

  const [canStartMinigame, setCanStartMinigame] = useState(true);

  useEffect(()=>{
    visualViewport.addEventListener("resize", ()=>{
      setScreenSize(visualViewport.height/viewportHeightDivision)
    })
  }, [])


  useEffect(()=>{

    moveTimer = setInterval(() => {
      if(shoreDistance>0 || moveSpeed > 0){
        setShoreDistance(shoreDistance + moveSpeed) 
      }
      
      
    }, 333)

    const inputHandler = (event)=>{
      
      if(isMinigameActive == false){
        if(event.key == "ArrowRight"){
          setWaterSpeed(baseWaterSpeed-waterSpeedDifference);
          setMoveSpeed(1)
        }
        if( event.key == "ArrowLeft"){
          if(shoreDistance > 0){
            setWaterSpeed(baseWaterSpeed+waterSpeedDifference)
            setMoveSpeed(-1)
          }else{
            setWaterSpeed(baseWaterSpeed);
            setMoveSpeed(0);
          }
          
        }
      }
    }

    const resetWaterSpeed = (event)=>{
      if(event.key == "ArrowRight" || event.key == "ArrowLeft"){
        setWaterSpeed(baseWaterSpeed);
        setMoveSpeed(0);
        
      }
      
      if(event.key == " "){
        if(canStartMinigame && !isMinigameActive){
          setIsMinigameActive(true)
        }else if(isMinigameActive){
          setIsMinigameActive(false)
          setCanStartMinigame(false)
          let looper = setInterval(()=>{
            setCanStartMinigame(true)
            clearInterval(looper)
          }, 2000)
        }
        
    }
    }

    document.addEventListener("keydown", inputHandler)

    document.addEventListener("keyup", resetWaterSpeed)
    return ()=> {
      document.removeEventListener("keydown", inputHandler)
      document.removeEventListener("keyup", resetWaterSpeed)
      clearInterval(moveTimer)
    };
  }, [shoreDistance, moveSpeed, isMinigameActive, canStartMinigame])

  const togglePedia = () => {
    setIsPediaOpen(!isPediaOpen);
  }

  return (
    <>

      <NavBar distance={shoreDistance} togglePedia={togglePedia}></NavBar>

      <FishPedia open={isPediaOpen}></FishPedia>
      <FishingMiniGame active={isMinigameActive}></FishingMiniGame>
      {/*<p>Fish Installed WIndos XP</p>*/}
      <Box flexGrow={1} flexDirection={"column"} height={screenSize} visibility={"hidden"}>
        
      
      </Box>

      <Water animSpeed={waterSpeed} direction={moveSpeed}>
        
      </Water>

      
    </>
  )
}

export default App
