package com.free.freeweather.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.free.freeweather.R;

public class AboutDeveloperActivity extends AppCompatActivity {

    private EditText emailText;
    private String emailTextString;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);

        initView();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTextString = emailText.getText().toString();
                Intent data=new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:peizhouyu@live.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, "hello MrPei from user");
                data.putExtra(Intent.EXTRA_TEXT, emailTextString);
                startActivity(data);
            }
        });
    }



    private void initView(){
        emailText = (EditText) findViewById(R.id.contentPanel);
        submitBtn = (Button) findViewById(R.id.submit_btn);

    }
}
