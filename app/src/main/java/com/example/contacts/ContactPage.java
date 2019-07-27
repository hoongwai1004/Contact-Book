package com.example.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 정예린 on 11/17/2017.
 */

public class ContactPage extends AppCompatActivity{

    DatabaseHelper myDb;
    public static final String CONTACT = "contact";
    private TextView contact_id, contact_name, contact_phone, contact_email, contact_address;
    String sNum, sURL, sId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details2);

        contact_id = (TextView)findViewById(R.id.viewId2);
        contact_name= (TextView)findViewById(R.id.viewContactName2);
        contact_phone = (TextView)findViewById(R.id.viewPhone2);
        contact_email = (TextView)findViewById(R.id.viewEmail2);
        contact_address = (TextView)findViewById(R.id.viewAddress2);


        if(getIntent().getExtras() != null){
            Contact contact = (Contact)getIntent().getSerializableExtra(CONTACT);
            if(contact != null){
                contact_id.setText(contact.getId());
                contact_name.setText(contact.getName());
                contact_phone.setText(contact.getPhone());
                contact_email.setText(contact.getEmail());
                contact_address.setText(contact.getAddress());
            }
        }

        callOnClick();
        emailOnClick();
        smsOnClick();
        webOnClick();
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

    private void callOnClick(){
        FloatingActionButton fabPhoneCall = (FloatingActionButton) findViewById(R.id.floatingActionButtonPhoneCall);
        fabPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                sNum = contact_phone.getText().toString();
                intent.setData(Uri.parse("tel:" + sNum));

                if(ActivityCompat.checkSelfPermission(ContactPage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Please grant the permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    private void emailOnClick(){
        FloatingActionButton fabEmail = (FloatingActionButton)findViewById(R.id.floatingActionButtonEmail);
        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("email"));
                String [] sEmail = {contact_email.getText().toString()};
                intent.putExtra(Intent.EXTRA_EMAIL, sEmail);
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Launch Email");
                startActivity(chooser);
            }
        });
    }

    private void smsOnClick(){
        FloatingActionButton fabMessage = (FloatingActionButton)findViewById(R.id.floatingActionButtonMessage);
        fabMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                sNum = contact_phone.getText().toString();
                intent.setData(Uri.parse("sms:" + sNum));
                startActivity(intent);
            }
        });
    }

    private void webOnClick(){
        FloatingActionButton fabFB = (FloatingActionButton)findViewById(R.id.floatingActionButtonFB);
        fabFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                sURL = contact_address.getText().toString();
                intent.setData(Uri.parse("http://" + sURL));
                startActivity(intent);
            }
        });
    }
}
