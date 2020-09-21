package com.example.hmm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogRegActivity extends AppCompatActivity {

    TextView txtStatusDriver, txtPodstatusDriver, txtNotAkkDriver;
    Button btnDriverLog, btnDriverReg;
    EditText emailDriver, passDriver;

    FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);

        txtStatusDriver = (TextView)findViewById(R.id.txtStatusDriver);
        txtPodstatusDriver = (TextView)findViewById(R.id.txtPodstatusDriver);
        txtNotAkkDriver = (TextView)findViewById(R.id.txtNotAkkDriver);

        btnDriverLog = (Button)findViewById(R.id.btnDriverLog);
        btnDriverReg = (Button)findViewById(R.id.btnDriverReg);

        emailDriver = (EditText)findViewById(R.id.emailDriver);
        passDriver = (EditText)findViewById(R.id.passDriver);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        btnDriverReg.setEnabled(false);

        txtNotAkkDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDriverReg.setVisibility(View.VISIBLE);
                btnDriverLog.setVisibility(View.INVISIBLE);

                btnDriverReg.setEnabled(true);

            }
        });
        btnDriverReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailDriver.getText().toString();
                String password = passDriver.getText().toString();

                RegisterDriver(email, password);
            }

            private void RegisterDriver(String email, String password)
            {
                loadingBar.setTitle("Регистрация пользователя");
                loadingBar.setMessage("Дождитесь окончания загрузки, обычно это занимает 1-5 секунд");
                loadingBar.show();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(LogRegActivity.this, "Регистрация прошла успешна", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent driverIntent = new Intent(LogRegActivity.this, MenuActivity.class);
                            startActivity(driverIntent);
                        }
                        else
                        {
                            Toast.makeText(LogRegActivity.this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }

                    }
                });

            }
        });

        btnDriverLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailDriver.getText().toString();
                String password = passDriver.getText().toString();

                LoginDriver(email, password);
            }

            private void LoginDriver(String email, String password) {
                loadingBar.setTitle("Авторизация пользователя");
                loadingBar.setMessage("Дождитесь окончания загрузки, обычно это занимает 1-5 секунд");
                loadingBar.show();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(LogRegActivity.this, "Авторизация прошла успешна", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent driverIntent = new Intent(LogRegActivity.this, MenuActivity.class);
                            startActivity(driverIntent);
                        }
                        else
                        {
                            Toast.makeText(LogRegActivity.this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                });
            }

        });

    }

}