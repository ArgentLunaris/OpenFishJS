import { Visibility, VisibilityOff } from "@mui/icons-material";
import { Button, FormControl, IconButton, InputAdornment, InputLabel, Modal, OutlinedInput, Paper, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";

export default function Register({ isOpen, setIsOpen, switchLR }) {

    const [showPassword, setShowPassword] = useState(false);
    const [showPassword2, setShowPassword2] = useState(false);

    const [errorMessage, setErrorMessage] = useState("");

    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleClickShowPassword2 = () => setShowPassword2((show) => !show);

    const [formInput, setFormInput] = useState({
        username: "",
        email: "",
        password: "",
        password2: ""
    })

    const handleSubmit = (event) => {
        setErrorMessage("");
        event.preventDefault();
        if (/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}/.test(formInput.email)) {
            if (formInput.password === formInput.password2) {
                axios.post("/api/register", {
                    username: formInput.username,
                    email: formInput.email,
                    password: formInput.password
                }).then(() => {
                    axios.post("/api/login", {
                        username: formInput.username,
                        password: formInput.password
                    })
                        .then((response) => {
                            localStorage.setItem("token", response.data)
                            setIsOpen(false)
                        })
                        .catch((error) => console.error(error))
                    
                }).catch((error) => console.error(error))

            } else {
                setErrorMessage("The two passwords do not match!")
            }
        }else{
            setErrorMessage("Invalid Email!")
        }


    }

    const handleInput = (event) => {
        const { name, value } = event.target;
        setFormInput({ ...formInput, [name]: value })

    }


    return <>
        <Modal open={isOpen} sx={{ alignItems: "center", display: "flex", justifyContent: "center" }}>
            <FormControl component={Paper} sx={{ backgroundColor: "white", padding: 2, alignItems: "center", display: "flex", justifyContent: "center" }}>
                <Typography variant="h4" sx={{ fontFamily: "Itim" }} textAlign={"center"}>Register</Typography>
                <TextField value={formInput.username} onChange={handleInput} name="username" label="Username" id="username" variant="outlined" sx={{ margin: 1, width: "100%" }}></TextField>
                <TextField value={formInput.email} onChange={handleInput} name="email" label="Email" id="email" variant="outlined" sx={{ margin: 1, width: "100%" }}></TextField>
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
                <TextField value={formInput.password2} onChange={handleInput} name="password2"
                    sx={{ margin: 1, width: "100%" }}
                    id="password2"
                    type={showPassword2 ? 'text' : 'password'}
                    slotProps={
                        {
                            input: {
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label={
                                                showPassword2 ? 'hide the password' : 'display the password'
                                            }
                                            onClick={handleClickShowPassword2}
                                            edge="end"
                                        >
                                            {showPassword2 ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    </InputAdornment>
                                )
                            }
                        }
                    }
                    label="Confirm password"
                />
                <Typography sx={{ fontFamily: "Itim" }} textAlign={"center"} color="error">{errorMessage}</Typography>
                <Button variant="outlined" sx={{ margin: 0.5, width: "100%" }} onClick={handleSubmit}>Register</Button>
                <Button onClick={switchLR} sx={{ margin: 0.5, fontSize: 10, width: 20 }}>Log In</Button>
            </FormControl>
        </Modal>
    </>
}