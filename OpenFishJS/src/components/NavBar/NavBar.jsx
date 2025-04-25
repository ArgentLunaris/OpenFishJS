import { AppBar, Box, Button, createTheme, ThemeProvider, Toolbar, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function NavBar({ distance, togglePedia, points }) {

    const theme = createTheme({
        typography: {
            fontFamily: 'Itim'
        }
    })

    const logOut = () => {
        localStorage.clear();
        window.location.reload(false)
    }

    const navigate = useNavigate();

    const navToUsers = () =>{
        navigate("/users")
    }

    return <AppBar position='static' sx={{
        backgroundColor: '#00000055',
        boxShadow: 'none',
        color: 'white'
    }}>
        <ThemeProvider theme={theme}>
            <Toolbar>
                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }} onClick={() => { togglePedia() }}>FishPedia</Button>

                <Typography sx={{
                    position: 'relative',
                    left: '10%',
                    fontSize: 'large'
                }}>{points} points</Typography>

                <Box flexGrow={1} sx={{
                    display: 'flex',
                    justifyContent: 'center'
                }}>
                    <Typography sx={{ fontSize: 'large' }}>{distance} meters from shore</Typography>
                </Box>


                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }} onClick={navToUsers}>Users</Button>
                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }}>Account</Button>
                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }} onClick={logOut}>Log Out</Button>
            </Toolbar>
        </ThemeProvider>
    </AppBar>
}