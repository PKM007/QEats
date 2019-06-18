package com.example.dell.qeats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userId;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId=findViewById(R.id.userId);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                String id=userId.getText().toString();
                if(id.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter User ID",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences prefs = getSharedPreferences("loginFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("userId", id);
                    editor.commit();
                    i.putExtra("userId", id);
                    startActivity(i);
                }

            }
        });

    }
}
