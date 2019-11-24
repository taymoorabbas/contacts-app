package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactapp.data.DatabaseHandler;
import com.example.contactapp.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        contactList = new ArrayList<>();


          DatabaseHandler database = new DatabaseHandler(MainActivity.this);

        Contact contact1 = new Contact("adam khor","24253643");
        database.addContact(contact1);

        Contact contact2 = new Contact("malik al mout","75778493");
        database.addContact(contact2);

        Contact contact3 = new Contact("pakora","99292993949");
        database.addContact(contact3);

        Contact contact4 = new Contact("mechanic","112424249");
        database.addContact(contact4);

        Contact contact5 = new Contact("kasai","99847377282");
        database.addContact(contact5);

        Contact contact6 = new Contact("ghulam mustafa","678849930");
        database.addContact(contact6);

        Contact contact7 = new Contact("machhar","00010304043");
        database.addContact(contact7);

        Contact contact8 = new Contact("ghunda","3323999390002");
        database.addContact(contact8);

        Contact contact9 = new Contact("sir fida","11122333");
        database.addContact(contact9);

        Contact contact10 = new Contact("qamar bajwa","8888848488448");
        database.addContact(contact10);

        Contact contact11 = new Contact("elon musk","838290929383232");
        database.addContact(contact11);

        Contact contact12 = new Contact("Khadim rizvi","013 tuadi pen di siri");
        database.addContact(contact12);


        final List<Contact> contacts = database.getAllContacts();

        for (int x=0; x<contacts.size(); x++){

            Log.d("contacts", "contact: " + contacts.get(x).getContactName() + ", " + contacts.get(x).getPhoneNumber());
            contactList.add(contacts.get(x).getContactName());
        }

        //layout is stock list layouts from android resources
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(MainActivity.this, contacts.get(position).getPhoneNumber() + "Oo soora", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
