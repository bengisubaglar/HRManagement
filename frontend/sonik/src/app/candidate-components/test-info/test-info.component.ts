import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CandidateHpComponent } from '../candidate-hp/candidate-hp.component';
import { CandidateService } from 'src/app/services/candidate.service';
import { CandidateTestService } from 'src/app/services/candidate-test.service';
import { DatePipe } from '@angular/common';
import { TestEntService } from 'src/app/services/test-ent.service';


@Component({
  selector: 'app-test-info',
  templateUrl: './test-info.component.html',
  styleUrls: ['./test-info.component.scss']
})
export class TestInfoComponent {
  constructor(public dialogRef: MatDialogRef<TestInfoComponent>, private candidateHpComponent: CandidateHpComponent, private candidateService: CandidateService,
    private candidateTestService: CandidateTestService,
    @Inject(MAT_DIALOG_DATA) public buttonId: number,
    public datePipe:DatePipe){ }

  duration:any;
  startDate:any;
  endDate: any;
  score:any;
  name:any;

 ngOnInit(): void{  

   if(this.candidateService.CANDIDATE_TEST_DATA.length >= this.buttonId ){
    this.name = this.candidateService.STAGE_DATA[this.buttonId-1].stageName;
    this.duration = this.candidateService.CANDIDATE_TEST_DATA[this.buttonId -1].duration;
    this.score = this.candidateService.CANDIDATE_TEST_DATA[this.buttonId -1].score;
    this.startDate = this.candidateService.CANDIDATE_TEST_DATA[this.buttonId -1].startDate;
    this.endDate = this.candidateService.CANDIDATE_TEST_DATA[this.buttonId -1].endDate;
   }
   
   else{
    this.name = 'NOT ASSIGNED';
    this.duration = 'NOT ASSIGNED';
    this.startDate = 'NOT ASSIGNED';
    this.endDate = 'NOT ASSIGNED';
    this.score = 'NOT ASSIGNED';
   }
 }
}
