import React from 'react';
import Home from './Home';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { useNavigate } from 'react-router';

export default function Dashboard() {

    //const navigate = useNavigate();


    return (
        <>
            <Navbar expand="lg" className="custom-nav" bg="dark" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand href="/">Person Registry Manager</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                        <Nav className="me-auto">
                            <Nav.Link href="/persons">List</Nav.Link>
                        </Nav>

                        <Navbar.Text>
                            
                        </Navbar.Text>
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            <Container className="p-3">
                <Home />
            </Container>
        </>
    );
}