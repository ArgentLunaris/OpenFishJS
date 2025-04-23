import { Visibility, VisibilityOff } from "@mui/icons-material";
import { Button, FormControl, IconButton, InputAdornment, InputLabel, Modal, OutlinedInput, Paper, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";

export default function Login({ isOpen, setIsOpen, switchLR }) {

    const [showPassword, setShowPassword] = useState(false);

    const [errorMessage, setErrorMessage] = useState("");

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const [formInput, setFormInput] = useState({
        username: "",
        password: ""
    })

    const handleSubmit = (event) => {
        setErrorMessage("");
        axios.post("/api/login", {
            username: formInput.username,
            password: formInput.password
        })
            .then((response) => {
                if (response.data.token != "Fail") {
                    localStorage.setItem("token", response.data.token)
                    localStorage.setItem("id", response.data.userId)
                    setIsOpen(false)
                    window.location.reload(false)
                } else {
                    setErrorMessage("Incorrect username or password")
                }


            })
            .catch((error) => console.error(error))

    }


    const handleInput = (event) => {
        const { name, value } = event.target;
        setFormInput({ ...formInput, [name]: value })

    }


    return <>
        <Modal open={isOpen} sx={{ alignItems: "center", display: "flex", justifyContent: "center" }}>
            <FormControl component={Paper} sx={{ backgroundColor: "white", padding: 2, alignItems: "center", display: "flex", justifyContent: "center" }}>
                <Typography variant="h4" sx={{ fontFamily: "Itim" }} textAlign={"center"}>Log in</Typography>
                <TextField value={formInput.username} onChange={handleInput} name="username" label="Username" id="username" variant="outlined" sx={{ margin: 1, width: "100%" }}></TextField>
                <TextField value={formInput.password} onChange={handleInput} name="password"
                    sx={{ margin: 1, width: "100%" }}
                    id="password"
                    type={showPassword ? 'text' : 'password'}
                    slotProps={
                        {
                            input: {
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label={
                                                showPassword ? 'hide the password' : 'display the password'
                                            }
                                            onClick={handleClickShowPassword}
                                            edge="end"
                                        >
                                            {showPassword ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    </InputAdornment>
                                )
                            }
                        }
                    }
                    label="Password"
                />

                <Typography sx={{ fontFamily: "Itim" }} textAlign={"center"} color="error">{errorMessage}</Typography>
                <Button variant="outlined" sx={{ margin: 0.5, width: "100%" }} onClick={handleSubmit}>Log In</Button>
                <Button onClick={switchLR} sx={{ margin: 0.5, fontSize: 10, width: 20 }}>Register</Button>
            </FormControl>
        </Modal>
    </>
}