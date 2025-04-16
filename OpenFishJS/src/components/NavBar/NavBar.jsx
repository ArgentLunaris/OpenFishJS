import { AppBar, Box, Button, createTheme, ThemeProvider, Toolbar, Typography } from '@mui/material';

export default function NavBar({ distance, togglePedia }) {

    const theme = createTheme({
        typography: {
            fontFamily: 'Itim'
        }
    })

    const logOut = () => {
        localStorage.clear();
        window.location.reload(false)
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
                }}>0 points</Typography>

                <Box flexGrow={1} sx={{
                    display: 'flex',
                    justifyContent: 'center'
                }}>
                    <Typography sx={{ fontSize: 'large' }}>{distance} meters from shore</Typography>
                </Box>


                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }}>Users</Button>
                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }}>Account</Button>
                <Button color='white' variant='text' sx={{ textTransform: 'capitalize', fontSize: 'large' }} onClick={logOut}>Log Out</Button>
            </Toolbar>
        </ThemeProvider>
    </AppBar>
}