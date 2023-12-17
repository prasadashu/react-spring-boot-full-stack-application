import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import AddUser from './users/AddUser';

function App() {
  /*
    Function to render Main Application page
   */
  return (
    <div className="App">
      <Router>
        {/* Adding Navbar Component */}
        <Navbar/>

        {/* Adding Routes */}
        <Routes>
          {/* Adding route endpoint for Home Component */}
          <Route exact path="/" element={<Home/>}/>
          {/* Adding route endpoint for AddUser Component */}
          <Route exact path="/adduser" element={<AddUser/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
