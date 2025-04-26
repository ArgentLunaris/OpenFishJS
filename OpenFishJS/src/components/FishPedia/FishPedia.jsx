import { useContext, useEffect, useState } from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography, Link } from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import axios from "axios";
import styles from "./FishPedia.module.css";

import { styled } from '@mui/material/styles';
import { CaughtFishContext, KnownFishContext } from "../Contexts/Contexts";

export default function FishPedia({ open }) {

  const CustomAccordion = styled(Accordion)(({ theme }) => {
    return {
      backgroundColor: "rgba(255,255,255,0.1)",
      color: "white",
      marginLeft: 5,
      marginRight: 5,
      '.MuiAccordionDetails-root': { marginLeft: 5, marginRight: 5, },
      '.MuiAccordionSummary-root': { marginLeft: 5, marginRight: 5, },
    };
  });

  const [currentAnim, setCurrentAnim] = useState(styles.slideOut);

  const [expanded, setExpanded] = useState(false);

  const handleChange = (panel) => (_event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  }

  const [fishList, setFishList] = useState([]);
  const [knownFish, setKnownFish] = useContext(KnownFishContext);
  const [caughtFish, setCaughtFish] = useContext(CaughtFishContext);



  const getFish = () => {
    //while(localStorage.getItem("token") == null){};

    axios.get("api/fish/getAll", { headers: { Authorization: "Bearer " + localStorage.getItem("token") } })
      .then((response) => {
        let fuckingChrist = response.data;

        setFishList(fuckingChrist.map((fs) => {

          let gh = caughtFish.find(({ fishId }) => {

            return fishId === fs.id
          })


          if (gh != null) {

            return {
              id: fs.id,
              species: fs.species,
              description: fs.description,
              rarity: fs.rarity,
              wiki: fs.wiki,
              amount: gh.amount,
              record: gh.record
            }
          } else {
            return {
              id: fs.id,
              species: fs.species,
              description: fs.description,
              rarity: fs.rarity,
              wiki: fs.wiki,
              amount: 0,
              record: 0
            }
          }


        }))

      })
      .catch((error) => console.error(error));

  }

  useEffect(() => {

    if (open) {
      getFish();
    }



  }, [open, caughtFish])

  useEffect(() => {
    setCurrentAnim((open) ? styles.slideIn : styles.slideOut);


  }, [open])

  return <div className={`${styles.container} ${currentAnim}`}>
    {fishList.map((f, key) => {

      if (knownFish.includes(f.id)) {
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
            <Typography component="span" variant="text.secondary" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
              Caught: {f.amount}
            </Typography>

          </AccordionSummary>
          <AccordionDetails>
            <Typography fontFamily={"Itim"}>
              <Typography component="span" variant="text.secondary" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
                Record: {f.record} kg
              </Typography>
              <br />
              <br />
              {f.description}
              <br></br>
              <Link href={f.wiki} color="#ffffff">
                Read More
              </Link>
            </Typography>
          </AccordionDetails>
        </CustomAccordion>
      } else {
        return <CustomAccordion key={key} expanded={false}>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls={`panel${key}bh-content`}
            id={`panel${key}bh-header`}
          >
            <Typography component="span" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
              ???
            </Typography>
            <Typography component="span" variant="text.secondary" sx={{ width: '33%', flexShrink: 0 }} fontFamily={"Itim"}>
              Rarity: ???
            </Typography>
          </AccordionSummary>
        </CustomAccordion>
      }



    })}
  </div>

}