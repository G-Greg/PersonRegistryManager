import React from 'react'
import { Col, Form, } from 'react-bootstrap';


export default function AddressCard() {


    return (
        <>
            <Col>
                <Form.Group className="mb-3" controlId="zipCode">
                    <Form.Label>zipCode</Form.Label>
                    <Form.Control type="number" placeholder="ZIP Code" value={""} onChange={() => { }} />
                </Form.Group>
            </Col>

            <Col>
                <Form.Group className="mb-3" controlId="city">
                    <Form.Label>City</Form.Label>
                    <Form.Control type="text" placeholder="City" value={""} onChange={() => { }} />
                </Form.Group>
            </Col>

            <Col>
                <Form.Group className="mb-3" controlId="street">
                    <Form.Label>Street</Form.Label>
                    <Form.Control type="text" placeholder="Street" value={""} onChange={() => { }} />
                </Form.Group>
            </Col>

            <Col>
                <Form.Group className="mb-3" controlId="houseNumber">
                    <Form.Label>House Number</Form.Label>
                    <Form.Control type="number" placeholder="House Number" value={""} onChange={() => { }} />
                </Form.Group>
            </Col>

        </>
    );
}