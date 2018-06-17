import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';

import { AppComponent } from './app.component';

import * as SockJS from 'sockjs-client';

export function socketProvider() {
  return new SockJS('http://127.0.0.1:8080/socket');
}

const stompConfig = {
  url: socketProvider,

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
