import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { TestComponent } from './test/test.component';
import { RegisterComponent } from './register/register.component';

import { AuthGuard } from './services/auth.guard';
import { CandidateHpComponent } from './candidate-components/candidate-hp/candidate-hp.component';
import { HrHpComponent } from './hr-components/hr-hp/hr-hp.component';
import { CandidateProfileComponent } from './candidate-components/candidate-profile/candidate-profile.component';
import {ZoomComponent} from "./zoom/zoom.component";
import { CandidateMeetingConfirmationComponent } from './candidate-components/candidate-meeting-confirmation/candidate-meeting-confirmation.component';
import { CandidateTestConfirmationComponent } from './candidate-components/candidate-test-confirmation/candidate-test-confirmation.component';





const routes: Routes = [
  {
    path: 'auth/candidate/login',
    component: LoginComponent
  },
  {
    path: 'auth/hr/login',
    component: LoginComponent,
  },
  {
    path: 'auth/candidate/register',
    component: RegisterComponent
  },
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'candidate/homepage',
    component: CandidateHpComponent,
    canActivate: [AuthGuard]

  },
  {
    path: 'candidate/findByEmail',
    component: CandidateProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'candidate/update',
    component: CandidateProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'hr/homepage',
    component: HrHpComponent,
    canActivate: [AuthGuard]

  },
  {
    path: 'candidate/pre-meeting',
    component: CandidateMeetingConfirmationComponent,
    canActivate: [AuthGuard]

  },
  {
    path: 'candidate/pre-test',
    component: CandidateTestConfirmationComponent,
    canActivate: [AuthGuard]

  },
  {
    path: 'user/test',
    component: TestComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'video-call',
    component: ZoomComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    RouterModule.forRoot([
      {
        path: '',
        component: WelcomeComponent

      }
    ])],

  exports: [RouterModule]
})
export class AppRoutingModule { }
