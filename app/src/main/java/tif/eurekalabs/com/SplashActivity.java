package tif.eurekalabs.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tif.eurekalabs.com.misc.ConnectionDetector;
import tif.eurekalabs.com.misc.Constant;

public class SplashActivity extends AppCompatActivity {

    Boolean isConnected = false;
    ConnectionDetector cd;

    RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        parent = (RelativeLayout) findViewById(R.id.parent);
        reload();
    }

    private void reload() {
        cd = new ConnectionDetector(getApplicationContext());
        isConnected = cd.isConnectingToInternet(this);


        if (isConnected) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            final boolean isLoggedIn = prefs.getBoolean(Constant.IS_LOGGED_IN_KEY, false);
            final boolean isFirstLaunch = prefs.getBoolean(Constant.IS_FIRST_LAUNCH_KEY ,true);
            final boolean isLocationSet = prefs.getBoolean(Constant.IS_LOCATION_SET_KEY, false);

            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    } finally {

                        if (!isLoggedIn) {

                            Intent mainactivity1 = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(mainactivity1);
                        } else {
                            Intent mainactivity2 = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(mainactivity2);
                        }

                    }
                }

            };
            timer.start();
        } else {
            final Snackbar snackbar = Snackbar
                    .make(parent, "Please check internet connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reload();
                        }
                    });

            snackbar.setActionTextColor(Color.RED);

            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlueGrey));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
    }


    protected void onPause() {
        super.onPause();
        finish();
    }

}
