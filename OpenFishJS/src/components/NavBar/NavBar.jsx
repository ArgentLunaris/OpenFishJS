import { AppBar, Box, Button, createTheme, ThemeProvider, Toolbar, Typography} from '@mui/material';

export default function NavBar(){

    const theme = createTheme({
        typography: {
            fontFamily:'Itim'
        }
    })

    return <AppBar position='static' th sx={{
        backgroundColor: '#00000055',
        boxShadow: 'none',
        color:'white'
    }}>
        <ThemeProvider theme={theme}>
        <Toolbar>
            <Button color='white' variant='text' sx={{textTransform: 'capitalize', fontSize:'large'}}>FishPedia</Button>

            <Typography sx={{
                position:'relative',
                left:'10%',
                fontSize:'large'
                }}>0 points</Typography>
            
            <Box flexGrow={1} sx={{
                display:'flex',
                justifyContent:'center'
            }}>
                <Typography sx={{fontSize:'large'}}>0 meters from shore</Typography>
            </Box>
            

            <Button color='white' variant='text' sx={{textTransform: 'capitalize', fontSize:'large'}}>Users</Button>
            <Button color='white' variant='text' sx={{textTransform: 'capitalize', fontSize:'large'}}>Login</Button>
            <Button color='white' variant='text' sx={{textTransform: 'capitalize', fontSize:'large'}}>Sign-Up</Button>
        </Toolbar>
        </ThemeProvider>
    </AppBar>
}