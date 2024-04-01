import React from 'react';
import { Form, Col, Row, Card, Button, Container } from 'react-bootstrap';
import { Address } from '../models/Address';
import Addresses from './Addresses';
import PhoneNumbers from './PhoneNumbers';
import { Person } from '../models/Person';
import { createPerson } from '../api/PersonAPI';


export default function Create() {

    //Name
    const [name, setName] = React.useState("");
    const handleNameChange = (event) => {
        setName(event.target.value);
    };


    //Birthday
    const [birthday, setBirthday] = React.useState("");
    const handleBirthdayChange = (event) => {
        setBirthday(event.target.value);
    };


    //Birthplace
    const [birthplace, setBirthplace] = React.useState("");
    const handleBirthplaceChange = (event) => {
        setBirthplace(event.target.value);
    };


    //TAJ
    const [taj, setTaj] = React.useState("");
    const handleTajChange = (event) => {
        setTaj(event.target.value);
    };


    //Tax ID
    const [taxId, setTaxId] = React.useState("");
    const handleTaxIdChange = (event) => {
        setTaxId(event.target.value);
    };


    //Email
    const [email, setEmail] = React.useState("");
    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };


    //Addresses
    const [addresses, setAddresses] = React.useState<Address[]>([{} as Address]);
    const handleAddressesChange = (addresses: Address[]) => {
        setAddresses(addresses);
    };


    //PhoneNumbers
    const [phoneNumbers, setPhoneNumbers] = React.useState<string[]>([""]);
    const handlePhoneNumbersChange = (phoneNumbers: string[]) => {
        setPhoneNumbers(phoneNumbers);
    };


    const handleSubmit = (event) => {
        const person: Person = {
            name: name,
            birthdate: birthday,
            birthplace: birthplace,
            taj: parseInt(taj),
            taxId: parseInt(taxId),
            email: email,
            addresses: addresses,
            phoneNumbers: phoneNumbers
        } as Person;
        createPerson(person)
            .then(() => {
                alert("Person created");
            })
            .catch(error => {
                console.log(error);
            });
    };


    return (
        <Container className="p-2">
            <Form>

                <h1>Create</h1>
                <Card style={{ backgroundColor: "rgb(255,255,255,0.8)" }}>
                    <Card.Body>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="name">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control type="text" placeholder="Name" value={name} onChange={handleNameChange} />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="birthday">
                                    <Form.Label>Birthday</Form.Label>
                                    <Form.Control type="date" placeholder="Birthday" value={birthday} onChange={handleBirthdayChange} />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="birthplace">
                                    <Form.Label>Birthplace</Form.Label>
                                    <Form.Control type="text" placeholder="Birthplace" value={birthplace} onChange={handleBirthplaceChange} />
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="taj">
                                    <Form.Label>TAJ</Form.Label>
                                    <Form.Control type="text" placeholder="TAJ" value={taj} onChange={handleTajChange} />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="taxId">
                                    <Form.Label>Tax ID</Form.Label>
                                    <Form.Control type="text" placeholder="Tax ID" value={taxId} onChange={handleTaxIdChange} />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" placeholder="Email" value={email} onChange={handleEmailChange} />
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Form.Label>Addresses</Form.Label>
                            <Addresses addresses={addresses} handleAddressesChange={handleAddressesChange} />
                        </Row>
                        <Row>
                            <Form.Label>Phone Numbers</Form.Label>
                            <PhoneNumbers phoneNumbers={phoneNumbers} handlePhoneNumbersChange={handlePhoneNumbersChange} />
                        </Row>

                        <Button variant="primary" type="submit" onClick={handleSubmit}>
                            Submit
                        </Button>


                    </Card.Body>
                    <Card.Footer>
                        {new Date().toLocaleString()}
                    </Card.Footer>
                </Card>
            </Form>
        </Container>
    );
}