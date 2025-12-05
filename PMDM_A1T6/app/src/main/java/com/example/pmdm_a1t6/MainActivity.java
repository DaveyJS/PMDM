package com.example.pmdm_a1t6;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText mail;
    EditText pass;
    Switch remember;
    TextView result;

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

        submit = findViewById(R.id.button);
        mail = findViewById(R.id.cajaTextoCorreo);
        pass = findViewById(R.id.cajaTextoPass);
        remember = findViewById(R.id.switchRecordar);

        result = findViewById(R.id.textView4);
        submit.setOnClickListener(v -> login(mail.getText().toString(), pass.getText().toString()));
    }

    private void login(String mail, String pass) {
        result.setVisibility(TextView.VISIBLE);
        boolean r = remember.isChecked();
        if (mail.equals("correo@correo.com") && pass.equals("123")) {
            if (r) result.setText("Usuario y contraseña correctos.\nAlmacenados para siguiente acceso.");
            else result.setText("Usuario y contraseña correctos.");
            result.setTextColor(Color.GREEN);

            Intent menuActivity = new Intent(this, MenuActivity.class);
            menuActivity.putExtra("mail", mail);
            startActivity(menuActivity);
        } else {
            result.setText("Usuario y/o contraseña incorrectos.");
            result.setTextColor(Color.RED);
        }
    }
}