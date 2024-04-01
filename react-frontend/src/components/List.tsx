import React from "react";
import { Button, Card, Container, Form, Table } from "react-bootstrap";
import { Person } from "../models/Person";
import { GRDPRPersonDelete, deletePerson, getPersons, updatePerson } from "../api/PersonAPI";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faAdd, faSave } from "@fortawesome/free-solid-svg-icons"; import { Address } from "../models/Address";

export default function List() {
    const [persons, setPersons] = React.useState<Person[]>();
    React.useEffect(() => {
        getPersons()
            .then((res: any) => {
                setPersons(res);
            }).catch(error => {
                console.log(error);
            });
    }, []);


    const deletePersonById = (id: number) => {
        deletePerson(id)
            .then(() => {
                getPersons()
                    .then((res: any) => {
                        alert("Person deleted");
                        setPersons(res);
                    }).catch(error => {
                        console.log(error);
                    });
            })
            .catch(error => {
                console.log(error);
            });
    }

    const deleteGDRP = (id: number) => {
        GRDPRPersonDelete(id)
            .then(() => {
                getPersons()
                    .then((res: any) => {
                        alert("GDPR data deleted");
                        setPersons(res);
                    }).catch(error => {
                        console.log(error);
                    });
            })
            .catch(error => {
                console.log(error);
            });
    }

    const handleNameChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].name = event.target.value;
        setPersons(newPersons);
    };

    const handleBirthdayChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].birthdate = event.target.value;
        setPersons(newPersons);
    };

    const handleBirthplaceChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].birthplace = event.target.value;
        setPersons(newPersons);
    };

    const handleTajChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].taj = event.target.value;
        setPersons(newPersons);
    };

    const handleTaxIdChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].taxId = event.target.value;
        setPersons(newPersons);
    };

    const handleEmailChange = (event, index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].email = event.target.value;
        setPersons(newPersons);
    };


    const handleAddAddress = (index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses.push({} as Address);
        setPersons(newPersons);
    };

    const handleRemoveAddress = (index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses.splice(j, 1);
        setPersons(newPersons);
    };

    const handleZipCodeChange = (event, index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses[j].zipCode = event.target.value;
        setPersons(newPersons);
    };

    const handleCityChange = (event, index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses[j].city = event.target.value;
        setPersons(newPersons);
    };

    const handleStreetChange = (event, index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses[j].street = event.target.value;
        console.log(persons);
        setPersons(newPersons);
    };

    const handleHouseNumberChange = (event, index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].addresses[j].houseNumber = event.target.value;
        setPersons(newPersons);
    };

    const handleAddPhoneNumber = (index) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].phoneNumbers.push("");
        setPersons(newPersons);
    };

    const handleRemovePhoneNumber = (index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].phoneNumbers.splice(j, 1);
        setPersons(newPersons);
    };

    const handlePhoneNumberChange = (event, index, j) => {
        const newPersons = [...persons as Person[]];
        newPersons[index].phoneNumbers[j] = event.target.value;
        setPersons(newPersons);
    };

    const handleEditPerson = (index: number) => {
        const person = persons![index];
        updatePerson(person)
            .then(() => {
                alert("Person updated");
                getPersons()
                    .then((res: any) => {
                        setPersons(res);
                    }).catch(error => {
                        console.log(error);
                    });
            })
            .catch(error => {
                console.log(error);
            });
    }

    return (
        <Container className="p-2" fluid>
            <Card style={{ backgroundColor: "rgb(255,255,255,0.8)" }}>
                <Card.Body >
                    <h1>List</h1>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Birthday</th>
                                <th>Birthplace</th>
                                <th>TAJ</th>
                                <th>TAX Id</th>
                                <th>Email</th>
                                <th >Address(es)
                                    <tr>
                                        <th>ZIP</th>
                                        <th>City</th>
                                        <th>Street</th>
                                        <th>House Number</th>
                                    </tr>
                                </th>
                                <th>Phone Number(s)</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                persons?.map((person, index) =>
                                    <tr key={index}>
                                        <td>{person.id}</td>
                                        <td><Form.Control type="text" placeholder="Name" value={person.name} onChange={(value) => handleNameChange(value, index)} /></td>
                                        <td><Form.Control type="date" placeholder="Birthday" value={person.birthdate} onChange={(value) => handleBirthdayChange(value, index)} /></td>
                                        <td><Form.Control type="text" placeholder="Birthplace" value={person.birthplace} onChange={(value) => handleBirthplaceChange(value, index)} /></td>
                                        <td><Form.Control type="number" placeholder="TAJ" value={person.taj} onChange={(value) => handleTajChange(value, index)} /></td>
                                        <td><Form.Control type="number" placeholder="Tax Id" value={person.taxId} onChange={(value) => handleTaxIdChange(value, index)} /></td>
                                        <td><Form.Control type="text" placeholder="Email" value={person.email} onChange={(value) => handleEmailChange(value, index)} /></td>
                                        <td>
                                            <table>
                                                {
                                                    person.addresses.map((address, j) => (
                                                        <tr>
                                                            <td><Form.Control type="number" placeholder="ZIP" value={address.zipCode} onChange={(value) => handleZipCodeChange(value, index, j)} /></td>
                                                            <td><Form.Control type="text" placeholder="City" value={address.city} onChange={(value) => handleCityChange(value, index, j)} /></td>
                                                            <td><Form.Control type="text" placeholder="Street" value={address.street} onChange={(value) => handleStreetChange(value, index, j)} /></td>
                                                            <td><Form.Control type="number" placeholder="House Number" value={address.houseNumber} onChange={(value) => handleHouseNumberChange(value, index, j)} /></td>
                                                            <Button variant="outline-danger" onClick={() => handleRemoveAddress(index, j)}><FontAwesomeIcon icon={faTrash} /></Button>
                                                        </tr>
                                                    ))
                                                }
                                                <Button variant="outline-primary" size="sm" onClick={() => handleAddAddress(index)}><FontAwesomeIcon icon={faAdd} /></Button>
                                            </table>
                                        </td>
                                        <td>
                                            {
                                                person.phoneNumbers.map((phoneNumber, j) =>
                                                    <>
                                                        <Form.Control type="text" placeholder="Phone Number" value={phoneNumber} onChange={(value) => handlePhoneNumberChange(value, index, j)} />
                                                        <Button size="sm" variant="outline-danger" onClick={() => handleRemovePhoneNumber(index, j)}><FontAwesomeIcon icon={faTrash} /></Button>
                                                    </>
                                                )
                                            }{' '}
                                            <Button variant="outline-primary" size="sm" onClick={() => handleAddPhoneNumber(index)}><FontAwesomeIcon icon={faAdd} /></Button>
                                        </td>
                                        <td>
                                            <Button variant="primary" size="sm" onClick={() => handleEditPerson(index)}><FontAwesomeIcon icon={faSave} /></Button>{' '}
                                            <Button variant="danger" size="sm" onClick={() => deletePersonById(person.id)}><FontAwesomeIcon icon={faTrash} /></Button>
                                            <Button variant="outline-danger" size="sm" onClick={() => deleteGDRP(person.id)}>GDRP <FontAwesomeIcon icon={faTrash} /></Button>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </Container>
    );
}