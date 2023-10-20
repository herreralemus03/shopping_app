import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Page } from 'src/app/interfaces/page.interface';
import { Client } from 'src/app/interfaces/client.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private httpService : BaseHttpService) { }

  public loadClients(page : number, size : number) : Observable<ResponseEntity<Page<Client>>> {
    return this.httpService.get('/clients/get/paged', {page : page, size : size})
  }

  public createClient(client : Client) : Observable<ResponseEntity<Client>> {
    return this.httpService.post('/clients/add', client);
  }

  public updateClient(client: Client) : Observable<ResponseEntity<Client>> {
    return this.httpService.post('/clients/update', client);
  }

  public deleteClient(id : string) : Observable<ResponseEntity<Client>> {
    return this.httpService.get(`/clients/delete/${id}`);
  }

}
