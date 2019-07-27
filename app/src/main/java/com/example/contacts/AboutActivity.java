package com.example.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 정예린 on 11/20/2017.
 */

public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_about);
        String words = "Contacts can be used to store your contacts.\nYou can add new contact into your list.\n\nSupported by: SourceMusic Entertainment";

        TextView textView = (TextView)findViewById(R.id.textViewAbout);
        textView.setText(words);
    }
}
