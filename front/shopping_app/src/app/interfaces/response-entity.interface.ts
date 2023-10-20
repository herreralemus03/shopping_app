export interface ResponseEntity<T> {
    code:    string;
    message: string;
    body:    T;
}