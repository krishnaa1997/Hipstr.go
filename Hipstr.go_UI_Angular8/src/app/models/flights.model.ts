import { Tickets } from './tickets.model';

export class Flights{
    flightNumber: string;
    source: string;
    destination: string;
    flightCompany: string;
    cost: number;
    availableSeats: number;
    journeyDate: string;
    tickets: Tickets[];
}