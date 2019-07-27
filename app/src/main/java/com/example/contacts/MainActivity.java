package com.example.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity{

    ListViewAdapter listViewAdapter;
    DatabaseHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    private Contact contact;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemAdd:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.itemDelete:
                Intent intent1 = new Intent(this, DeleteActivity.class);
                startActivityForResult(intent1, 1);
                return true;
            case R.id.itemEdit:
                Intent intent2 = new Intent(this, UpdateActivity.class);
                startActivityForResult(intent2, 1);
                return true;
            case R.id.itemAbout:
                Intent intent3 = new Intent(this, AboutActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void populate(){
        myDb = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        res = myDb.getAllData();
        String id, name, phone, email, address;

        ListView listView = (ListView)findViewById(R.id.list_item);
        listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.activity_main);
        listView.setAdapter(listViewAdapter);

        if(res.moveToFirst()){
            do{
                id = res.getString(0);
                name = res.getString(1);
                phone = res.getString(2);
                email = res.getString(3);
                address = res.getString(4);
                contact = new Contact(id, name, phone, email, address);
                listViewAdapter.add(contact);
            }while (res.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = (Contact)listViewAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ContactPage.CONTACT, contact);
                Intent intent = new Intent(MainActivity.this, ContactPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
