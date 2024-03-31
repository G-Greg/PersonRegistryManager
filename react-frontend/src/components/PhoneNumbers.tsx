import React from 'react'
import { Button, Col, Form, Row, } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';


export default function PhoneNumbers({ phoneNumbers, handlePhoneNumbersChange }: { phoneNumbers: string[], handlePhoneNumbersChange: any }) {
    const addPhone = () => {
        handlePhoneNumbersChange([...phoneNumbers, ""]);
    }

    const editPhoneNumber = (index: number, event) => {
        console.log(event.target.value);
        const newAddresses = [...phoneNumbers];
        newAddresses[index] = event.target.value;
        handlePhoneNumbersChange(newAddresses);
    }

    const deletePhone = (index) => {
        const newAddresses = [...phoneNumbers];
        newAddresses.splice(index, 1);
        handlePhoneNumbersChange(newAddresses);
    }


    return (
        <>
            {
                phoneNumbers.map((phoneNumber, index) => (
                    <Row>
                        <Col>
                            <Form.Group className="mb-3" controlId="TelephoneNumber">
                                <Form.Control type="number" placeholder="+36 30 ..." value={phoneNumber} onChange={(event) => editPhoneNumber(index, event)} />
                            </Form.Group>
                        </Col>

                        <Col>
                            <Form.Group className="mb-3" controlId="">
                                <Button variant="danger" onClick={() => deletePhone(index)} disabled={phoneNumbers.length === 1}><FontAwesomeIcon icon={faTrash} /></Button>
                            </Form.Group>
                        </Col>
                    </Row>
                ))
            }
            <Form.Group className="mb-3" controlId="phoneAdd">
                <Button variant="outline-primary" onClick={addPhone}>
                    Add more
                </Button>
            </Form.Group>
        </>
    );
}