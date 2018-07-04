import sys
import time
from chirp import ChirpConnect, CallbackSet, CHIRP_CONNECT_STATE
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_v1_5
from Crypto.Hash import SHA
from Crypto import Random

app_key = "8aFfE9c960cBDB01FFE373053"
app_secret = "f2a0E9f5582Fc46DB9E6d713e22BF4b391f87BdfadbFe28a57"
global_passcode = "1234"

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
            message = bytearray.fromhex(str(payload)).decode('latin-1')
            print('Received: ' + message)
            
            if authenticate(message):
                sdk.send(sdk.new_payload(str.encode("Auth success")))
            else:
                sdk.send(sdk.new_payload(str.encode("Auth failed")))

    def on_sending(self, payload):
        """ Called when a chirp has started to be transmitted """
        print('Sending data')

    def on_sent(self, payload):
        """ Called when the entire chirp has been sent """
        print('Sent: ' + bytearray.fromhex(str(payload)).decode())

def main():
    global sdk
    sdk.set_callbacks(MyCallbacks())
    sdk.start(send=True, receive=True)

    try:
        # Process audio streams
        while True:
            time.sleep(0.5)
            sys.stdout.flush()
    except KeyboardInterrupt:
        print('Exiting')

    sdk.stop()
    sdk.close()

# def decrypt(encrypted):
#     key = RSA.importKey(open('private_key.pem').read())
#     dsize = SHA.digest_size
#     sentinel = Random.new().read(15+dsize)
#     cipher = PKCS1_v1_5.new(key)
#     return cipher.decrypt(encrypted, sentinel)

def authenticate(passcode):
    if passcode == global_passcode:
        return True
    else:
        return False

if __name__ == "__main__":
    main()