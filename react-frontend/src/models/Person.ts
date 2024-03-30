import { Address } from "./Address";

export type Person = {
    id: number;
    name: string;
    birthdate: string;
    birthplace: string;
    taj: number;
    taxId: number;
    email: string;
    addresses: Address[];
    phoneNumbers: string[];
}