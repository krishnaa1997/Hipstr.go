import { Tickets } from './tickets.model';

export class User{
    userId: string;
    firstName: string;
    lastName: string;
    password: string;
    mobileNumber: number;
    gender: string;
    bookedTickets: Tickets[];
}