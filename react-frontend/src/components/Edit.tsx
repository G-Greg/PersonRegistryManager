import React from 'react';
import { Form, Col, Row, Card, Button, Container } from 'react-bootstrap';
import { Address } from '../models/Address';
import Addresses from './Addresses';
import { useParams } from 'react-router';
import { getPerson } from '../api/PersonAPI';


export default function Edit() {

    const { id } = useParams();
    console.log(id);
    if (id !== undefined) {
        React.useEffect(() => {
            getPerson(parseInt(id))
                .then((res: any) => {
                    console.log(res);
                    setName(res.name);
                    setBirthday(res.birthdate);
                    setBirthplace(res.birthplace);
                    setTaj(res.taj);
                    setTaxId(res.taxId);
                    setEmail(res.email);
                    setAddresses(res.addresses);
                    setPhoneNumbers(res.phoneNumbers);
                })
                .catch(error => {
                    console.error(error);
                });
        }, []);
    }


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
    const [addresses, setAddresses] = React.useState<Address[]>([]);
    const handleAddressesChange = (event) => {
        setAddresses(event.target.value);
    };


    //PhoneNumbers
    const [phoneNumbers, setPhoneNumbers] = React.useState([]);
    const handlePhoneNumbersChange = (event) => {
        setPhoneNumbers(event.target.value);
    };


    return (
        <Container className="p-3">
            <Form>

                <h1>Edit</h1>
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
                            <Col>
                                <Form.Group className="mb-3" controlId="phoneNumbers">
                                    <Form.Label>Phone Numbers</Form.Label>
                                    <Form.Control type="text" placeholder="Phone Numbers" value={phoneNumbers} onChange={handlePhoneNumbersChange} />
                                </Form.Group>
                            </Col>
                            <Form.Group className="mb-3" controlId="houseNumber">
                                <Button variant="primary" type="submit">
                                    Add more
                                </Button>
                            </Form.Group>
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