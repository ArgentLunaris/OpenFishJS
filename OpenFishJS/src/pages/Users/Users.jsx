import { useEffect, useState } from "react"
import styles from "./Users.module.css"
import { Button, createTheme, ThemeProvider, Typography } from "@mui/material"
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Users() {

    const [list, setList] = useState([]);

    const theme = createTheme({
        typography: {
            fontFamily: 'Itim'
        },
        button: {
            fontFamily: 'Itim'
        }
    })

    useEffect(() => {
        document.body.classList.add("users-body")
        return () => { document.body.classList.remove("users-body") }
    }, [])

    useEffect(() => {
        axios.get("api/listPlayers", { headers: { Authorization: "Bearer " + localStorage.getItem("token") } })
            .then((response) => setList(response.data.sort((a, b) => b.points - a.points)))
            .catch((error) => console.error(error));
    }, [])

    const navigate = useNavigate();

    const navToGame = () => {
        navigate("/")
    }

    return <ThemeProvider theme={theme}>
        <Button size="large" sx={{ color: "black", fontSize: 20, right: "10px", position: "fixed" }} onClick={navToGame}>Back to Game</Button>
        <Typography variant="h3" align="center">Leaderboards</Typography>
        {list.map((u, key) => {
            return <div className={styles.card} key={key}>
                <div>
                    <Typography variant="h6">{u.username}</Typography>
                    <Typography>{u.points} points</Typography>
                </div>
            </div>
        })}
    </ThemeProvider>
}