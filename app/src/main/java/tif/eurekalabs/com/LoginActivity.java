package tif.eurekalabs.com;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import tif.eurekalabs.com.misc.ConnectionDetector;
import tif.eurekalabs.com.rest.ServiceHandler;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 007;

    private static final String TAG = "LoginActivity";

    private AppCompatButton btnGoogleSignIn;
    private AppCompatButton btnSignIn;

    private AppCompatEditText etUsername;
    private AppCompatEditText etPassword;

    private TextView tvForgotPswd;
    private TextView tvSignUp;

    String strUsername;
    String strPassword;

    RelativeLayout parent;

    LoginButton btnFbSignIn;

    Toolbar toolbar;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        btnFbSignIn = (LoginButton) findViewById(R.id.btn_fb_sign_in);

        parent = (RelativeLayout) findViewById(R.id.parent);

        btnGoogleSignIn = (AppCompatButton) findViewById(R.id.btn_google_sign_in);
        btnSignIn = (AppCompatButton) findViewById(R.id.btn_login);

        etUsername = (AppCompatEditText) findViewById(R.id.et_username);
        etPassword = (AppCompatEditText) findViewById(R.id.et_password);

        tvForgotPswd = (TextView) findViewById(R.id.tv_forgot_pswd);
        tvSignUp = (TextView) findViewById(R.id.tv_signup);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_white));


        btnGoogleSignIn.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

        tvSignUp.setOnClickListener(this);
        tvForgotPswd.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("388282401496-qc8cp8hou8cndv9822j3jsm6e0i5hj4u.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btnFbSignIn.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        //facebook button click hendler
        callbackManager = CallbackManager.Factory.create();
        btnFbSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e(TAG, "User ID: " + loginResult.getAccessToken().getToken());
                        new getFacebookUserDetails(loginResult.getAccessToken().getToken()).execute();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login attempt canceled.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Toast.makeText(LoginActivity.this, "Login attempt failed.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "resultcode" + requestCode);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    private class getFacebookUserDetails extends AsyncTask<String, Void, String> {


        String accessToken;
        String name;
        String email;

        getFacebookUserDetails(String token) {
            accessToken = token;
        }

        @Override
        protected String doInBackground(String... params) {

            ServiceHandler test = new ServiceHandler();
            String response = null;

            try {
                response = test.getInternetData("https://graph.facebook.com/v2.8/me?fields=id%2Cname%2Cemail&access_token=" + accessToken);

                if (response != null) {
                    JSONObject jsonObj = new JSONObject(response);
                    if (jsonObj != null) {
                        name = jsonObj.getString("name");
                        email = jsonObj.getString("email");

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        Log.e(TAG, "Name: " + name + ", email: " + email);

                    }
                }
            } catch (JSONException e) {
                Log.e("JSONException", "" + e);
            } catch (Exception e) {
                // TODO Auto-generated catch block


                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null)
                Toast.makeText(LoginActivity.this, "Name: " + name + ", email: " + email, Toast.LENGTH_LONG).show();
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());
            Log.e(TAG, "display name: " + acct.getIdToken());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);
            // Toast.makeText(this, "Welcome " + personName, Toast.LENGTH_LONG).show();


        } else {
            Log.d(TAG, "else");
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_google_sign_in:

                if (ConnectionDetector.isConnectingToInternet(LoginActivity.this)) {
                    signIn();
                } else {
                    // Misc.createSnackbarRed("Please check internet connection", parent, LoginActivity.this);
                }
                break;
            case R.id.btn_login:
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.tv_forgot_pswd:
                Intent i2=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(i2);
                break;
            case R.id.tv_signup:
                Intent i3=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i3);
        }
    }
}
