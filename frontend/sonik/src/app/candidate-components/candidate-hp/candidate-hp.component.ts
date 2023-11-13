import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { TestInfoComponent } from '../test-info/test-info.component';
import { Candidate, CandidateService, CandidateStage, CandidateTest, Stage } from 'src/app/services/candidate.service';
import { CandidateTestService } from 'src/app/services/candidate-test.service';
import { TestEntService } from 'src/app/services/test-ent.service';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-candidate-hp',
  templateUrl: './candidate-hp.component.html',
  styleUrls: ['./candidate-hp.component.scss']
})


export class CandidateHpComponent implements OnInit, AfterViewInit {


  CANDIDATE_DATA: Candidate[] = [];
  STAGE_DATA: Stage[] = [];
  CANDIDATE_STAGE_DATA: CandidateStage[] = [];
  CANDIDATE_TEST_DATA: CandidateTest[] = [];
  dataSource: MatTableDataSource<CandidateTest>;

  url = "http://localhost:8080/api/candidate/";
  steps = ['TECHNICAL EVALUA', 'ENGLISH TEST', 'VIDEO INTERVIEW', 'FINAL'];

  displayedColumns: string[] = ['testName','startDate', 'endDate', 'duration', 'score', 'action'];

  @ViewChild('progressBar', { static: false }) progressBar!: ElementRef<HTMLSpanElement>;


  constructor(private userService: UserService, private httpClient: HttpClient, public dialog: MatDialog,
    private candidateService: CandidateService,
    private router: Router,
    private datePipe: DatePipe) {
    this.dataSource = new MatTableDataSource(this.CANDIDATE_TEST_DATA);

  }

  name: any;
  email: any;
  public buttonId: any;

  logout() {
    this.userService.logout();
  }

  redirectProfile() {
    this.userService.redirectProfile();
  }

  ngOnInit(): void {
    this.email = this.userService.showProfile();
    this.candidateService.getCandidateData(this.email).subscribe((data: any) => {
      this.STAGE_DATA = this.candidateService.STAGE_DATA;
      this.CANDIDATE_STAGE_DATA = this.candidateService.CANDIDATE_STAGE_DATA;
      this.CANDIDATE_TEST_DATA = this.candidateService.CANDIDATE_TEST_DATA.map((candidateTest) => {
        if (candidateTest) { 
          candidateTest.startDate = this.datePipe.transform(candidateTest.startDate, 'dd-MM-yy h:mm:a');
          candidateTest.endDate = this.datePipe.transform(candidateTest.endDate, 'dd-MM-yy h:mm:a');
         
        }
        return candidateTest;
        
        
      });
     
      this.dataSource.filteredData = this.CANDIDATE_TEST_DATA;
      console.log(this.dataSource.filteredData);
    });
  }

  ngAfterViewInit() {

  }


  get firstElementState(): string | null {
    if (this.CANDIDATE_STAGE_DATA.length > 0) {
      return this.CANDIDATE_STAGE_DATA[0].state;
    }
    return 'NOT_STARTED';
  }
  get secondElementState(): string | null {
    if (this.CANDIDATE_STAGE_DATA.length > 1) {
      return this.CANDIDATE_STAGE_DATA[1].state;
    }
    return 'NOT_STARTED';
  }
  get thirdElementState(): string | null {
    if (this.CANDIDATE_STAGE_DATA.length > 2) {
      return this.CANDIDATE_STAGE_DATA[2].state;
    }

    return 'NOT_STARTED';
  }
  get fourthElementState(): string | null {
    if (this.CANDIDATE_STAGE_DATA.length > 3) {
      return this.CANDIDATE_STAGE_DATA[3].state;
    }
    return 'NOT_STARTED';

  }


  openInfo(id: any) {
    if (this.CANDIDATE_TEST_DATA[id - 1] !== null) {
      this.buttonId = id;
      this.dialog.open(TestInfoComponent, { data: this.buttonId });
    }
  }

  isEndDatePassed(element: any) {
    
      const endDate = new Date(element.endDate);
      const now = new Date();
      if (endDate > now) {
        return true;
      }
      else{
        return false;
      }
    
    
  }

  doAction(){
    if(this.CANDIDATE_STAGE_DATA.length <= 2){
      this.router.navigate(["candidate/pre-test"]);
      
    }
    else{
      this.router.navigate(["/candidate/pre-meeting"]);
    }
  }

  takeTest() {
    this.router.navigate(["/user/test"]);
  }

  isTest(){   
  
    if(this.CANDIDATE_STAGE_DATA.length <= 2){
      return true;
    }
    return false;
  }
  
  joinInterview(){
    this.router.navigate(['/video-call']);
  }


}