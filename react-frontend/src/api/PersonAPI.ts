import axios from 'axios';
import { Person } from '../models/Person';

const basePath = 'api/persons';

export async function getPersons(): Promise<Person[]> {
    const response = await axios.get<Person[]>(basePath)
        .catch(error => {
            throw error;
        });
    return response.data;
}

export async function getPerson(id: number): Promise<Person> {
    const response = await axios.get<Person>(`${basePath}/${id}`)
        .catch(error => {
            throw error;
        });
    return response.data;
}

export async function createPerson(person: Person): Promise<Person> {
    const response = await axios.post<Person>(basePath, person)
        .catch(error => {
            throw error;
        });
    return response.data;
}

export async function updatePerson(person: Person): Promise<Person> {
    const response = await axios.put<Person>(`${basePath}/${person.id}`, person)
        .catch(error => {
            throw error;
        });
    return response.data;
}

export async function deletePerson(id: number): Promise<void> {
    await axios.delete(`${basePath}/${id}`)
        .catch(error => {
            throw error;
        });
}

export async function GRDPRPersonDelete(id: number): Promise<void> {
    await axios.delete(`${basePath}/gdpr/${id}`)
        .catch(error => {
            throw error;
        });
}