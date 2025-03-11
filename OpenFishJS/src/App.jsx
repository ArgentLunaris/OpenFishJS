import './App.css';
import Wave from 'react-wavify';
import {Box} from '@mui/material';
import { useState, useEffect } from 'react';
import Water from './components/Water/Water';

function App() {

  const viewportHeightDivision = 1.5;

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
        setWaterSpeed(5);
      }
      if( event.key == "ArrowLeft"){
        setWaterSpeed(15)
      }
    }

    const resetWaterSpeed = (event)=>{
      if(event.key == "ArrowRight" || event.key == "ArrowLeft"){
        setWaterSpeed(10);
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

      {/*<p>Fish Installed WIndos XP</p>*/}
      <Box flexGrow={1} flexDirection={"column"} height={screenSize} visibility={"hidden"}>
        
      
      </Box>

      <Water animSpeed={waterSpeed}></Water>

    
      
    </>
  )
}

export default App
