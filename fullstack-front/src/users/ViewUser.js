import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom'

export default function ViewUser() {
    // Define hook to get user details based on id
    const [user, setUser] = useState({
        name: "",
        username: "",
        email: ""
    });

    // Define variable to store user ID
    const {id} = useParams();

    // Define function to get user details based on user ID
    const loadUser = async(e) => {
        // Get user details and store in result
        const result = await axios.get(`http://localhost:8080/api/v1/user/${id}`);
        // Set user details in page based on data obtained in 'result' variable
        setUser(result.data);
    };

    // Define hook which gets triggered when page loads
    useEffect(() => {
        // Trigger function to load user details
        loadUser();
    },[]);

  return (
    <div className="container">
        <div className="row">
            <div className="container">
                <div className="row">
                    <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                        {/* Form Heading */}
                        <h2 className="text-center m-4">User Details</h2>

                        {/* User Details */}
                        <div className="card">
                            <div className="card-header">
                                Details of user id: {user.id}
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">
                                        <b>Name: </b>
                                        {user.name}
                                    </li>
                                    <li className="list-group-item">
                                        <b>Username: </b>
                                        {user.username}
                                    </li>
                                    <li className="list-group-item">
                                        <b>Email: </b>
                                        {user.email}
                                    </li>
                                </ul>
                            </div>
                        </div>

                        {/* Button to navigate back to Home page */}
                        <Link className="btn btn-primary my-2" to={"/"}>Home</Link>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}
