import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Clipboard } from '@angular/cdk/clipboard';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { UserService } from 'src/app/services/user.service';
import { FormGroup } from '@angular/forms';
import { AddCandidateComponent } from '../add-candidate/add-candidate.component';

import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { UpdateInterviewComponentComponent } from '../update-interview-component/update-interview-component.component';

export interface Candidate {
  firstName: string;
  lastName: string;
  tckn: string;
  address: string;
  birthdate: string;
  gender: string;
  candidateStages: any[];
}

@Component({
  selector: 'app-hr-hp',
  templateUrl: './hr-hp.component.html',
  styleUrls: ['./hr-hp.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class HrHpComponent implements OnInit, AfterViewInit {

  url = 'http://localhost:8080/api/hr/';
  email: any;
  ELEMENT_DATA: Candidate[] = [];
  dataSource: MatTableDataSource<Candidate>;
  displayedColumns: string[] = ['NAME', 'LASTNAME', 'SUCCESSFULL'];
  value:any;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  columnsToDisplayWithExpand: string[] = [];
  expandedElement!: Candidate | null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserService, private httpClient: HttpClient, public dialogRef: MatDialogRef<AddCandidateComponent>,public dialog: MatDialog,
    private matSnackBar: MatSnackBar,
    private clipboard:Clipboard) {
    this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
  }

  ngOnInit(): void {
    this.email = this.userService.showProfile();
    this.columnsToDisplayWithExpand = [...this.displayedColumns, 'expand'];
  }

  ngAfterViewInit(): void {
    this.httpClient.get(this.url + 'candidates?email=' + this.email).subscribe((data: any) => {
      this.ELEMENT_DATA = data.map((item: any) => ({
        NAME: item.firstName,
        LASTNAME: item.lastName,
        email: item.email,
        birthdate: item.birthdate,
        address: item.address,
        tckn: item.tckn,
        gender: item.gender,
        candidateStages: item.candidateStages,
        SUCCESSFULL: item.candidateStages.length >= 3 ? 'YES' : 'NO',
        about: item.about
      }));
      
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.dataSource.data = this.ELEMENT_DATA;
      this.columnsToDisplayWithExpand = [...this.displayedColumns, 'expand'];
    });
  }

  logout(): void {
    this.userService.logout();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  addCandidate(){
    this.dialog.open(AddCandidateComponent);
  }

  handleSubmit(data:any){
    this.dialogRef.close();
    this.matSnackBar.open("Sent invite email successfully!", "OKAY", { horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition})
    return this.httpClient.post("http://localhost:8080/api/mail/sendRegistrationEmail", data,{
      headers: new HttpHeaders()
    });
   
  }
  updateInterview(){
    this.dialog.open(UpdateInterviewComponentComponent);
  }
  handleUpdate(data:any){
    this.matSnackBar.open("Candidate stage state updates successfully!", "OKAY", { horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition})
    this.dialogRef.close();
    return this.httpClient.post("http://localhost:8080/api/hr/update-interview", data,{
      headers: new HttpHeaders()
    });
  }
  
  copyText(textToCopy: string) {
    this.clipboard.copy(textToCopy);
}
}
