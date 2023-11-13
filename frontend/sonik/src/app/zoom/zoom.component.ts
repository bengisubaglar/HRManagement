import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { CandidateService } from '../services/candidate.service';
@Component({
  selector: 'app-zoom',
  templateUrl: './zoom.component.html',
  styleUrls: ['./zoom.component.scss']
})
export class ZoomComponent {
  constructor(private userService:UserService,private candidateService: CandidateService){}

  async ngAfterContentInit() : Promise<any> {
    const {ZoomMtg} = await import('@zoomus/websdk');
    console.log(this.candidateService.CANDIDATE_DATA[0]);
    console.log(this.candidateService.CANDIDATE_DATA[1]);
    let payload = {
      meetingNumber: '***',
      passWord: '***',
      sdkKey: '**',
      sdkSecret: '**',
      userName: this.candidateService.CANDIDATE_DATA[0] + ' ' + this.candidateService.CANDIDATE_DATA[1],
      userEmail: this.userService.showProfile(),
      role: '0',
      leaveUrl: 'http://localhost:4200/candidate/homepage',

    };

    ZoomMtg.setZoomJSLib('https://source.zoom.us/2.13.0/lib', '/av');
    ZoomMtg.preLoadWasm();
    ZoomMtg.prepareWebSDK();


    ZoomMtg.generateSDKSignature({
      meetingNumber:payload.meetingNumber,
      role:payload.role,
      sdkKey:payload.sdkKey,
      sdkSecret:payload.sdkSecret,
      success:function(signature: any) {
        ZoomMtg.init({
          leaveUrl:payload.leaveUrl,
          success: function (data: any) {
            ZoomMtg.join({
              meetingNumber:payload.meetingNumber,
              passWord:payload.passWord,
              sdkKey: payload.sdkKey,
              userName: payload.userName,
              userEmail: payload.userEmail,
              signature: signature.result,
              tk: '',
              success:function (data: any) {
                console.log(data);
              },
              error: function (error: any) {
                console.log('-- Error Join --> ' + error);
              }
            })
          },
          error: function (error: any) {
            console.log('-- Error Init --> ' + error)
          }
        })
      },
      error:function (error: any) {
        console.log(error)
      }
    })

  }


}
