package io.chirp.connectdemoapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectLicenceListener;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.models.ConnectState;


public class MainActivity extends AppCompatActivity {

    private ChirpConnect chirpConnect;

    private static final int RESULT_REQUEST_RECORD_AUDIO = 1;

    TextView txtPassword;
    TextView txtFromServer;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View parentLayout = findViewById(android.R.id.content);

        txtPassword = findViewById(R.id.txtPassword);
        txtFromServer = findViewById(R.id.txtFromServer);
        btnSend = findViewById(R.id.btnSend);

        txtPassword.setEnabled(false);
        txtFromServer.setEnabled(false);
        btnSend.setEnabled(false);

        Log.v("Connect Version: ", ChirpConnect.getVersion());

        String APP_KEY = "8aFfE9c960cBDB01FFE373053";
        String APP_SECRET = "f2a0E9f5582Fc46DB9E6d713e22BF4b391f87BdfadbFe28a57";

        /*
          Key and secret initialisation
         */
        chirpConnect = new ChirpConnect(this, APP_KEY, APP_SECRET);
        chirpConnect.getLicence(new ConnectLicenceListener() {
            @Override
            public void onSuccess(String licence) {
                ChirpError licenceError = chirpConnect.setLicence(licence);
                if (licenceError.getCode() > 0) {
                    Log.e("setLicenceError", licenceError.getMessage());
                }
            }

            @Override
            public void onError(ChirpError chirpError) {
                Log.e("getLicenceError", chirpError.getMessage());
            }
        });


        /*
          Set-up the connect callbacks
         */
        chirpConnect.setListener(new ConnectEventListener() {
            @Override
            public void onSending(byte[] data) {
                /*
                  onSending is called when a send event begins.
		          The data argument contains the payload being sent.
		         */
                String s = "null";
                if (data != null) {
                    s = new String(data);
                }
                Log.v("chirp-client", "ConnectCallback: onSending: " + s);
            }

            @Override
            public void onSent(byte[] data) {
                /*
                  onSent is called when a send event has completed.
		          The data argument contains the payload that was sent.
		         */
                String s = "null";
                if (data != null) {
                    s = new String(data);
                }
                Log.v("chirp-client", "ConnectCallback: onSent: " + s);
            }

            @Override
            public void onReceiving() {
                /*
                  onReceiving is called when a receive event begins.
		          No data has yet been received.
		         */
                Log.v("chirp-client", "ConnectCallback: onReceiving");
            }

            @Override
            public void onReceived(byte[] data) {
                /*
                  onReceived is called when a receive event has completed.
		          If the payload was decoded successfully, it is passed in data.
		          Otherwise, data is null.
		         */
                String s = null;
                if (data != null) {
                    s = new String(data);
                }

                Log.v("chirp-client", "ConnectCallback: onReceived: " + s);

                if (s == null) {
                    Log.e("MessageCastingError: ", "String is null");
                    return;
                }

                updateTxtFromServer(s);
            }

            @Override
            public void onStateChanged(byte oldState, byte newState) {
                /*
                  onStateChanged is called when the SDK changes state.
		         */
                ConnectState state = ConnectState.createConnectState(newState);
                Log.v("chirp-client", "ConnectCallback: onStateChanged " + oldState + " -> " + newState);
            }

            @Override
            public void onSystemVolumeChanged(int oldVolume, int newVolume) {
                /*
                  onSystemVolumeChanged is called when the system volume is changed.
                 */
                Snackbar snackbar = Snackbar.make(parentLayout, "System volume has been changed to: " + newVolume, Snackbar.LENGTH_LONG);
                snackbar.setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
                Log.v("chirp-client", "System volume has been changed, notify user to increase the volume when sending data");
            }

        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startSdk();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtPassword.setEnabled(true);
                        txtFromServer.setEnabled(true);
                        btnSend.setEnabled(true);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        } else {
            startSdk();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    stopSdk();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSdk();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            chirpConnect.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        stopSdk();
    }

    public void updateTxtFromServer(final String newPayload) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtFromServer.setText(newPayload);
            }
        });
    }

    public void stopSdk() {
        ChirpError error = chirpConnect.stop();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
        }
    }

    public void startSdk() {
        ChirpError error = chirpConnect.start();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
        }
    }

    public void btnSendOnClicked(View view) {
        /*
          A payload is a byte array dynamic size with a maximum size defined by the licence settings.
         */
        long maxPayloadLength = chirpConnect.getMaxPayloadLength();
        byte[] payload = txtPassword.getText().toString().getBytes();

        if (maxPayloadLength < payload.length) {
            Log.e("ConnectError: ", "Invalid Payload");
            return;
        }

        ChirpError error = chirpConnect.send(payload);

        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
        }
    }

}
