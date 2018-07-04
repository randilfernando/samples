import sys
import time
from chirp import ChirpConnect, CallbackSet, CHIRP_CONNECT_STATE

app_key = "c506a55Fae19Ec5cE05c9AB4B"
app_secret = "c4381ef75eB9ac1Efb22AA5EcDd609a2E972e41CefDe6faBE3"

sdk = ChirpConnect(app_key, app_secret)

class MyCallbacks(CallbackSet):

    def on_state_changed(self, previous_state, current_state):
        """ Called when the SDK's state has changed """
        print("State changed from {} to {}".format(
            CHIRP_CONNECT_STATE.get(previous_state),
            CHIRP_CONNECT_STATE.get(current_state)))

    def on_receiving(self):
        """ Called when a chirp frontdoor is detected """
        print('Receiving data')

    def on_received(self, payload):
        """
        Called when an entire chirp has been received.
        Note: A length of 0 indicates a failed decode.
        """
        if len(payload) == 0:
            print('Decode failed!')
        else:
            print('Received: ' + bytearray.fromhex(str(payload)).decode())

def main():
    global sdk
    sdk.set_callbacks(MyCallbacks())
    sdk.start(send=False, receive=True)

    try:
        # Process audio streams
        while True:
            time.sleep(0.5)
            sys.stdout.flush()
    except KeyboardInterrupt:
        print('Exiting')

    sdk.stop()
    sdk.close()
    
if __name__ == "__main__":
    main()