# Video chat using peer.js
Enable peer 2 peer video chatting using peer.js library.
Peer.js uses centralized server to store active clients and to manage message parsing between connected clients.
Video streaming use peer 2 peer connection while other messages communicate via central server.

# How to setup
- Clone sampel repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "Web RTC/01 Video chat using peerjs" folder

# Start clients
- Open index.html using your favourite browser
- Open index.html from another computer (since we need two clients to communicate)
- Browser will ask permissions to access video and audio
- Copy My id from any of the above opened windows (any computer)
- Paste copied My id into Peer id in other window
- Click call button and notification should appear on other window
- Enjoy