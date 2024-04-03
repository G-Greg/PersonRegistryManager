import React from 'react'
import { Button, Col, Form, Row, } from 'react-bootstrap';
import { Address } from '../models/Address';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';


export default function Addresses({ addresses, handleAddressesChange, errors }: { addresses: Address[], handleAddressesChange: any, errors: any }) {
    const addAddress = () => {
        handleAddressesChange([...addresses, {} as Address]);
    }

    const editZipCode = (index: number, event) => {
        const newAddresses = [...addresses];
        newAddresses[index].zipCode = event.target.value;
        handleAddressesChange(newAddresses);
    }

    const editCity = (index: number, event) => {
        const newAddresses = [...addresses];
        newAddresses[index].city = event.target.value;
        handleAddressesChange(newAddresses);
    }

    const editStreet = (index: number, event) => {
        const newAddresses = [...addresses];
        newAddresses[index].street = event.target.value;
        handleAddressesChange(newAddresses);
    }

    const editHouseNumber = (index: number, event) => {
        const newAddresses = [...addresses];
        newAddresses[index].houseNumber = event.target.value;
        handleAddressesChange(newAddresses);
    }

    const deleteAddress = (index) => {
        const newAddresses = [...addresses];
        newAddresses.splice(index, 1);
        handleAddressesChange(newAddresses);
    }


    return (
        <>
            {
                addresses.map((address, index) => (
                    <Row>
                        <Col>
                            <Form.Group className="mb-3" controlId="zipCode">
                                <Form.Control
                                    type="number"
                                    placeholder="ZIP Code"
                                    value={address.zipCode}
                                    onChange={(event) => editZipCode(index, event)}
                                    required
                                    isInvalid={errors[`addresses[${index}].zipCode`]} />
                                <Form.Control.Feedback type="invalid">{errors[`addresses[${index}].zipCode`]}</Form.Control.Feedback>
                            </Form.Group>
                        </Col>

                        <Col>
                            <Form.Group className="mb-3" controlId="city">
                                <Form.Control
                                    type="text"
                                    placeholder="City"
                                    value={address.city}
                                    onChange={(event) => editCity(index, event)}
                                    required
                                    isInvalid={errors[`addresses[${index}].city`]} />
                                <Form.Control.Feedback type="invalid">{errors[`addresses[${index}].city`]}</Form.Control.Feedback>
                            </Form.Group>
                        </Col>

                        <Col>
                            <Form.Group className="mb-3" controlId="street">
                                <Form.Control
                                    type="text"
                                    placeholder="Street"
                                    value={address.street}
                                    onChange={(event) => editStreet(index, event)}
                                    required
                                    isInvalid={errors[`addresses[${index}].street`]}
                                />
                                <Form.Control.Feedback type="invalid">{errors[`addresses[${index}].street`]}</Form.Control.Feedback>
                            </Form.Group>
                        </Col>

                        <Col>
                            <Form.Group className="mb-3" controlId="houseNumber">
                                <Form.Control
                                    type="number"
                                    min={0}
                                    placeholder="House Number"
                                    value={address.houseNumber}
                                    onChange={(event) => editHouseNumber(index, event)}
                                    required
                                    isInvalid={errors[`addresses[${index}].houseNumber`]}
                                />
                                <Form.Control.Feedback type="invalid">{errors[`addresses[${index}].houseNumber`]}</Form.Control.Feedback>
                            </Form.Group>
                        </Col>


                        <Col>
                            <Form.Group className="mb-3" controlId="">
                                <Button variant="danger" onClick={() => deleteAddress(index)} disabled={addresses.length === 1}><FontAwesomeIcon icon={faTrash} /></Button>
                            </Form.Group>
                        </Col>
                    </Row>
                ))
            }
            <Form.Group className="mb-3" controlId="addressAdd">
                <Button variant="outline-primary" onClick={addAddress}>
                    Add more
                </Button>
            </Form.Group>
        </>
    );
}