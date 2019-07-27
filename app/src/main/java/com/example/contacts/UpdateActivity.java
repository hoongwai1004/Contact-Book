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
 * Created by 정예린 on 11/21/2017.
 */

public class UpdateActivity extends AppCompatActivity{
    DatabaseHelper myDb;
    EditText txtName, txtID, txtPhone, txtEmail, txtAddress;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_update);
        myDb = new DatabaseHelper(this);
        txtID = (EditText)findViewById(R.id.editId2);
        txtName = (EditText)findViewById(R.id.editName2);
        txtPhone = (EditText)findViewById(R.id.editPhone2);
        txtEmail = (EditText)findViewById(R.id.editEmail2);
        txtAddress = (EditText)findViewById(R.id.editAddress2);
        btnClick = (Button)findViewById(R.id.buttonAdd2);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickMe();
            }
        });
    }

    public void ClickMe(){
        String id = txtID.getText().toString();
        String name = txtName.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();
        String address = txtAddress.getText().toString();
        Boolean result = myDb.updateData(id, name, phone, email, address);
        if(result == true){
            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, null);
        }else {
            Toast.makeText(this, "No Rows Affected", Toast.LENGTH_SHORT).show();
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
