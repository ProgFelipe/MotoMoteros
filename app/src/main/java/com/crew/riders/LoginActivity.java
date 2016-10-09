package com.crew.riders;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//import com.firebase.client.AuthData;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;

import java.io.IOException;
import java.util.Arrays;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    //private Handler handler;
    //Firebase ref = new Firebase("https://motomoteros-c409c.firebaseio.com");

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    // UI references.
    private View mProgressView;
    CallbackManager callbackManager;
    View gifImageView;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase.setAndroidContext(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        AppEventsLogger.activateApp(this);
        initFacebookLogin();
        if (isLoggedIn()){
            ShowHomeMenu();
        }


        mProgressView = findViewById(R.id.login_progress);
        gifImageView = findViewById(R.id.background_gif);
        //Background Gif
        backgroundGifAnimation();
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void initFacebookLogin(){
        // Set up the login facebook.
        loginButton = (LoginButton) findViewById(R.id.facebook_sign_in_button);
        loginButton.setReadPermissions("email");
        //Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                ShowHomeMenu();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    public void ShowHomeMenu(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void backgroundGifAnimation(){
        try {
            //resource (drawable or raw)
            final GifDrawable [] gifts = new GifDrawable[]{
                    new GifDrawable(getResources(), R.drawable.crew0)
            };

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

//            final int time = gifts[0].getDuration();
            final GifImageView imageView = (GifImageView)gifImageView;
            imageView.setImageDrawable(gifts[0]);
            imageView.setMinimumHeight(height);
            imageView.setMinimumWidth(width);

//            handler = new Handler();
//            Runnable runnable = new Runnable() {
//                int displayedGif = 0;
//                int handlerTime = 0;
//                GifDrawable gifDrawable = (GifDrawable) imageView.getDrawable();

//                @Override
//                public void run() {
//                    if(gifDrawable.isPlaying()){
//                        gifDrawable.stop();
//                        if(displayedGif < gifts.length-1){displayedGif++;}else{displayedGif = 0;}
//                        imageView.setImageDrawable(gifts[displayedGif]);
//                        gifDrawable = (GifDrawable) imageView.getDrawable();
//                        gifDrawable.setSpeed(0.6f);
//                        handlerTime = gifDrawable.getDuration();
//                        gifDrawable.start();
//                    }
//                    handler.postDelayed(this, handlerTime);
//                }
//            };
//
//
//            handler.postDelayed(runnable, time);

        }catch (IOException ex){
            Log.e("GifResource Exception", ex.getMessage());
        }
    }
//
//    private void onFacebookAccessTokenChange(AccessToken token) {
//        if (token != null) {
//            ref.authWithOAuthToken("facebook", token.getToken(), new Firebase.AuthResultHandler() {
//                @Override
//                public void onAuthenticated(AuthData authData) {
//                    // The Facebook user is now authenticated with your Firebase app
//                }
//                @Override
//                public void onAuthenticationError(FirebaseError firebaseError) {
//                    // there was an error
//                }
//            });
//        } else {
//        /* Logged out of Facebook so do a logout from the Firebase app */
//            ref.unauth();
//        }
//    }

}

