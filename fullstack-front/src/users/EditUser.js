import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditUser() {

    // Define navigate hook
    let navigate = useNavigate();

    // Define 'id'
    const {id} = useParams();

    // Define useState hook for user
    const [user,setUser] = useState({
        name: "",
        username: "",
        email: ""
    });

    // Deconstruct useState hook for user
    const {name, username, email} = user;

    // Define function to enter values to decon
    const onInputChange = (e) => {
        setUser({
            ...user, [e.target.name]: e.target.value
        })
    }

    // Define function to handle submission of form
    const onSubmit = async(e) => {
        // Prevent the form from posting information to URL as paramters
        e.preventDefault();
        // Post 'user' information to POST URL
        await axios.put(`http://localhost:8080/user/${id}`, user);
        // Navigate to Home page once user data is submitted
        navigate("/")
    };

    // Define function to load data for a particular user id when page loads
    const loadUser = async() => {
        const result = await axios.get(`http://localhost:8080/user/${id}`);
        setUser(result.data);
    }

    // Define function to call 'loadUser' function
    useEffect(() => {
        // Call the 'loadUser' function
        loadUser();
    }, []);


  return (
    <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                {/* Form Heading */}
                <h2 className="text-center m-4">Edit User</h2>

                {/* Define a form */}
                <form onSubmit={(e) => onSubmit(e)}>
                
                    {/* Division for name */}
                    <div className="mb-3">
                        <label htmlFor="Name" className="formLabel">Name</label>
                        <input 
                            type="text" 
                            className="form-control" 
                            placeholder="Enter you name" 
                            name="name" 
                            value={name}
                            onChange={(e) => onInputChange(e)}/>
                    </div>

                    {/* Division for username */}
                    <div className="mb-3">
                        <label htmlFor="Username" className="formLabel">Username</label>
                        <input 
                            type="text" 
                            className="form-control" 
                            placeholder="Enter you username" 
                            name="username" 
                            value={username}
                            onChange={(e) => onInputChange(e)}/>
                    </div>

                    {/* Division for email */}
                    <div className="mb-3">
                        <label htmlFor="Email" className="formLabel">E-mail</label>
                        <input 
                            type="text" 
                            className="form-control" 
                            placeholder="Enter you e-mail address" 
                            name="email" 
                            value={email}
                            onChange={(e) => onInputChange(e)}/>
                    </div>

                    {/* Button to submit our form data */}
                    <button type="submit" className="btn btn-outline-primary">Submit</button>

                    {/* Button to cancel addition of user */}
                    {/* Redirects to Home page using Router Link */}
                    <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>

                </form>
            </div>
        </div>
    </div>
  )
}
