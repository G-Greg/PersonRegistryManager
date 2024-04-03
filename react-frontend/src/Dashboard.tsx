import React from 'react';
import CreatePage from './CreatePage';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ListPage from './ListPage';

export default function Dashboard() {

    return (
        <>
            <Navbar expand="lg" className="custom-nav" bg="dark" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand href="/">Person Registry Manager</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                        <Nav className="me-auto">
                            <Nav.Link href="/">Create</Nav.Link>
                            <Nav.Link href="/persons">List</Nav.Link>
                        </Nav>

                        <Navbar.Text>

                        </Navbar.Text>
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            <BrowserRouter>
                <Routes>
                    <Route path="/">
                        <Route index element={<CreatePage />} />
                        <Route path="/persons" element={<ListPage />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </>
    );
}