import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CandidateService } from 'src/app/services/candidate.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-candidate-meeting-confirmation',
  templateUrl: './candidate-meeting-confirmation.component.html',
  styleUrls: ['./candidate-meeting-confirmation.component.scss']
})
export class CandidateMeetingConfirmationComponent {
  name:any;
  lastName:any;
  email:any;
  HR_DATA: any[] = [];
  hrEmail:any;
  hrName:any;
  hrLastName: any;

  constructor(private userService:UserService, private candidateService: CandidateService, private router:Router){
    this.name = this.candidateService.CANDIDATE_DATA[0];
    this.lastName = this.candidateService.CANDIDATE_DATA[1];
    this.email = this.userService.showProfile();
    this.HR_DATA = this.candidateService.getHrData(this.email);
    this.hrEmail = this.HR_DATA[0];
    this.hrName = this.HR_DATA[1];
    this.hrLastName = this.HR_DATA[2];
   
  }

  
  
  redirectMeeting(){
    this.router.navigate(["/video-call"]);
  }
  
  logout() {
    this.userService.logout();
  }

  redirectProfile() {
    this.userService.redirectProfile();
  }
}
