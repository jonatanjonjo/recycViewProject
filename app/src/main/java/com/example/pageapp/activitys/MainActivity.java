package com.example.pageapp.activitys;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.Navigation;

import com.example.pageapp.R;
import com.example.pageapp.models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
    }
    public void login(View view){
        String email=((EditText)findViewById(R.id.editTextEmail)).getText().toString();
        String password=((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"login ok",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_fragmentOne_to_fragmentThree);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"login not ok",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    public void register(){
        String email=((EditText)findViewById(R.id.etEmail)).getText().toString();
        String password=((EditText)findViewById(R.id.etPassword)).getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"reg ok",Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this,"reg not ok",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }




    public void addData(){
        String phone=((EditText) findViewById(R.id.etPhoneNumber)).getText().toString();
        String email=((EditText) findViewById(R.id.etEmail)).getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(phone);
        Student s =new Student(phone,email);
        myRef.setValue(s);
    }

    public void getStudent(String phone){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(phone);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Student value = dataSnapshot.getValue(Student.class);
                Toast.makeText(MainActivity.this,value.getEmail(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }


}




