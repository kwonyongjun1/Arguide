package com.example.arguide_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText id_et,pw_et;
    private String id,pw;
    Button login_btn,join_btn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_et = findViewById(R.id.loginid_et);
        pw_et = findViewById(R.id.loginpw_et);
        login_btn = findViewById(R.id.login_btn);
        join_btn = findViewById(R.id.join_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Join.class);
                startActivity(intent);
                finish();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id_et.getText().toString().trim();
                pw = pw_et.getText().toString().trim();

                if(id.equals("") || pw.equals("")){
                    Toast.makeText(Login.this, "정보를 입력하세요", Toast.LENGTH_SHORT).show();

                }else{
                    firebaseAuth.signInWithEmailAndPassword(id,pw).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if(user.isEmailVerified()){
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(Login.this, "로그인 정보를 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}