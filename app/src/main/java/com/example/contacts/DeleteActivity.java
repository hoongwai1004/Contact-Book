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

public class DeleteActivity extends AppCompatActivity{
    DatabaseHelper myDb;
    EditText txtID;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_delete);
        myDb = new DatabaseHelper(this);
        txtID = (EditText)findViewById(R.id.txtId);
        btnClick = (Button)findViewById(R.id.buttonDelete);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickMe();
            }
        });
    }

    public void ClickMe(){
        String id = txtID.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this, result + ":Rows Affected", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, null);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
