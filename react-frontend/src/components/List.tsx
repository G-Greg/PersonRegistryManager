import React from "react";
import { Button, Card, Container, Table } from "react-bootstrap";
import { Person } from "../models/Person";
import { deletePerson, getPersons } from "../api/PersonAPI";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPen } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router";

export default function List() {

    const navigate = useNavigate();

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

    return (
        <Container className="p-3">
            <h1>List</h1>
            <Card style={{ backgroundColor: "rgb(255,255,255,0.8)" }}>
                <Card.Body>
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
                                <th>Address(es)</th>
                                <th>Phone Number(s)</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                persons?.map((person, index) =>
                                    <tr key={index}>
                                        <td>{person.id}</td>
                                        <td>{person.name}</td>
                                        <td>{person.birthdate}</td>
                                        <td>{person.birthplace}</td>
                                        <td>{person.taj}</td>
                                        <td>{person.taxId}</td>
                                        <td>{person.email}</td>
                                        <td>
                                            {
                                                person.addresses.map((address, index) => <div key={index}>{address.zipCode} {address.city}, {address.street} {address.houseNumber}</div>)
                                            }
                                        </td>
                                        <td>
                                            {
                                                person.phoneNumbers.map((phoneNumber, index) => <div key={index}>{phoneNumber}</div>)
                                            }
                                        </td>
                                        <td>
                                            <Button variant="primary" onClick={() => navigate(`/persons/${person.id}`)}><FontAwesomeIcon icon={faPen} /></Button>{' '}
                                            <Button variant="danger" onClick={() => deletePersonById(person.id)}><FontAwesomeIcon icon={faTrash} /></Button>
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