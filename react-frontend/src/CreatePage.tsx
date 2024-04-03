import React from 'react';
import { Form, Col, Row, Card, Button, Container } from 'react-bootstrap';
import { Address } from './models/Address';
import Addresses from './components/Addresses';
import PhoneNumbers from './components/PhoneNumbers';
import { Person } from './models/Person';
import { createPerson } from './api/PersonAPI';
import * as yup from "yup";


export default function CreatePage() {
    const schema = yup.object().shape({
        name: yup.string().min(3).max(50).required("Name is required"),
        birthdate: yup.string().required("Birthday is required"),
        birthplace: yup.string().required("Birthplace is required"),
        email: yup.string().email("Email not valid").required("Email is required"),
        taj: yup.number().required("TAJ is required"),
        taxId: yup.number().required("Tax ID is required"),
        addresses: yup.array().of(yup.object().shape({
            zipCode: yup.number().required("ZIP Code is required"),
            city: yup.string().required("City is required"),
            street: yup.string().required("Street is required"),
            houseNumber: yup.number().required("House number is required")
        })),
        phoneNumbers: yup.array().min(1).of(yup.string().min(10).max(11).required("Phone number not valid"))
    });


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


    const [errors, setErrors] = React.useState({});
    const [validated, setValidated] = React.useState(false);
    const handleSubmit = (event) => {
        setValidated(true);
        event.preventDefault();

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

        setErrors({});
        schema.validate(person, { abortEarly: false }).then(() => {
            createPerson(person)
                .then(() => {
                    alert("Person created");
                })
                .catch(error => {
                    console.log(error);
                });
        })
            .catch(error => {
                error.inner.forEach((err) => {
                    setErrors(errors => ({ ...errors, [err.path]: err.message }));
                });
            });

    };


    return (
        <Container className="p-2">
            <Form noValidate validated={validated} onSubmit={handleSubmit}>

                <h1>Create</h1>
                <Card style={{ backgroundColor: "rgb(255,255,255,0.8)" }}>
                    <Card.Body>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="name">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Name"
                                        value={name}
                                        onChange={handleNameChange}
                                        required
                                        isInvalid={validated && name.length < 3}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["name"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="birthday">
                                    <Form.Label>Birthday</Form.Label>
                                    <Form.Control
                                        type="date"
                                        placeholder="Birthday"
                                        value={birthday}
                                        onChange={handleBirthdayChange}
                                        required
                                        isInvalid={validated}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["birthdate"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="birthplace">
                                    <Form.Label>Birthplace</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Birthplace"
                                        value={birthplace}
                                        onChange={handleBirthplaceChange}
                                        required
                                        isInvalid={validated}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["birthplace"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="taj">
                                    <Form.Label>TAJ</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="TAJ"
                                        value={taj}
                                        onChange={handleTajChange}
                                        required
                                        isInvalid={validated}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["taj"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="taxId">
                                    <Form.Label>Tax ID</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Tax ID"
                                        value={taxId}
                                        onChange={handleTaxIdChange}
                                        required
                                        isInvalid={validated}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["taxId"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control
                                        type="email"
                                        placeholder="Email"
                                        value={email}
                                        onChange={handleEmailChange}
                                        required
                                        isInvalid={validated}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors["email"]}</Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Form.Label>Addresses</Form.Label>
                            <Addresses addresses={addresses} handleAddressesChange={handleAddressesChange} errors={errors} />
                        </Row>
                        <Row>
                            <Form.Label>Phone Numbers</Form.Label>
                            <PhoneNumbers phoneNumbers={phoneNumbers} handlePhoneNumbersChange={handlePhoneNumbersChange} errors={errors} />
                        </Row>

                        <Button variant="primary" type="submit">
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