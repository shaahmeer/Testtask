package com.example.testtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopupWindowActivity extends AppCompatActivity {
    TextView btcpop;
    TextView satoshee;
    Button share,backpressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        btcpop = findViewById(R.id.btcpopup);
        satoshee = findViewById(R.id.satoshi);
        share = findViewById(R.id.share);
        backpressed = findViewById(R.id.back);
        Bundle gt=getIntent().getExtras();
        String btc=gt.getString("btc");
        String sts = gt.getString("satoshi");
        btcpop.setText(btc);
        satoshee.setText(sts);
        sharebutton();
        onBackPressed();

    }

    public void sharebutton(){
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Where do you want to share ";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
    }

    public void share(View view) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your subject here";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }


    public void onBackPressed() {
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopupWindowActivity.this,BlankFragment2.class);
                startActivity(intent);
            }
        });

    }
}