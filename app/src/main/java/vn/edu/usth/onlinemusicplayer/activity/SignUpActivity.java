package vn.edu.usth.onlinemusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.menu.CustomActionBarFragment;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        CustomActionBarFragment.header_title.setText("Sign Up");
        CustomActionBarFragment.search.setVisibility(View.GONE);

        TextView signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        TextView signup = findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    private void registerAccount() {
        EditText input_username = findViewById(R.id.username);
        String username = input_username.getText().toString();

        EditText input_email = findViewById(R.id.email);
        String email = input_email.getText().toString();

        EditText input_pwd = findViewById(R.id.pwd);
        String pwd = input_pwd.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // If sign up fails, display a message to the user.
                            Log.w("error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign up success, update UI with the signed-in user's information
                            Log.d("success", "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Authentication succeed.",
                                    Toast.LENGTH_SHORT).show();

                            // Update display name
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateDisplayName(user, username);
                            finish();
                        }
                    }
                });
    }

    private void updateDisplayName(FirebaseUser user, String username) {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        user.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) { //success on updating user profile
                            Toast.makeText(getApplicationContext(), "Registration Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else { //failed on updating user profile
                            Toast.makeText(getApplicationContext(), "Registration Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}