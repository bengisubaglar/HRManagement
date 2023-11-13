import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

// TO DO: ADD TEST RESULT COLUMN TO CANDIDATE TABLE (BOOLEAN)
export class TestService {

  url = "http://localhost:8080/api/"
  constructor(private httpClient: HttpClient) { }

  save(data:any){
      return this.httpClient.post(this.url + 
        "user/test", data, {
          headers: new HttpHeaders()
        })
  }
}
