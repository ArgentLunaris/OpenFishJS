import './App.css';
import Wave from 'react-wavify';
import {Box} from '@mui/material';
import { useState, useEffect } from 'react';
import Water from './components/Water/Water';
import NavBar from './components/NavBar/NavBar';

function App() {

  const viewportHeightDivision = 1.5;

  const baseWaterSpeed = 5;
  const waterSpeedDifference = 3;

  const [screenSize, setScreenSize] = useState(visualViewport.height/viewportHeightDivision);
  const [waterSpeed, setWaterSpeed] = useState(10);

  useEffect(()=>{
    visualViewport.addEventListener("resize", ()=>{
      setScreenSize(visualViewport.height/viewportHeightDivision)
    })
  }, [])

  useEffect(()=>{

    const inputHandler = (event)=>{
      if(event.key == "ArrowRight"){
        setWaterSpeed(baseWaterSpeed-waterSpeedDifference);
      }
      if( event.key == "ArrowLeft"){
        setWaterSpeed(baseWaterSpeed+waterSpeedDifference)
      }
    }

    const resetWaterSpeed = (event)=>{
      if(event.key == "ArrowRight" || event.key == "ArrowLeft"){
        setWaterSpeed(baseWaterSpeed);
      }
    }

    document.addEventListener("keydown", inputHandler)

    document.addEventListener("keyup", resetWaterSpeed)
    return ()=> {
      document.removeEventListener("keydown", inputHandler)
      document.removeEventListener("keyup", resetWaterSpeed)
    };
  }, [])

  return (
    <>

      <NavBar></NavBar>

      {/*<p>Fish Installed WIndos XP</p>*/}
      <Box flexGrow={1} flexDirection={"column"} height={screenSize} visibility={"hidden"}>
        
      
      </Box>

      <Water animSpeed={waterSpeed}>
        
      </Water>

    
      
    </>
  )
}

export default App
