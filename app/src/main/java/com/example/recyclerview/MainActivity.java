package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactClickListener {

    // variable two editText, button and RecyclerView
    EditText nameText, phoneNumberText;
    Button submitButton;
    RecyclerView recyclerView;
    ArrayList<Contact> contacts = new ArrayList<>();
    ContactAdapter adapter;
    ContactAdapter.OnContactClickListener onContactClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onContactClickListener = this;

        // call the initialize method
        Initialize();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameText.getText().toString().equals("") || phoneNumberText.getText()
                        .toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please insert data", Toast.LENGTH_SHORT).show();

                }else {
                    String name, phoneNumber;
                    name = nameText.getText().toString();
                    phoneNumber = phoneNumberText.getText().toString();
                    Contact temp = new Contact(name, phoneNumber);
                    contacts.add(temp);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    // create a initialize method to initialize all view variable
    private void Initialize() {
        nameText = findViewById(R.id.nameid_et);
        phoneNumberText = findViewById(R.id.phonneid_et);
        submitButton = findViewById(R.id.submit_btn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contacts, onContactClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onContactClick(int position) {
        Contact tempContact = contacts.get(position);
        Toast.makeText(MainActivity.this, "name: " + tempContact.getName() + " , and cell no: "
                + tempContact.getPhoneNumber(), Toast.LENGTH_SHORT).show();

    }
}