import { useEffect, useState } from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography, Link } from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import axios from "axios";
import styles from "./FishPedia.module.css";

import { styled } from '@mui/material/styles';

export default function FishPedia({open}){

  const CustomAccordion = styled(Accordion)(({ theme }) => {
    return {
      backgroundColor: "rgba(255,255,255,0.1)",
      color:"white",
      marginLeft:5,
      marginRight:5,
      '.MuiAccordionDetails-root': {marginLeft:5, marginRight:5,},
      '.MuiAccordionSummary-root': {marginLeft:5, marginRight:5,},
    };
  });

    const [currentAnim, setCurrentAnim] = useState(styles.slideOut); 
    
    const [expanded, setExpanded] = useState(false);

    const handleChange = (panel) => (_event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    }

    const [fishList, setFishList] = useState([]);
    
    const getFish = () => {
      while(localStorage.getItem("token") == null){};

      axios.get("api/fish/getAll")
        .then((response) => setFishList(response.data))
        .catch((error) => console.error(error));
    } 

    useEffect(()=>{
        getFish();
        
        
    },[])

    useEffect(()=>{
        console.log(fishList);
    }, [fishList])

    useEffect(()=>{
      setCurrentAnim((open)?styles.slideIn:styles.slideOut);
      
      
    },[open])

    return <div className={`${styles.container} ${currentAnim}`}>
        {fishList.map((f, key) => { 
            return <CustomAccordion key={key} expanded={expanded === `panel${key}`} onChange={handleChange(`panel${key}`)} >
            <AccordionSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls={`panel${key}bh-content`}
              id={`panel${key}bh-header`}
            >
              <Typography component="span" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
                {f.species}
              </Typography>
              <Typography component="span" variant="text.secondary" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
                Rarity: {f.rarity}
              </Typography>
            </AccordionSummary>
            <AccordionDetails>
              <Typography fontFamily={"Itim"}>
                ${f.description}
                <br></br>
                <Link href={f.wiki} color="#ffffff" target="_blank" rel="noopener">
                Read More
                </Link>
              </Typography>
            </AccordionDetails>
          </CustomAccordion>
        })}
    </div>

}