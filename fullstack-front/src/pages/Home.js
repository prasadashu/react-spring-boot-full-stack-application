import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'

export default function Home() {
    // Define variable and state function using 'useState' Hook
    const [users,setUsers] = useState([]);

    // Define async function to load user data from Spring Boot API
    const loadUsers = async() => {
        const result = await axios.get("http://localhost:8080/users");
        setUsers(result.data);
    };

    // Define function to delete user data using Spring Boot API
    const deleteUser = async(id) => {
        await axios.delete(`http://localhost:8080/user/${id}`);
        loadUsers();
    };

    // Call the 'useEffect' Hook
    useEffect(() => {
        // Call the load users function
        loadUsers();
    },[]);

  return (
    <div className="container">
        <div className="py-4">
            <table className="table border shadow">
                <thead>
                    {/* Defining headers for table */}
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {/* Creating Map to dump data fetched from Database */}
                    {
                        users.map((user, index) => (
                            <tr>
                                <th scope="row" key={index}>{index + 1}</th>
                                <td>{user.name}</td>
                                <td>{user.username}</td>
                                <td>{user.email}</td>
                                <td>
                                    <Link className="btn btn-primary mx-2" to={`/viewuser/${user.id}`}>View</Link>
                                    <Link className="btn btn-outline-primary mx-2" to={`/edituser/${user.id}`}>Edit</Link>
                                    <button className="btn btn-danger mx-2" onClick={() => deleteUser(user.id)}>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    </div>
  )
}
