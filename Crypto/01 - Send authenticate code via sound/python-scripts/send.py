import sys
import time
from chirp import ChirpConnect, CallbackSet, CHIRP_CONNECT_STATE

app_key = "8aFfE9c960cBDB01FFE373053"
app_secret = "f2a0E9f5582Fc46DB9E6d713e22BF4b391f87BdfadbFe28a57"

class MyCallbacks(CallbackSet):

    def on_state_changed(self, previous_state, current_state):
        """ Called when the SDK's state has changed """
        print("State changed from {} to {}".format(
            CHIRP_CONNECT_STATE.get(previous_state),
            CHIRP_CONNECT_STATE.get(current_state)))

    def on_sending(self, payload):
        """ Called when a chirp has started to be transmitted """
        print('Sending data')

    def on_sent(self, payload):
        """ Called when the entire chirp has been sent """
        print('Sent: ' + bytearray.fromhex(str(payload)).decode())

def main():
    sdk = ChirpConnect(app_key, app_secret)
    sdk.set_callbacks(MyCallbacks())
    sdk.start(send=True, receive=False)
    
    message = sys.argv[1]
    payload = sdk.new_payload(str.encode(message))
    sdk.send(payload)
    time.sleep(10) # give some time to send data.
    sdk.stop()
    sdk.close()
    
if __name__ == "__main__":
    main()