package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 정예린 on 11/15/2017.
 */

public class AddActivity extends AppCompatActivity{

    DatabaseHelper myDb;
    EditText txtName, txtPhone, txtEmail, txtAddress;
    Button btnClick;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);
        myDb = new DatabaseHelper(this);

        txtName = (EditText)findViewById(R.id.editName);
        txtPhone = (EditText)findViewById(R.id.editPhone);
        txtEmail = (EditText)findViewById(R.id.editEmail);
        txtAddress = (EditText)findViewById(R.id.editAddress);
        btnClick = (Button)findViewById(R.id.buttonAdd);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickMe();
            }
        });
    }

    private void ClickMe(){
        String name = txtName.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();
        String address = txtAddress.getText().toString();
        Boolean result = myDb.insertData(name, phone, email, address);
        if(result == true){
            Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, null);
        }else{
            Toast.makeText(this, "Data inserted Failed", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
