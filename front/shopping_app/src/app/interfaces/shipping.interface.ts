import { Article } from "./article.interface";
import { Client } from "./client.interface";

export interface Shipping {
    id : string;
    notes : string;
    client : Client;
    articles : Article[];
}