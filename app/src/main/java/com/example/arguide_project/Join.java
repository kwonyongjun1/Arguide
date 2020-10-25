package com.example.arguide_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Join extends AppCompatActivity {
    private EditText id_et,pw_et,pwcheck_et,name_et;
    private String id,pw,pwcheck,name;
    private Button join_btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        id_et = findViewById(R.id.id_et);
        pw_et = findViewById(R.id.pw_et);
        pwcheck_et = findViewById(R.id.pwcheck_et);
        name_et = findViewById(R.id.name_et);
        join_btn = findViewById(R.id.signup_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");


        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id_et.getText().toString().trim();
                pw = pw_et.getText().toString().trim();
                pwcheck = pwcheck_et.getText().toString().trim();
                name = name_et.getText().toString().trim();

                if(pw.equals(pwcheck)){
                    firebaseAuth.createUserWithEmailAndPassword(id,pw).
                            addOnCompleteListener(Join.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = firebaseAuth.getCurrentUser();

                                        sendVerificationEmail();

                                        String id = user.getEmail();
                                        String uid = user.getUid();

                                        HashMap<Object, String> hashMap = new HashMap<>();
                                        hashMap.put("uid",uid);
                                        hashMap.put("id",id);
                                        hashMap.put("name",name);
                                        hashMap.put("pw",pw);

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference reference = database.getReference("User");
                                        reference.child(uid).setValue(hashMap);

                                        Toast.makeText(Join.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else if (!task.isSuccessful()){
                                        Toast.makeText(Join.this, "fail", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }else{
                    Toast.makeText(Join.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //메일 인증 요청
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Join.this, "인증메일이 발송되었습니다.", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                }
                else{
                    Toast.makeText(Join.this, "인증메일 발송이 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}