import { Component } from '@angular/core';
import { StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  messages = [];

  constructor(private stompService: StompService) {
    this.initWebSocketConnection();
  }

  private initWebSocketConnection() {
    const topicSubscription = this.stompService.subscribe('/topic/chat');

    topicSubscription.subscribe((message: Message) => {
      this.messages.push(message.body.toString());
    });
  }
}
