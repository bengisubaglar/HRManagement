import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BodyComponent } from './body/body.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { WelcomeComponent } from './welcome/welcome.component';
import { TestComponent } from './test/test.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RegisterComponent } from './register/register.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './services/auth.guard';
import { LogoutComponent } from './logout/logout.component';
import { CandidateHpComponent } from './candidate-components/candidate-hp/candidate-hp.component';
import { HrHpComponent } from './hr-components/hr-hp/hr-hp.component';
import { CandidateProfileComponent } from './candidate-components/candidate-profile/candidate-profile.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatSelectModule} from '@angular/material/select';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { HrSidenavComponent } from './hr-components/hr-sidenav/hr-sidenav.component';
import { CandidateSidenavComponent } from './candidate-components/candidate-sidenav/candidate-sidenav.component';
import { AddCandidateComponent } from './hr-components/add-candidate/add-candidate.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TestInfoComponent } from './candidate-components/test-info/test-info.component';
import {MatListModule} from '@angular/material/list';
import { ZoomComponent } from './zoom/zoom.component';
import { CandidateMeetingConfirmationComponent } from './candidate-components/candidate-meeting-confirmation/candidate-meeting-confirmation.component';
import { UpdateInterviewComponentComponent } from './hr-components/update-interview-component/update-interview-component.component';
import { CandidateTestConfirmationComponent } from './candidate-components/candidate-test-confirmation/candidate-test-confirmation.component';
import {ClipboardModule} from '@angular/cdk/clipboard';







@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    HrSidenavComponent,
    CandidateSidenavComponent,
    LoginComponent,
    WelcomeComponent,
    TestComponent,
    RegisterComponent,
    LogoutComponent,
    RegisterComponent,
    CandidateHpComponent,
    HrHpComponent,
    CandidateProfileComponent,
    AddCandidateComponent,
    TestInfoComponent,
    ZoomComponent,
    CandidateMeetingConfirmationComponent,
    UpdateInterviewComponentComponent,
    CandidateTestConfirmationComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    FormsModule,
    MatFormFieldModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDividerModule,
    MatSelectModule,
    MatExpansionModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatTooltipModule,
    MatListModule,
    ClipboardModule
  ],

  providers: [
    AuthService,
    AuthGuard,
    HrHpComponent,
    CandidateHpComponent,
    { provide: MatDialogRef, useValue: null }, 
    { provide: MAT_DIALOG_DATA, useValue: {} } ,
  DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
