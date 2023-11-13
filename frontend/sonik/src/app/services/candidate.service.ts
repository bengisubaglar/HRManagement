import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { Observable, tap } from 'rxjs';


export interface Candidate {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  gender: string;
  birthdate: string;
  tckn: string;
  address: string;
  userRole: string;
  isActive: string;
  creationDate: string;
  lastLogin: string;
  candidateStages: CandidateStage[] | null;
  about: string;
}

export interface CandidateStage {
  id: number | null;
  state: string | null;
  candidateTest: CandidateTest[] | null;
  stage: Stage[] | null;

}
export interface Stage {
  id: number | null;
  stageName: string | null;
}

export interface CandidateTest {
  id: number | null;
  duration: number | null;
  startDate: string | null;
  endDate: string | null;
  score: number | null;
}


@Injectable({
  providedIn: 'root'
})
export class CandidateService {
  CANDIDATE_DATA: any[] = [];
  STAGE_DATA: Stage[] = [];
  CANDIDATE_STAGE_DATA: CandidateStage[] = [];
  CANDIDATE_TEST_DATA: CandidateTest[] = [];
  HR_DATA: any[] = [];
  url = "http://localhost:8080/api/candidate/";
  constructor(private httpClient: HttpClient, private userService: UserService) { }

  getCandidateData(email: any): Observable<any> {
    return this.httpClient.get(this.url + "findByEmail?email=" + email).pipe(
      tap((data: any) => {
        this.CANDIDATE_DATA = this.CANDIDATE_DATA.concat(data.firstName,data.lastName);
        this.CANDIDATE_STAGE_DATA = data.candidateStages || [];
        this.CANDIDATE_TEST_DATA = this.CANDIDATE_STAGE_DATA.map((stage: any) => stage.candidateTest);
        this.STAGE_DATA = this.CANDIDATE_STAGE_DATA.map((stage: any) => stage.stage);       
      })
    );
  }

  getHrData(email:any){
   this.httpClient.get(this.url + "getAssociatedHr?email=" +email).subscribe((result:any) => {
      this.HR_DATA[0] = result.email,      
      this.HR_DATA[1]= result.firstName,
      this.HR_DATA[2]= result.lastName
    });
    return this.HR_DATA;
  }



}
