import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CandidateService } from 'src/app/services/candidate.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-candidate-test-confirmation',
  templateUrl: './candidate-test-confirmation.component.html',
  styleUrls: ['./candidate-test-confirmation.component.scss']
})
export class CandidateTestConfirmationComponent {
  name:any;
  lastName:any;
  email:any;

  constructor(private userService:UserService, private candidateService: CandidateService, private router:Router){
    this.name = this.candidateService.CANDIDATE_DATA[0];
    this.lastName = this.candidateService.CANDIDATE_DATA[1];
    this.email = this.userService.showProfile();
  }

  
  
  redirectMeeting(){
    this.router.navigate(["/user/test"]);
  }
  
  logout() {
    this.userService.logout();
  }

  redirectProfile() {
    this.userService.redirectProfile();
  }
}
