import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
  return (
    <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                {/* Define navigation bar */}
                <a className="navbar-brand" href="#">Full Stack Application</a>
                <button className="navbar-toggler" 
                        type="button" data-bs-toggle="collapse" 
                        data-bs-target="#navbarSupportedContent" 
                        aria-controls="navbarSupportedContent" 
                        aria-expanded="false" 
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                {/* Map the route endpoint from Main Application page to Link */}
                <Link className="btn btn-outline-light" to="/adduser">Add User</Link>
            </div>
        </nav>
    </div>
  )
}
