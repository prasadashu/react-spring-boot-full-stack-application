import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import AddUser from './users/AddUser';
import EditUser from './users/EditUser';
import ViewUser from './users/ViewUser';

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
        {/*
           Route Endpoints are declared here.
           A mapping of Endpoint to Component is done here
           Execution of routing is done via 'Link' tag using 'to="/sampleEndpoint"'
        */}

        <Routes>

          {/* Adding route endpoint for Home Component */}
          <Route exact path="/" element={<Home/>}/>
          {/* Adding route endpoint for AddUser Component */}
          <Route exact path="/adduser" element={<AddUser/>}/>
          {/* Adding route endpoint for EditUser Component */}
          <Route exact path="/edituser/:id" element={<EditUser/>}/>
          {/* Adding route endpoint for ViewUser Component */}
          <Route exact path="/viewuser/:id" element={<ViewUser/>}/>

        </Routes>
      </Router>
    </div>
  );
}

export default App;
