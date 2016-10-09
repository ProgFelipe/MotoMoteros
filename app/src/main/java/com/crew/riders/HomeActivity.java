package com.crew.riders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_maps) Button home_maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        home_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        LoginManager.getInstance().logOut();
    }

    private void goToMap(){
        Intent intent = new Intent(this, RidersMapActivity.class);
        startActivity(intent);
    }
}
