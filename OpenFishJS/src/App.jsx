import { BrowserRouter, Route, Routes } from "react-router-dom";
import Game from "./pages/Game/Game"
import Users from "./pages/Users/Users";

export default function App(){
    return <BrowserRouter>
        <Routes>
            <Route index element={<Game></Game>} />
            <Route path="/users" element={<Users></Users>}/>
        </Routes>
    </BrowserRouter>
}