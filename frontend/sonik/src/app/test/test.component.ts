import { HttpClient } from '@angular/common/http';
import { Component, HostListener, OnInit } from '@angular/core';

import { Subscription, interval } from 'rxjs';
import { TestService } from '../services/test.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import { CandidateService } from '../services/candidate.service';
import { Router} from '@angular/router';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  showWarning: boolean = false;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  isQuizStarted: boolean = false;
  isQuizEnded: boolean = false;
  questionsList: any[] = [];
  currentQuestionNo: number = 0;
  tabCount: number = 0;
  remainingTime: number = 0;

  timer = interval(1000);
  subscription: Subscription[] = [];
  correctAnswerCount: number = 0;
  success = false;
  url = "http://localhost:8080/api/candidate/";
  score: number = 0;

  constructor(private http: HttpClient,
              private testService: TestService,
              private _snackBar: MatSnackBar,
              private userService: UserService,
              private candidateService: CandidateService,
              private router: Router) {


  }

  ngOnInit(): void {
    this.loadQuestions();

  }
  logout() {
    this.userService.logout();
  }

  redirectProfile() {
    this.userService.redirectProfile();
  }
  loadQuestions() {
    var candId = this.userService.getCandidateId();

    var name;

    var data = {
      candidateId: candId,
      stageId: this.candidateService.STAGE_DATA.length
    }

    this.http.post(this.url + "testConfirmation", data).subscribe((result: any) => {

      this.questionsList = result.test.questions;
      this.remainingTime = (result.duration * 60) / this.questionsList.length;
    });

  }


  @HostListener('document:visibilitychange', ['$event'])
  appVisibility() {
    if (document.hidden && this.isQuizStarted == true) {
      this.tabCount++;
      if (this.tabCount < 2) {
        this._snackBar.open('Tab switching during the test is not allowed. Please do not open any tabs other than the test tab. If you switch this tab one more time, the test will end.', 'I understand', {
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition,
        });

      }
      else {
        this.finish();
        this.success = false;
      }

    }

  }

  nextQuestion() {
    this.remainingTime = 180;
    if (this.currentQuestionNo < this.questionsList.length - 1) {
      this.currentQuestionNo++;
    } else {
      this.subscription.forEach(element => {
        element.unsubscribe();
      });
    }
  }
  finish() {
    this.isQuizEnded = true;
    this.isQuizStarted = false;
  }

  start() {
    this.showWarning = false;
    this.isQuizEnded = false;
    this.isQuizStarted = false;
  }
  showWarningPopUp() {
    this.showWarning = true;
  }
  selectOption(option: any) {
    if (option.correct) {
      this.correctAnswerCount++;
    }
    option.isSelected = true;
  }
  isOptionSelected(options: any) {
    const selectionCount = options.filter((m: any) => m.isSelected == true).length;
    if (selectionCount == 0) {
      return false;
    } else {
      return true;
    }
  }
  startQuiz() {
    this.showWarning = false;
    this.isQuizStarted = true;
    this.subscription.push(this.timer.subscribe(res => {

      if (this.remainingTime != 0) {
        this.remainingTime--;
      }
      if (this.remainingTime == 0) {
        this.nextQuestion();
        this.remainingTime = 180;
      }

    })
    )
  }

  checkSuccess() {
    this.score = this.correctAnswerCount * 10;
    if(this.score>=60){
      this.success=true;
    }
  }

  save() {
    this.checkSuccess();
    var data = {
      id: this.userService.getCandidateId(),
      score: this.score,
      stageId: this.candidateService.STAGE_DATA.length,
      passed: this.score ? (this.score >= 60) : null
    }
    this.http.post(this.url + "updateCandidateStage", data).subscribe((result: any) => {
    })
    this.router.navigate(["/candidate/homepage"])
  }

}

